package Server.game;

import java.util.HashMap;

import org.java_websocket.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import Server.App;
import Server.Logger;

public class Game {

	private HashMap<String,MessageHandler> handlers = new HashMap<>();
	
	private App app;
	
	public Game(App app) {
		handlers.put("login", this::login);
		this.app = app;
	}
	
	public void login(WebSocket conn,JSONObject object) {
		String username = object.getString("username");
		String password = object.getString("password");
		
		JSONObject response = new JSONObject();
		response.put("key", "loginResponse");
		response.put("success", true);
		conn.send(response.toString());
	}
	
	
	
	public void handle(WebSocket conn,String message) {
		try {
			JSONObject obect = new JSONObject(message);
			handlers.getOrDefault(obect.getString("key"),
					(c,mess)->{
						Logger.info("Cannot handle object because key="+obect.getString("key")+" was not found");
						});
		}catch(JSONException x) {
			Logger.err(x.getMessage());
		}
	}
}
