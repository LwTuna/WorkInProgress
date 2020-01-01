package Client.game.states;

import java.awt.Graphics;
import java.util.HashMap;

import org.json.JSONObject;

import Client.App;
import Client.Logger;
import Client.game.ui.UiManager;
import Client.game.ui.elements.UiButton;
import Client.server.MessageHandler;

public class MenuState extends State{

	private HashMap<String,MessageHandler> handlers = new HashMap<>();
	
	
	
	public MenuState(App app) {
		super(app);
		handlers.put("loginResponse", this::handleLoginResponse);
	}

	@Override
	protected void onOpen() {
		manager.add(new UiButton(app, 0.1, 0.1, 0.2, 0.1, (app,uiEle)-> {sendLogin("testUser", "testPassword");}));
		
	}
	
	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawString("MenuState", convertX(0.5d), convertY(0.5d));
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
			State.setCurrentState(app.getGameState(),app);
		}
		
	}



	@Override
	public void onDisconnect() {
	}

	

}
