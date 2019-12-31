package Client.game.states;

import java.awt.Graphics;

import org.json.JSONObject;

import Client.App;
import Client.Logger;

public class GameState extends State{

	public GameState(App app) {
		super(app);
	}

	@Override
	public void tick() {
		if(app.getMouseManager().isAleftPressed()) {
			Logger.info("left Pressed");
		}
		
		if(app.getMouseManager().isAleftClicked()) {
			Logger.info("left Clicked");
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.drawString("GameState", convertX(0.5d), convertY(0.5d));
		
	}

	@Override
	public void onServerMessage(JSONObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnect() {
		// TODO Auto-generated method stub
		
	}

}
