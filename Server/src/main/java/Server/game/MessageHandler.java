package Server.game;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

public interface MessageHandler {

	public void handle(WebSocket conn,JSONObject object);
	
}
