/*
 * T+ file was generated by the Gradle 'init' task.
 */
package Client;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONObject;

import Client.game.displays.Display;
import Client.game.displays.input.KeyManager;
import Client.game.displays.input.MouseManager;
import Client.game.states.GameState;
import Client.game.states.MenuState;
import Client.game.states.State;
import Client.server.ServerConnection;

public class App implements Runnable{
  

	private static final String serverUri = "ws://localhost:8888";
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Thread thread;
	private boolean running = false;
	
	private Display display;
	
	private int width,height;
	
	private State menuState;
	private State gameState;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private ServerConnection connection;
	
	public App() throws URISyntaxException {
		connection= new ServerConnection(new URI(serverUri));
		connection.connect();
		
		display = new Display("", 800, 600, false);
		keyManager = new KeyManager();
		display.addKeyManager(keyManager);
		mouseManager = new MouseManager();
		display.addMouseManager(mouseManager);
		width = display.getWidth();
		height = display.getHeight();
		
		menuState = new MenuState(this);
		gameState = new GameState(this);
		
		start();
	}
	
	private void tick() {
		if(State.getCurrentState() !=null) {
			keyManager.tick();
			mouseManager.tick();
			State.getManager().tick();
			State.getCurrentState().tick();
		}
	}
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		
		
		if(State.getCurrentState() !=null) {
			State.getManager().render(g);
			State.getCurrentState().render(g);
		}
		
		bs.show();
		g.dispose();
		
	}
	@Override
	public void run() {
		State.setCurrentState(menuState,this);
		final int tps = 60;
 		final double timePerTick = 1000000000  / tps;
 		double delta = 0;
 		long now;
 		long lastTime = System.nanoTime();
 		long timer = 0;
 		long rendered = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				delta--;
			}

			render();
			rendered++;
			if(timer >= 1000000000) {
				System.out.println("FPS : "+rendered);
				rendered = 0;
				timer = 0;
			}
			
		}
		stop();
	}
	public synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop() {
		
		if(!running) {
			return;
		}
		running = false;
			
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public void send(JSONObject object) {
		if(connection.isOpen()) {
			connection.send(object.toString());
		}else {
			Logger.info("Cannot send object because connection is not open!");
		}
		
	}
	
    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	
	
	
	public State getMenuState() {
		return menuState;
	}

	public State getGameState() {
		return gameState;
	}
	

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	/*********************************STATIC*********************/
	public static void main(String[] args) throws URISyntaxException {
       new App();
    }
    
	
	
}
