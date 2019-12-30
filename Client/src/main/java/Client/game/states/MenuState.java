package Client.game.states;

import java.awt.Graphics;
import java.util.HashMap;

import org.json.JSONObject;

import Client.App;
import Client.server.MessageHandler;

public class MenuState extends State{

	private HashMap<String,MessageHandler> handlers = new HashMap<>();
	
	public MenuState(App app) {
		super(app);
		handlers.put("loginResponse", this::handleLoginResponse);
	}

	
	
	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		
	}

	

	@Override
	public void onServerMessage(JSONObject object) {
		MessageHandler hand = handlers.get(object.getString("key"));
		hand.handle(object);
		
	}



	private void handleLoginResponse(JSONObject object) {
		if(object.getBoolean("success")) {
			State.setCurrentState(app.getGameState());
		}
		
	}



	@Override
	public void onDisconnect() {
		//TODO
		
	}

}
