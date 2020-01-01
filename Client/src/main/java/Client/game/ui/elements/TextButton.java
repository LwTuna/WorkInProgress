package Client.game.ui.elements;

import java.awt.Color;
import java.awt.Graphics;

import Client.App;
import Client.game.states.State;
import Client.game.ui.elements.interfaces.UiOnClickListener;

public class TextButton extends UiButton{

	private String text;
	private Color background,textcolor,hoverColor;
	
	public TextButton(App app, double x, double y, double width, double height, UiOnClickListener listener,String text,Color background,Color hoverColor,Color textcolor) {
		super(app, x, y, width, height, listener);
		this.text = text;
		this.textcolor = textcolor;
		this.hoverColor = hoverColor;
		this.background = background;
	}

	@Override
	public void render(Graphics g) {
		if(hovering) {
			g.setColor(hoverColor);
		}else {
			g.setColor(background);
		}
		g.fillRect(State.getCurrentState().convertX(x), State.getCurrentState().convertY(y), State.getCurrentState().convertX(width), State.getCurrentState().convertY(height));
		g.setColor(textcolor);
		g.drawString(text, State.getCurrentState().convertX(x), State.getCurrentState().convertY(y)+(State.getCurrentState().convertY(height)/2));
	}

}
