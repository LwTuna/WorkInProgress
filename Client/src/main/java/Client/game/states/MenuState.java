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

	
	boolean sendLogin=false;
	@Override
	public void tick() {
		if(!sendLogin) {
			sendLogin("TestUsername", "TestPassword");
			sendLogin = true;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawString("GameState", convertX(0.5d), convertY(0.5d));
	}

	
	private void sendLogin(String username,String password) {
		JSONObject object = new JSONObject();
		object.put("key", "login");
		object.put("username", username);
		object.put("password", password);
		app.send(object);
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
