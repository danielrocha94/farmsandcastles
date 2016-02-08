package Plataform;

import java.awt.*;

public class Cell extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int[] id = {0,0};
	
	public Cell(Rectangle size, int[] id){
		setBounds(size);
		this.id = id;
		
	}
	
	public void render(Graphics g){
		if(id != Tile.air){
			g.drawImage(Tile.tileset_terrain, x + Tile.invItemBorder, y + Tile.invItemBorder, x - Tile.invItemBorder + width, y + height, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
		g.drawImage(Tile.tile_cell, x, y, width, height, null);
		
	}
}
