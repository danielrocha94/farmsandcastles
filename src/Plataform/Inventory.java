package Plataform;

import java.awt.*;
import java.awt.event.MouseEvent;





public class Inventory {
	
	public static int[] holdingID = Tile.air;
	public static boolean isHolding = false;
		
	public static Cell[] invBar = new Cell[Tile.invLenght];
	
	public Inventory(){
		for(int i=0; i<invBar.length; i++){
			invBar[i] = new Cell(new Rectangle((Component.pixel.width/2) - ((Tile.invLenght * (Tile.invCellSize + Tile.invCellSpace))/2) + (i*(Tile.invCellSize + Tile.invCellSpace)), Component.pixel.height - (Tile.invCellSize + Tile.invBorderSpace), Tile.invCellSize, Tile.invCellSize), Tile.air);
		}
		invBar[0].id = Tile.grass;
		invBar[1].id = Tile.stone;
		
		
	}
	public static void click(MouseEvent e){
		if(!Store.isOpen){
		for(int i =0; i< invBar.length; i++){
			if(invBar[i].contains(new Point(Component.mse.x/Component.pixelSize, Component.mse.y/Component.pixelSize))){
				if(invBar[i].id != Tile.air && !isHolding){
					holdingID = invBar[i].id; // with this you pick them up
					invBar[i].id = Tile.air;  // remove things by clicking
					
					isHolding = true;
				}else if(isHolding && invBar[i].id == Tile.air) {
					invBar[i].id = holdingID;
					isHolding = false;
				}else if (isHolding && invBar[i].id != Tile.air){
					int[] holdingID2 = invBar[i].id;
					invBar[i].id = holdingID;
					holdingID = holdingID2;
				}
				
			}
		}
		}
	}

	
	public void render(Graphics g){
		if(!Store.isOpen){
			for(int i=0; i<invBar.length; i++){
					invBar[i].render(g);
				}
			}
			
			if(isHolding){
				g.drawImage(Tile.tileset_terrain, (Component.mse.x/Component.pixelSize) - (Tile.invCellSize/2)/*size of sqr div/2*/ + Tile.invItemBorder, (Component.mse.y/Component.pixelSize)- (Tile.invCellSize/2)/*size of sqr div /2*/ + Tile.invItemBorder , (Component.mse.x/Component.pixelSize) + Tile.invCellSize - Tile.invItemBorder, (Component.mse.y/Component.pixelSize) + Tile.invCellSize - Tile.invItemBorder, holdingID[0] * Tile.tileSize, holdingID[1] * Tile.tileSize,holdingID[0] * Tile.tileSize + Tile.tileSize, holdingID[1] * Tile.tileSize + Tile.tileSize, null); // draw holdinh id on mouse
	
			}
		
		}
	}
