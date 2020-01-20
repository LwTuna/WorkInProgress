package Client.game;

import org.json.JSONArray;
import org.json.JSONObject;

import Client.App;
import Client.game.engine.WindowManager;
import Client.game.engine.io.Window;
import Client.game.engine.render.Assets;
import Client.game.engine.render.Camera;
import Client.game.engine.shaders.Shader;
import Client.game.entitys.Entity;
import Client.game.entitys.Player;
import Client.game.entitys.Transform;
import Client.game.world.TileRenderer;
import Client.game.world.World;
import Client.server.packets.Packet;

public class Game {

    private World world;
    
    private WindowManager manager;
    
    private TileRenderer renderer;
    
    private App app;
    
    private boolean offline;
    
    public Game(App app, boolean offline) {
	this.app = app;
	this.offline = offline;
	manager = new WindowManager(this);
	manager.start();
	
    }
    public void init() {
		Assets.init();
		renderer = new TileRenderer();
		if(offline) {
		    world = new World();
		    world.calculateView(manager.getWindow());
		}else {
		    getWorld();
		}
    }
    
    public void update(float delta, Window window, Camera camera) {
	if(world == null) return;
		world.update(delta, window, camera);
    }
    public void render(Shader shader,Camera camera,Window window) {
	if(world == null) return;  //TODO DIFFRENT STATES
	    world.render(renderer, shader, camera);
	
    }
    public void onMessage(JSONObject object) {
	if(object.getString("key").equalsIgnoreCase("world")) {
	    int width = object.getInt("width");
	    int height = object.getInt("height");
	    JSONArray rows = object.getJSONArray("world");
	    world = new World(width,height,rows);
	    world.calculateView(manager.getWindow());
	}
    }
    private void getWorld() {
	app.send(new Packet("getWorld").toJSON());
    }
    
    
}
