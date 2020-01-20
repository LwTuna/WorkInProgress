package Client.server;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import Client.App;
import Client.Logger;

public class ServerConnection extends WebSocketClient{

	private App app;
	
	public ServerConnection(URI serverUri,App app) {
		super(serverUri);
		this.app = app;
	}

	@Override
	public void onOpen(ServerHandshake handshakedata) {
		Logger.info("Opened");
		
	}

	@Override
	public void onMessage(String message) {
		try {
			JSONObject object = new JSONObject(message);
			
			app.onMessage(object);
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
