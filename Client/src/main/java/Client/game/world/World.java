package Client.game.world;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import Client.game.engine.render.Camera;
import Client.game.engine.shaders.Shader;

public class World {

    //Change to chunk later
    private String[][] tiles;
    
    private int width;
    private int height;
    private float scale;
    
    private Matrix4f world;
    
    public World() {
	width = 16;
	height = 16;
	scale = 64f;
	
	tiles  = new String[width][height];
	
	for(int x=0;x<width;x++) {
	    for(int y=0;y<height;y++) {
		tiles[x][y] = "0:"+Math.round(Math.random());
	    }
	}
	tiles[1][2] = "penis";
	world = new Matrix4f().setTranslation(new Vector3f(0));
	world.scale(scale);
    }
    
    
    public void render(TileRenderer render,Shader shader,Camera camera) {
	for(int x=0;x<width;x++) {
	    for(int y=0;y<height;y++) {
		Tile tile = getTile(x, y);
		  render.renderTile(tile, x, -y, shader, world, camera);  
	    } 
	}
	
    }
    public Tile getTile(int x,int y) {
	try {
	    return Tile.tiles.get(tiles[x][y]);
	}catch(Exception e) {
	    return Tile.tiles.get("0:0");
	}
    }
    public void setTile(Tile tile,int x,int y) {
	tiles[x][y] = tile.getId();
    }
}
