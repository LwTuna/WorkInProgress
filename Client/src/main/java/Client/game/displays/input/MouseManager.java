package Client.game.displays.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;


public class MouseManager implements MouseListener,MouseMotionListener{

	
	private int mouseX=0,mouseY=0;
	private boolean leftPressed=false,rightPressed=false;
	private boolean leftClicked = false,rightClicked=false;
	
	private int AmouseX=0,AmouseY=0;
	private boolean AleftPressed=false,ArightPressed=false;
	private boolean AleftClicked = false,ArightClicked=false;
	
	//TODO
	public void tick() {
		AmouseX = mouseX;
		AmouseY = mouseY;
		AleftPressed = leftPressed;
		ArightPressed = rightPressed;
		
		AleftClicked = leftClicked;
		ArightClicked = rightClicked;
		leftClicked = false;
		rightClicked = false;
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftClicked = true;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			leftClicked = true;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			rightPressed = false;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	public int getAmouseX() {
		return AmouseX;
	}

	public int getAmouseY() {
		return AmouseY;
	}

	public boolean isAleftPressed() {
		return AleftPressed;
	}

	public boolean isArightPressed() {
		return ArightPressed;
	}

	public boolean isAleftClicked() {
		return AleftClicked;
	}

	public boolean isArightClicked() {
		return ArightClicked;
	}

	
	
}
