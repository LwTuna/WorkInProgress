package Server.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.java_websocket.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import Server.App;
import Server.Logger;
import Server.game.world.Chunk;
import Server.game.world.GameMap;

public class Game implements Runnable{

	private HashMap<String,MessageHandler> handlers = new HashMap<>();
	
	private App app;
	
	private Thread thread;
	private boolean running = false;
	
	private Map<String,GameMap> maps = new HashMap<>();
	
	public Game(App app) {
		handlers.put("login",this::login);
		handlers.put("getWorld", this::getWorld);
		this.app = app;
		maps.put("test", new GameMap("test"));
		
	}
	
	public void login(WebSocket conn,JSONObject object) {
		String username = object.getString("username");
		String password = object.getString("password");
		
		JSONObject response = new JSONObject();
		response.put("key", "loginResponse");
		response.put("success", true);
		conn.send(response.toString());
	}
	
	public void getWorld(WebSocket conn,JSONObject object) {
	    JSONObject response = new JSONObject();
	    response.put("key", "world");
	    response.put("width", Chunk.DEFAULT_CHUNK_SIZE);
	    response.put("height", Chunk.DEFAULT_CHUNK_SIZE);
	    response.put("world", maps.get("test").getChunks().get("0;0").getLayers().get(0).toJSONArray());
	    conn.send(response.toString());
	}
	
	@Override
	public void run() {
		while(running) {
			
		}
	}
	
	public void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public void stop() throws InterruptedException {
		if(!running) return;
		running = false;
		thread.join();
	}
	public void handle(WebSocket conn,String message) {
		try {
			
			JSONObject obect = new JSONObject(message);
			handlers.getOrDefault(obect.getString("key"),
					(c,mess)->{
						Logger.info("Cannot handle object because key="+obect.getString("key")+" was not found");
						}).handle(conn, new JSONObject(message));;
		}catch(JSONException x) {
			Logger.err(x.getMessage());
		}
	}
	
	public void createMap(String name) {
		maps.put(name, new GameMap());
	}
	
	public void save() throws IOException {
		for(Entry<String,GameMap> map:maps.entrySet()) {
			map.getValue().save(map.getKey());
		}
	}

	
}
