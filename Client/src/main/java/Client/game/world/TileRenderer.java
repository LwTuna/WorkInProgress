package Client.game.world;

import java.util.HashMap;
import java.util.Map.Entry;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import Client.game.engine.render.Camera;
import Client.game.engine.render.Model;
import Client.game.engine.render.Texture;
import Client.game.engine.shaders.Shader;

public class TileRenderer {

	private HashMap<String,Texture> tileTextures;
	
	private Model model;
	
	public TileRenderer() {
		tileTextures = new HashMap<String, Texture>();
		
		model = new Model(
				   new float[] {
						-1,1,0,
						1,1,0,
						1,-1,0,
						-1,-1,0
				}, new float[] {
						0,0,1,0,1,1,0,1
				}, new int[] {
						0,1,2,
						2,3,0
				});
		
		for(Entry<String, Tile> entry : Tile.tiles.entrySet()) {
			if(!tileTextures.containsKey(entry.getValue().getTexture())) {
				String tex = entry.getValue().getTexture();
				tileTextures.put(tex, new Texture(tex+".png"));
			}
		}
	}
	
	public void renderTile(String keyId,int x,int y,Shader shader,Matrix4f world,Camera camera	) {
		shader.bind();
		
		if(tileTextures.containsKey(Tile.tiles.get(keyId).getTexture())) {
			tileTextures.get(Tile.tiles.get(keyId).getTexture()).bind(0);
		}
		Matrix4f tilePos = new Matrix4f().translate(new Vector3f(x*2,y*2,0));
	}
	
	
}
