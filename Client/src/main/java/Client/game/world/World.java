package Client.game.world;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.json.JSONArray;

import Client.game.engine.io.Window;
import Client.game.engine.render.Camera;
import Client.game.engine.shaders.Shader;
import Client.game.entitys.Player;
import Client.game.entitys.Transform;

public class World {

    //Tiles Rendered
    private int viewX,viewY;
    
    //Change to chunk later
    private String[][] tiles;
    
    private int width;
    private int height;
    private float scale = 64f;
    
    private Matrix4f world;
    
    private Player player;
    
    /**
     * OFFLINE CONSTRUCTOR
     */
    public World() {
	this.width = 64;
	this.height = 64;
	tiles  = new String[width][height];
	
	for(int x=0;x<width;x++) {
	    for(int y=0;y<height;y++) {
		tiles[x][y] = "0:"+Math.round(Math.random());
	    }
	}
	
	world = new Matrix4f().setTranslation(new Vector3f(0));
	world.scale(scale);
	player = new Player(new Transform());
    }
    
    /**
     * ONLINE CONSTRUCTOR
     */
    public World(int width, int height, JSONArray rows) {
	this.width = width;
	this.height = height;
	tiles  = new String[width][height];
	
	for(int x=0;x<width;x++) {
	    for(int y=0;y<height;y++) {
		tiles[x][y] = rows.getJSONArray(y).getString(x);
	    }
	}
	world = new Matrix4f().setTranslation(new Vector3f(0));
	world.scale(scale);
	player = new Player(new Transform());
    }

    
    public void calculateView(Window window) {
	viewX = (int) (window.getWidth() / (scale*2)) +3;
	viewY = (int) (window.getHeight() / (scale*2)) +3;
    }
    
    public void update(float delta, Window window, Camera camera) {
	if(window.hasResized()) calculateView(window);
	player.update(delta, window, camera, this);
    }
    
    public void render(TileRenderer render,Shader shader,Camera camera) {	
	int posX = (int) (((int) camera.getPosition().x / (scale * 2)));
	int posY = (int) (((int) camera.getPosition().y  / (scale * 2)));
	
	for(int x = 0;x<viewX;x++) {
	    for(int y = 0;y<viewY;y++) {
		Tile t = getTile(x-posX-(viewX/2)+1, y+posY-(viewY/2));
		if(t != null) {
		    render.renderTile(t, x-posX-(viewX/2)+1, -y-posY+(viewY/2), shader, world, camera);
		}
	    }
	}
	
	player.render(shader, camera, this);
    }
    
    
    public Tile getTile(int x,int y) {
	try {
	    return Tile.tiles.get(tiles[x][y]);
	}catch(Exception e) {
	    return null;
	}
    }
    public void setTile(Tile tile,int x,int y) {
	tiles[x][y] = tile.getId();
    }
    
    public float getScale() {
	return scale;
    }
    
    public Matrix4f getWorldMatrix() {
	return world;
    }
}
