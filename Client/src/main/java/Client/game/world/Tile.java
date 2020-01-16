package Client.game.world;

import java.util.HashMap;
import java.util.Map;

public class Tile {

	public static Map<String,Tile> tiles = new HashMap<String,Tile>();
	
	private static final Tile air = new Tile((byte)0,(byte) 0, "air"); 
	
	
	
	private byte id,meta;
	private String texture;
	public Tile(byte id, byte meta, String texture) {
		
		this.id = id;
		this.meta = meta;
		this.texture = texture;
		String key = id+":"+meta;
		if(tiles.get(key) == null) {
			throw new IllegalArgumentException("Tile "+key+" is already initilized");
		}
		tiles.put(key, this);
		
	}
	public String getTexture() {
		return texture;
	}
	
	
}
