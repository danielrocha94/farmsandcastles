package Plataform;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Points {
	public static int counter = 0;
	public static int money= 0;
	public Points(){
		
	}
	
	public void render(Graphics g){
		if(!Store.isOpen){
			g.setColor(new Color(14,95,16));
			g.setFont(new Font("Arial", Font.BOLD, 14));
			g.drawString("POINTS: " + counter, 2, 375);
			g.drawString("SPACE LEFT: " + Level.emptySpaces, 2, 400 );
		}
	}
}
