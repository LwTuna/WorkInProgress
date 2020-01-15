package Client.server;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import Client.Logger;

public class ServerConnection extends WebSocketClient{

	
	
	public ServerConnection(URI serverUri) {
		super(serverUri);
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		Logger.info("Opened");
	}

	@Override
	public void onMessage(String message) {
		try {
			JSONObject object = new JSONObject(message);
			
			
		}catch(JSONException ex) {
			Logger.err(ex);
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		
	}

	@Override
	public void onError(Exception ex) {
		Logger.info("Error "+ex.getMessage());
	}

	
}
