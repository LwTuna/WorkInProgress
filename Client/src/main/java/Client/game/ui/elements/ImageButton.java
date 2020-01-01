package Client.game.ui.elements;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Client.App;
import Client.game.states.State;
import Client.game.ui.elements.interfaces.UiOnClickListener;

public class ImageButton extends UiButton{

	private BufferedImage image;
	
	public ImageButton(App app, double x, double y, double width, double height, UiOnClickListener listener,BufferedImage image) {
		super(app, x, y, width, height, listener);
		this.image = image;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, State.getCurrentState().convertX(x), State.getCurrentState().convertY(y), State.getCurrentState().convertX(width), State.getCurrentState().convertY(height),null);
	}

}
