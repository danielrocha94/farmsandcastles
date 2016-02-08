package Plataform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Level{
	private static final String System = null;
	public int counter = 0;
	public int pushCounter = 0;
	public static int emptySpaces =0;
	public static int MAPSIZE = 6;
	public static Block[][] block = new Block[MAPSIZE][MAPSIZE];
	public static Points points = new Points();
	public static Store store = new Store();
	public int[][] matrix = new int[50][2];
	public Level(){
		for(int x=0; x< block.length; x++){
			for(int y=0; y<block.length; y++){
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air); //render block size level
			}
		}
		generateLevel();
	}
	
	public void generateLevel(){
		//random world generation
		int randCount = 0;
		Random random = new Random();
		for(int x=0; x< block.length; x++){
			for(int y=0; y<block.length; y++){
				int valorRand = random.nextInt(10);
				if(randCount < 3 && valorRand > 7){	// generate random mountains and lake
					valorRand = random.nextInt(3);
					if(valorRand > 1){
						block[x][y].id = Tile.lake;
					}else{
						block[x][y].id = Tile.mountain;
					}
					randCount++;
				} 
				
				else if(valorRand > 4 && valorRand <= 7 ){
					if(new Random().nextInt(2) == 1){
						block[x][y].id = Tile.stone;
					}else {
						block[x][y].id = Tile.grass;
					}
				}
				
				else block[x][y].id = Tile.earth;
			}
			
			
			
		}
		for(int y =0; y < block.length-1; y++){
			for(int x =0; x < block.length-1; x++){
				if(block[x][y].id != Tile.earth && block[x][y].id != Tile.lake && block[x][y].id != Tile.mountain){
					placing(x, y);
				}
			}
		}
		Points.counter = 0;
		Points.money = 0;

	}
	
	public void placing(int x, int y){
		counter=0;
		pushCounter =0;
		Points.counter += 1;
		Points.money += 1;
		System.out.println("points: " + points);
		for(int[] row : matrix){
			Arrays.fill(row, -1);
		}
		checking(x, y);
		if(counter >= 3){
			transform(matrix[0][0], matrix[0][1]);
		}
	}
			
		
	
	
	public void checking(int x, int y){
		counter++;	
		push(x, y);
		
		if(x < block.length-1 && x > 0 && y < block.length-1 && y >0){//centro
			System.out.println("los valores no esten en las esquinas");
			down(x, y);
			right(x, y);
			left(x, y);
			up(x, y);
		}
		else if(x == 0 && y != 0 && y != block.length-1){ // <  
			up(x, y);
			down(x, y);
			right(x, y);
		}
		
		else if(x == 0 && y == 0){ 						// < ^
			down(x, y);
			right(x, y);
		}
		else if(x == 0 && y == block.length-1){ 		// < V
			up(x, y);
			right(x, y);
		}
		
		else if(y == block.length-1 && x != block.length-1 && x != 0){ // V
//			System.out.println("pared baja V");
			 up(x, y);
			 left(x, y);
			 right(x, y);
		}
		
		else if(y == block.length-1 && x == block.length-1){ 			//V >
			up(x, y);
			left(x, y);
		}
		
		
		
		else if(x == block.length-1 && y != 0 && y != block.length-1 ){				// >
			left(x, y);
			down(x, y);
			up(x, y);
		}
		
		else if(x == block.length-1 && y == 0){							// > ^
			down(x, y);
			left(x, y);
		}
		
		else if(y == 0 && x != block.length-1 && x != 0){  				//^
			down(x, y);
			left(x, y);
			right(x, y);
		}
		
	}
	
	public void right(int x, int y){
		if(block[x][y].id == block[x+1][y].id){//derecha
			if(!valueMatrixRepeated(x+1, y)){
				checking(x+1, y);			
			}					
		}
	}
	public void left(int x, int y){
		if(block[x][y].id == block[x-1][y].id){//izquierda
			if(!valueMatrixRepeated(x-1, y)){
				checking(x-1, y);			
			}		
		}
	}
	public void up(int x, int y){
		if(block[x][y].id == block[x][y-1].id){ //arriba
			if(!valueMatrixRepeated(x, y-1)){
				checking(x, y-1);			
			}						
		}
	}
	public void down(int x, int y){
		if(block[x][y].id == block[x][y+1].id){	//abajo
			if(!valueMatrixRepeated(x, y+1)){
				checking(x, y+1);			
			}					
		}
	}
		
	public void push(int a, int b){
		matrix[pushCounter][0] = a;
		matrix[pushCounter][1] = b;
		pushCounter++;
	}
	
	public boolean valueMatrixRepeated(int x, int y){
		boolean result = false;
		for(int j = 0; j < matrix.length; j++){
			if(	matrix[j][0] == x && matrix[j][1] == y){
				result = true;
			}
		}
		return result;

	}
	
	public void transform(int x, int y){
		System.out.println("Transformed item from pos: " + x+ ", " + y);
		if(block[x][y].id == Tile.grass){
			block[x][y].id = Tile.tree;
		}else if(block[x][y].id == Tile.stone ){
			block[x][y].id = Tile.threeStones;
		}else if(block[x][y].id == Tile.threeStones){
			block[x][y].id = Tile.wall;
//			Component.sizeX += 120;
//			Component.sizeY += 120;
//			MAPSIZE++;
		}else if(block[x][y].id == Tile.tree){
			block[x][y].id = Tile.forest;
		}else if(block[x][y].id == Tile.wall){
			block[x][y].id = Tile.tower;

		}else if(block[x][y].id == Tile.forest){
			block[x][y].id = Tile.tent;
		}else if(block[x][y].id == Tile.tent){
			block[x][y].id = Tile.farm;
		}else if(block[x][y].id == Tile.tower){
			block[x][y].id = Tile.castle;
		}else if(block[x][y].id == Tile.farm){
			block[x][y].id = Tile.bigFarm;
		}else if(block[x][y].id == Tile.castle){
			block[x][y].id = Tile.mansion;
		}
		
		for(int i =1; i < matrix.length-1; i++){
			
			if(matrix[i][0] != -1){
					block[matrix[i][0]][matrix[i][1]].id = Tile.earth;
					matrix[i][0] = -1;
					matrix[i][1] = -1;
			}		
		}
		placing(x, y);
		
	}
	
	public void building(){
		if(Component.isMouseRight || Component.isMouseLeft){
			emptySpaces = 0;
			for(int x = 0; x < MAPSIZE; x++){
				for(int y = 0; y < MAPSIZE; y++){
					if(block[x][y].contains(new Point((Component.mse.x  / Component.pixelSize)+ (int) Component.sX, (Component.mse.y  / Component.pixelSize)+ (int) Component.sY))){
						if(block[x][y].id == Tile.earth && Inventory.isHolding){
								block[x][y].id = Inventory.holdingID;
								Inventory.isHolding = false;
								placing(x, y);
						}
					}
					
					if(block[x][y].id == Tile.earth){
						emptySpaces++;
					}
				}
			}
			//System.out.println("Earth left: " + emptySpaces);
			if(emptySpaces == 0){
				JOptionPane.showMessageDialog(null, "Has perdido."); 
				Component component = new Component();
				
				JFrame frame = new JFrame();
				frame.add(component);
				frame.pack();
				frame.setTitle(Component.name);
				frame.setResizable(false);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				component.start();
				System.exit(0);
				
			}
		}
		if(!Inventory.isHolding){
			for(int i=0; i<Inventory.invBar.length; i++){
				if(Inventory.invBar[i].id == Tile.air){
					int rand = new Random().nextInt(100);
					if(rand >= 60){
						Inventory.invBar[i].id = Tile.grass;
					}else if(rand >= 20){
						Inventory.invBar[i].id = Tile.stone;
					}else if(rand >= 10){
						Inventory.invBar[i].id = Tile.threeStones;
					}else if(rand >= 5){
						Inventory.invBar[i].id = Tile.tree;
					}else if(rand >= 3){
						Inventory.invBar[i].id = Tile.wall;
					}else Inventory.invBar[i].id = Tile.forest;
				}
			}
		}
	}
	
	public void tick(){
		building();
	}
	
	public void render(Graphics g){
		for(int x=0; x< block.length; x++){
			for(int y=0; y<block.length; y++){
				block[x][y].render(g);
				if (block[x][y].contains(new Point((Component.mse.x  / Component.pixelSize)+ (int) Component.sX, (Component.mse.y  / Component.pixelSize)+ (int) Component.sY))) { //mouse clicks are exactly where the mouse is
					g.setColor(new Color(255, 255, 255, 100));
					g.fillRect(block[x][y].x, block[x][y].y, block[x][y].width, block[x][y].height);
				}
				
			}
		}
		points.render(g);
		store.render(g);
	}
}
