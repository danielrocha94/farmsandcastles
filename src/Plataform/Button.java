package Plataform;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Button extends Rectangle{


	private static final long serialVersionUID = 1L;
	
	public int[] id = {0,0};
	
	public Button(Rectangle size, int[] id){
		setBounds(size);
		this.id = id;
	}
	
	public void render(Graphics g){
		g.drawImage(Tile.store_button, x, y, width, height, null);

	}
}
