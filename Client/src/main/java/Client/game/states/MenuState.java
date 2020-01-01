package Client.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import org.json.JSONObject;

import Client.App;
import Client.Logger;
import Client.game.ui.UiManager;
import Client.game.ui.elements.TextButton;
import Client.game.ui.elements.TextInput;
import Client.game.ui.elements.UiButton;
import Client.server.MessageHandler;

public class MenuState extends State{

	private HashMap<String,MessageHandler> handlers = new HashMap<>();
	
	private TextInput userIn,passIn;
	
	public MenuState(App app) {
		super(app);
		handlers.put("loginResponse", this::handleLoginResponse);
	}

	@Override
	protected void onOpen() {
		userIn = new TextInput(app, 0.4, 0.4, 0.2, 0.1);
		passIn = new TextInput(app, 0.4, 0.55, 0.2, 0.1);
		manager.add(userIn);
		manager.add(passIn);
		manager.add(new TextButton(app, 0.5, 0.7, 0.2, 0.1, (app,uiEle)-> {sendLogin(userIn.getText(), passIn.getText());},"Login",new Color(100, 0, 0),new Color(120, 0, 0),new Color(255, 255, 255)));
		
	}
	
	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g) {
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
