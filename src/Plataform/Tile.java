package Plataform;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Tile {
	public static int tileSize = 60; 
	public static int invLenght = 2;
	public static int invHeight = 2;
	public static int invCellSize = 50;
	public static int invCellSpace = 4;
	public static int invBorderSpace = 4;
	public static int invItemBorder = 3;
	
	public static int[] air = {-1, -1};
	public static int[] earth = {0, 0};
	public static int[] lake = {1, 0};
	public static int[] mountain = {2, 0};
	//type 1
	public static int[] stone = {0, 5};
	public static int[] grass = {0, 4};
	//type 2
	public static int[] threeStones = {1,5};
	public static int[] tree = {1,4};
	//type 3
	public static int[] wall = {2,5};
	public static int[] forest = {2, 4};
	//type 4
	public static int[] tower = {3, 5};
	public static int[] tent = {3, 4};
	//type 5
	public static int[] castle = {4, 5};
	public static int[] farm = {4, 4};
	//type 6
	public static int[] mansion = {5, 5};
	public static int[] bigFarm = {5, 4};
	
	public static BufferedImage tileset_terrain;
	public static BufferedImage tile_cell;
	public static BufferedImage store_button;
	public static BufferedImage store_button2;
	
	public Tile(){
		try{
			tileset_terrain = ImageIO.read(new File("resources/tileset_terrain.png"));
			tile_cell = ImageIO.read(new File("resources/tile_cell.png"));
			store_button = ImageIO.read(new File("resources/store_button.png"));
			store_button2 = ImageIO.read(new File("resources/store_button2.png"));

		}catch(Exception e){}
	}
	
}
