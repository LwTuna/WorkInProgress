package Server.net;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import Server.App;
import Server.Logger;
import Server.game.Game;

public class Server extends WebSocketServer{

	private App app;
	
	public Server(InetSocketAddress address, App game) {
		super(address);
		this.app = game;
	}
	
	
	@Override
	public void onStart() {
		Logger.info("Started");
	}
	
	
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		Logger.info("New User Connected ");
		
	}

	
	@Override
	public void onMessage(WebSocket conn, String message) {
		app.getGame().handle(conn, message);
		
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		Logger.info("Error : "+ex.getMessage());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		Logger.info("Closed");
	}

	
	
	
	
	
}
