package Server.game.world;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Server.game.enteties.Player;
import Server.game.world.saves.SaveManager;

public class Chunk {

	public static final int DEFAULT_CHUNK_SIZE = 64;
	
	private Vector2i position;
	
	private List<TileLayer> layers;
	
	private List<Player> players;
	
	

	public Chunk(Vector2i position) {
		this.position = position;
		players = new ArrayList<>();
		layers = new ArrayList<>();
		layers.add(new TileLayer());
	}
	
	
	public Chunk(String mapPath,Vector2i position) {
		try {
			JSONObject info=SaveManager.from(SaveManager.load(mapPath+"/"+position.toString()+"/chunk.txt"));
			this.position = position;
			int layerCount  = info.getInt("layers");
			players = new ArrayList<>();
			layers = new ArrayList<>();
			for(int i=0;i<layerCount ;i++) {
				layers.add(new TileLayer(mapPath+"/"+position.toString()+"/layers/"+String.valueOf(i)+".txt"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	public List<TileLayer> getLayers() {
	    return layers;
	}


	


	public void addPlayer(Player p) {
		players.remove(p);
	}
	
	public boolean remove(Player p) {
		return players.remove(p);
	}
	
	public void createLayer() {
		layers.add(new TileLayer());
	}
	
	public void save(String path) throws IOException {
		for(int i=0;i<layers.size();i++) {
			layers.get(i).save(path+"/layers/"+String.valueOf(i)+".txt");
		}
		JSONObject object = new JSONObject();
		object.put("layers", layers.size());
		object.put("position", position.toString());
		SaveManager.save(path+"/chunk.txt", object.toString());
	}
	
	public Vector2i getPosition() {
		return position;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	
}
