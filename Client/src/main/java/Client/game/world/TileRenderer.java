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
	
	public void renderTile(Tile tile,int x,int y,Shader shader,Matrix4f world,Camera camera	) {
		shader.bind();
		if(tile != null)
		if(tileTextures.containsKey(tile.getTexture())) {
			tileTextures.get(tile.getTexture()).bind(0);
		}
		Matrix4f tilePos = new Matrix4f().translate(new Vector3f(x*2,y*2,0));
		Matrix4f target = new Matrix4f();
		
		camera.getProjection().mul(world,target);
		target.mul(tilePos);
		
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);
		
		model.render();
		
	}
	
	
}
