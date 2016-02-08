package Plataform;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;


public class Store {
	public static int numberOfItemsInStore = 5;
	public static boolean isOpen = false;
	public static Cell[] storeBar = new Cell[numberOfItemsInStore];
	public static Cell[] storeBag = new Cell[numberOfItemsInStore];
	
	public Store(){
		for(int i=0; i<storeBar.length; i++){
			storeBar[i] = new Cell(new Rectangle((Component.pixel.width/3)+15 - ((Tile.invLenght * (Tile.invCellSize + Tile.invCellSpace))/1) + (i*(Tile.invCellSize + Tile.invCellSpace)), (Component.pixel.height) - (Tile.invCellSize + Tile.invBorderSpace), Tile.invCellSize, Tile.invCellSize), Tile.air);
		}
		
		storeBar[0].id = Tile.grass;
		storeBar[1].id = Tile.stone;
		storeBar[2].id = Tile.tree;
		storeBar[3].id = Tile.threeStones;

		
//		int x = 0, y = 0;
//		for(int i=0; i< storeBag.length; i++){
//			storeBag[i] = new Cell(new Rectangle((Component.pixel.width/2) - ((Tile.invLenght * (Tile.invCellSize + Tile.invCellSpace))/2)  + (x * (Tile.invCellSize + Tile.invCellSpace)), Component.pixel.height - (Tile.invCellSize + Tile.invBorderSpace) - (Tile.invHeight * (Tile.invCellSize + Tile.invCellSpace)) + (y * (Tile.invCellSize + Tile.invCellSpace)), Tile.invCellSize, Tile.invCellSize ), Tile.air); // position of inv bar
//			x++;
//			if(x == Tile.invLenght){
//				x =0;
//				y++;
//			}
//		}
	}
	
	public static void click(MouseEvent e){
		//for(int i =0; i< storeBar.length; i++){
			if(e.getButton() == MouseEvent.BUTTON1 && storeBar[4].contains(new Point(Component.mse.x/Component.pixelSize, Component.mse.y/Component.pixelSize))){
				if(isOpen){
					isOpen = false;
					System.out.println("isOpen = "+isOpen);
				}else{
					isOpen = true;
					System.out.println("isOpen = "+isOpen);
				}
			}	
	//	}
			
			if(isOpen){
				for(int i =0; i< storeBar.length; i++){
					if(storeBar[i].contains(new Point(Component.mse.x/Component.pixelSize, Component.mse.y/Component.pixelSize))){
						if(storeBar[i].id != Tile.air && !Inventory.isHolding){
							if(!Inventory.isHolding){
								if (storeBar[i].id == Tile.grass && Points.counter >= 5){
									Inventory.holdingID = storeBar[i].id;
									Points.counter -= 5;
									Inventory.isHolding = true;
								}
							}
						}
					}
				}
			}
		
	}
	
	public void render(Graphics g){
		g.drawImage(Tile.store_button, 245, 360, 50, 60, null);
		g.drawImage(Tile.store_button2, 305, 360, 50, 60, null);
		if(isOpen){
			g.setColor(new Color(139,69,19));
			g.fillRect(0, storeBar[0].y - 3, storeBar[0].width*storeBar.length-10, storeBar[0].height+7);
			for(int i = 0; i < storeBar.length-1; i++){
					storeBar[i].render(g);
				}  
		}

	}
}
