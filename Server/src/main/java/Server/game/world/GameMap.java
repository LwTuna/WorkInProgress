package Server.game.world;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONObject;

public class GameMap {

	private static final int SPAWN_CHUNKS_IN_EACH_DIRECTION = 4;
	
	private static final String folderPath =  "res/saves/maps";
	
	private HashMap<String,Chunk> chunks = new HashMap<>();
	
	
	
	
	/**
	 * Use for Creating new Map
	 * 
	 */
	public GameMap() {
		for(int x=-SPAWN_CHUNKS_IN_EACH_DIRECTION;x<=4;x++) {
			for(int y=-SPAWN_CHUNKS_IN_EACH_DIRECTION;y<=4;y++) {
				Vector2i chunkCoords = new Vector2i(x, y);
				chunks.put(chunkCoords.toString(), new Chunk(chunkCoords));
			}
		}
	}

	/**
	 * Use for Loading Map from File
	 * 
	 */
	public GameMap(String name) {
		String mapPath = folderPath + "/"+name;
		File folder = new File(mapPath);
		for(String fp:folder.list()) {
			chunks.put(fp, new Chunk(mapPath,new Vector2i(fp)));
		}
	}
	public void save(String name) throws IOException {
		String mapPath = folderPath + "/"+name;
		for(Entry<String,Chunk> chunk:chunks.entrySet()) {
			chunk.getValue().save(mapPath+"/"+chunk.getKey());
		}
	}
	
	
	
	
}
