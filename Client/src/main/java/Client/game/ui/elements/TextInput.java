package Client.game.ui.elements;

import java.awt.Color;
import java.awt.Graphics;

import Client.App;
import Client.game.states.State;
import Client.game.ui.UiElement;

public class TextInput extends UiElement{

	protected boolean hovering = false;
	
	protected double width,height;
	
	protected String text = "";
	
	public TextInput(App app, double x2, double y2,double width,double height) {
		super(app, x2, y2);
		this.width = width;
		this.height = height;
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
			State.getManager().setFocused(this);
			
		}
		if(focused) {
			for(Character c : app.getKeyManager().lastTyped()) {
				if(c.equals('\b')) {
					if(text.length() >=1) {
						text = text.substring(0, text.length()-1);
					}
				}else {
					text += c;
				}
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(focused) {
			g.setColor(Color.lightGray);
		}else {
			g.setColor(Color.WHITE);
		}
		g.fillRect(State.getCurrentState().convertX(x), State.getCurrentState().convertY(y), State.getCurrentState().convertX(width), State.getCurrentState().convertY(height));
		g.setColor(Color.BLACK);
		g.drawString(text, State.getCurrentState().convertX(x), State.getCurrentState().convertY(y)+(State.getCurrentState().convertY(height)/2));
	}

	public String getText() {
		return text;
	}

	
	
}
