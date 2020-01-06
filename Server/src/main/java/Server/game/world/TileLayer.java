package Server.game.world;

import java.io.IOException;
import java.util.List;

import Server.game.world.saves.SaveManager;

public class TileLayer {

	private Tile[][] tiles;
	
	public TileLayer() {
		tiles = new Tile[Chunk.DEFAULT_CHUNK_SIZE][Chunk.DEFAULT_CHUNK_SIZE];
	}
	
	
	public TileLayer(String string) throws IOException {
		List<String> lines = SaveManager.load(string);
		tiles = new Tile[Chunk.DEFAULT_CHUNK_SIZE][Chunk.DEFAULT_CHUNK_SIZE];
		for(int y = 0;y<lines.size();y++) {
			String line = lines.get(0);
			if(line.isBlank()) continue;
			String[] split = line.split(" ");
			for(int x=0;x<split.length;x++) {
				String token = split[x];
				String[] spli = token.split(":");
				tiles[x][y] = Tile.getTile(Integer.parseInt(spli[0]), Integer.parseInt(spli[1]));
			}
		}
		
	}


	public void save(String path) throws IOException {
		StringBuilder builder = new StringBuilder();
		for(int x=0;x<Chunk.DEFAULT_CHUNK_SIZE;x++) {
			for(int y=0;y<Chunk.DEFAULT_CHUNK_SIZE;y++) {
				String token = tiles[x][y].getId()+":"+ tiles[x][y].getMetaId();
				builder.append(token+" ");
			}
			builder.append("\n");
		}
		SaveManager.save(path, builder.toString());
	}
	
	
}
