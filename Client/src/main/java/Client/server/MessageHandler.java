package Client.server;

import org.json.JSONObject;

public interface MessageHandler {

	public void handle(JSONObject object);
	
}
