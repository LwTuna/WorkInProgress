package Server.net;

import java.net.InetSocketAddress;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.JSONObject;

import Server.Logger;

public class Server extends WebSocketServer{

	
	public Server(InetSocketAddress address) {
		super(address);
		
	}
	
	public void broadcast(JSONObject text) {
		super.broadcast(text.toString());
	}
	
	public void send(JSONObject object,WebSocket conn) {
		conn.send(object.toString());
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
		Logger.info("Message Recieved : "+message);
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
