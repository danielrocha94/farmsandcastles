package Plataform;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;



public class Listening implements  KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseDragged(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
		
	}


	public void mouseMoved(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
		
	}
	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){//left
			System.out.println("LEFT");
			Component.isMouseLeft = true;
		} else if(e.getButton() == MouseEvent.BUTTON3){//right
			System.out.println("RIGHT");
			Component.isMouseRight = true;
		}
		Inventory.click(e);
		Store.click(e);
	}


	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			Component.isMouseLeft = false;
		} else if(e.getButton() == MouseEvent.BUTTON3){
			Component.isMouseRight = false;
		}
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
