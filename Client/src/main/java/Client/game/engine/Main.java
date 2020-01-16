package Client.game.engine;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwGetKey;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL;

import Client.Logger;
import Client.game.engine.io.Timer;
import Client.game.engine.io.Window;
import Client.game.engine.render.Camera;
import Client.game.engine.render.Model;
import Client.game.engine.render.Texture;
import Client.game.engine.shaders.Shader;

public class Main implements Runnable{

	
	
	
	private Window window;
	
	private Shader shader;
	
	private Camera camera;
	private final int TPS = 60;
	
	Matrix4f target,projection,scale;
	
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
		camera.add(new Vector3f(-100,0,10));
		shader = new Shader("shader");
		
		
		
		 projection = new Matrix4f().ortho2D(-window.getWidth()/2, window.getWidth()/2, -window.getHeight()/2, window.getHeight()/2);
		 scale = new Matrix4f().scale(3);
		 target = new Matrix4f();
		 
		 projection.mul(scale,target);
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
				unprocessed -= frameCap;
				canRender = true;
				
				target = scale;
				if(window.getInput().isKeyPressed(GLFW_KEY_ESCAPE)) {
					glfwSetWindowShouldClose(window.getWindow(), true);
				}
				window.update();
				
				if(frameTime >= 1) {
					frameTime =0;
					System.out.println(" FPS: "+frames);
					frames = 0;
				}
			}
			
			if(canRender) {
				glClear(GL_COLOR_BUFFER_BIT);
				
				
				shader.bind();
				shader.setUniform("sampler", 0);
				shader.setUniform("projection", camera.getProjection().mul(target));
				//RENDER STUFF
				
				
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
	

	
}
