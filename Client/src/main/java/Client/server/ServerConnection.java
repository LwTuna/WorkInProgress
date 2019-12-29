package Client.server;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

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
		Logger.info("Message : "+message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		Logger.info("Closed "+reason);
	}

	@Override
	public void onError(Exception ex) {
		Logger.info("Error "+ex.getMessage());
	}

}
