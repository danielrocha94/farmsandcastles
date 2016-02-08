package Plataform;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFrame;


public class Component extends Applet implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static int sizeX = 720;
	public static int sizeY = 837;
	public static int pixelSize = 2;
	public static int sX = 0, sY =0;
	public static boolean isRunning = false;
	
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;
	
	public static String name = "Farms & Castles";
	
	public static Point mse = new Point(0,0);
	
	public static Dimension size = new Dimension(sizeX, sizeY);
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);
	public static Dimension realSize;

	private Image screen;
	
	public static Level level;
	public static Inventory inventory;
	public static Points points;
	//public static Store store;
	
	public Component(){
		setPreferredSize(size);
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
//		addMouseWheelListener(new Listening());
	}
	
	public void start(){
		level = new Level();//generate level...
		new Tile();	//loading images...
		inventory = new Inventory();
		
		//game loop
		isRunning = true;
		new Thread(this).start();//something you can run multiple times
	}
	
	public void stop(){
		isRunning = false;
	}
	
	public static void main(String args[]){
		Component component = new Component();
		
		JFrame frame = new JFrame();
		frame.add(component);
		frame.pack();
		
		
		
		frame.setTitle(name);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		component.start();
		
	}

	private void tick() {
		level.tick();
	}
	private void render(){
		Graphics g = screen.getGraphics(); // draw on top of the screen img
		g.setColor(new Color(29,151,4));
		g.fillRect(0, 0, pixel.width, pixel.height);
		
		level.render(g);
		inventory.render(g);
		//store.render(g);
		//points.render(g);
		
		g = getGraphics();
		
		//g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();
	}
	@Override
	public void run() {
		screen = createVolatileImage(pixel.width, pixel.height); // on top of graphics card
				
		while (isRunning){
			tick();	// tick before render -- much easier when creating games
			render();
			try{
				Thread.sleep(5);
			}catch(Exception e){
				
			}
		}
	}
}
