package Client.game.ui.elements;

import java.awt.Graphics;

import Client.App;
import Client.game.states.State;
import Client.game.ui.UiElement;
import Client.game.ui.elements.interfaces.UiOnClickListener;

public abstract class UiButton extends UiElement{

	protected double width,height;
	
	private UiOnClickListener clickListener;
	
	protected boolean hovering = false;
	
	public UiButton(App app,double x, double y,double width,double height,UiOnClickListener listener) {
		super(app,x, y);
		this.width = width;
		this.height = height;
		this.clickListener = listener;
	}
	

	@Override
	public void tick() {
		double onScreenX = State.getCurrentState().convertX(x),onScreenY = State.getCurrentState().convertY(y);
		double onScreenWidth = State.getCurrentState().convertX(width),onScreenHeight = State.getCurrentState().convertY(height);
		if(app.getMouseManager().getAmouseX() >=onScreenX && app.getMouseManager().getAmouseX() <onScreenX+onScreenWidth) {
			if(app.getMouseManager().getAmouseY() >=onScreenY && app.getMouseManager().getAmouseY() < onScreenY+onScreenHeight) {
				hovering = true;
			}else {
				hovering = false;
			}
		}else {
			hovering =false;
		}
		if(hovering && app.getMouseManager().isAleftClicked()) {
			clickListener.onClick(app, this);
		}
	}

	@Override
	public abstract void render(Graphics g) ;

	
	
}
