package Client.game.engine;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

import Client.Logger;
import Client.game.Game;
import Client.game.engine.io.Timer;
import Client.game.engine.io.Window;
import Client.game.engine.render.Camera;
import Client.game.engine.shaders.Shader;

public class WindowManager implements Runnable{

	
	private Thread thread;
	
	private Window window;
	
	private Shader shader;
	
	private Camera camera;
	private final int TPS = 60;
	
	
	private Game game;
	
	//TODO change to diffrent file
	
	public WindowManager(Game game) {
	    this.game = game;
	}
	
	private void init() {
		Window.setCallbacks();
		
		if ( !glfwInit() ) {
			Logger.info("GLFW failed to initilize!");
			System.exit(1);
		}
			
		window = new Window();
		window.setFullScreen(false);
		window.createWindow("INSERTO TITLO HERO. I DONTO SPEAKO SPANISHO");
		
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		
		camera = new Camera(window.getWidth(), window.getHeight());
		camera.getPosition().sub(new Vector3f(700,-700,0));
		shader = new Shader("shader");
		
		game.init();
		 
	}

	
	private void loop() {
		double frameCap = 1d/(double)TPS;
		
		double frameTime = 0;
		int frames = 0;
		
		double time = Timer.getTime();
		double unprocessed =0;
		
		while(!window.shouldClose()) {
			boolean canRender = false;
			
			double time_2 = Timer.getTime();
			double passed = time_2 -time;
			unprocessed += passed;
			frameTime += passed;
			
			time = time_2;
			
			while(unprocessed >= frameCap) {
			    	if(window.hasResized()) {
			    	    
			    	    camera.setProjection(window.getWidth(), window.getHeight());
			    	    
			    	    glViewport(0,0,window.getWidth(),window.getHeight());
			    	}
			    
				unprocessed -= frameCap;
				canRender = true;
				
				if(window.getInput().isKeyPressed(GLFW_KEY_ESCAPE)) {
				    glfwSetWindowShouldClose(window.getWindow(), true);
				}
				
				game.update((float) frameCap,window,camera);
				window.update();
				
				if(frameTime >= 1) {
					frameTime =0;
					System.out.println(" FPS: "+frames);
					frames = 0;
				}
			}
			
			if(canRender) {
				glClear(GL_COLOR_BUFFER_BIT);
				
				game.render(shader,camera,window);
				
				window.swapBuffers();
				
				frames++;
			}
		}
	}	
	

		
	public void run() {
		init();
		loop();
		
		glfwTerminate();
	}
	

	public void start() {
	    thread = new Thread(this);
	    thread.start();
	}

	public Window getWindow() {
	    return window;
	}
}
