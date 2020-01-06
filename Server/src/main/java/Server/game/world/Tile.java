package Server.game.world;

public class Tile {

	private int id;
	private int metaId;
	private String texturePath;
	public Tile(int id, int metaId, String texturePath) {
		this.id = id;
		this.metaId = metaId;
		this.texturePath = texturePath;
	}
	public int getId() {
		return id;
	}
	public int getMetaId() {
		return metaId;
	}
	public String getTexturePath() {
		return texturePath;
	}
	
	
	public static Tile getTile(int id,int meta) {
		return null;
	}
	
}
