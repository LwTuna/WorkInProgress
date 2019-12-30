package Client.server;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import Client.Logger;
import Client.game.states.State;

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
			
			if(State.getCurrentState() == null) {
				Logger.info("State is null");
			}else {
				State.getCurrentState().onServerMessage(object);
			}
		}catch(JSONException ex) {
			Logger.err(ex);
		}
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		if(State.getCurrentState() == null) {
			Logger.info("State is null");
		}else {
			State.getCurrentState().onDisconnect();
		}
	}

	@Override
	public void onError(Exception ex) {
		Logger.info("Error "+ex.getMessage());
	}

	
}
