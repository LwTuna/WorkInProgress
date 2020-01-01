package Client.game.ui;

import java.awt.Graphics;

import Client.App;

public abstract class UiElement {

	protected boolean focused = false;
	
	protected App app;
	
	protected double x,y;
	
	
	
	public UiElement(App app,double x2,double y2) {
		this.x = x2;
		this.y = y2;
		this.app = app;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	
	public void setFocused(boolean focus) {
		focused = focus;
	}
	
}
