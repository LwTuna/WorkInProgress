package Client.game;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.json.JSONArray;
import org.json.JSONObject;

import Client.App;
import Client.game.assets.Assets;
import Client.game.engine.WindowManager;
import Client.game.engine.gui.GuiManager;
import Client.game.engine.gui.GuiTextLine;
import Client.game.engine.gui.GuiButton;
import Client.game.engine.gui.GuiElement;
import Client.game.engine.io.Window;
import Client.game.engine.render.Camera;
import Client.game.engine.render.Texture;
import Client.game.engine.shaders.Shader;
import Client.game.world.TileRenderer;
import Client.game.world.World;
import Client.server.packets.Packet;

public class Game {

    private World world;
    
    
    private WindowManager manager;
    
    private TileRenderer renderer;
    private GuiManager guiManager;
    
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
	guiManager = new GuiManager();
	
	GuiButton button = new GuiButton(new Texture[] {new Texture("test.png"),new Texture("test2.png"),new Texture("test3.png")}, new Vector2f(0.75f), new Vector2f(0.25f));
	button.add((event)->{System.out.println("ITS A ME MARIO");});
	
	guiManager.getGuis().add(button);
	guiManager.getGuis().add(new GuiTextLine("Hallo!", new Vector2f(0.75f), new Vector2f(0.25f),  new Font("Arial", Font.PLAIN, 48)));
	if(offline) {
	    world = new World();
	    world.calculateView(manager.getWindow());
	}else {
	    getWorld();
	}
    }
    
    public void update(float delta, Window window, Camera camera) {
	if(world != null)
		world.update(delta, window, camera);
	guiManager.update(window);
	
    }
    public void render(Shader shader,Camera camera,Window window) {
	if(world != null) 
	    world.render(renderer, shader, camera);
	guiManager.render(window);
	
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
