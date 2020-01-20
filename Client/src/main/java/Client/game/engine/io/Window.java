package Client.game.engine.io;

import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Window {

	
	
	
	private long window;
	private int width,height;
	
	private boolean fullScreen;
	
	private InputManager input;
	
	private boolean hasResized;
	private GLFWWindowSizeCallback windowSizeCallback;
	
	public Window() {
		setWidth(2060);
		setHeight(1040);
		setFullScreen(false);
		
		hasResized = false;
		
		
	}
	
	public static void setCallbacks() {
		glfwSetErrorCallback(
				(errorCode,description)->{//invoke(error,description)
					throw new IllegalArgumentException(GLFWErrorCallback.getDescription(description));
				}
		);
	}
	
	private void setLocalCallbacks() {
	    windowSizeCallback = new GLFWWindowSizeCallback() {
	        
	        @Override
	        public void invoke(long window, int awidth, int aheight) {
	            width = awidth;
	            height = aheight;
	            hasResized = true;
	        }
	    };
	    
	    glfwSetWindowSizeCallback(window, windowSizeCallback);
	}

	public void createWindow(String title){
		window = glfwCreateWindow(
				width,
				height,
				title,
				fullScreen ? glfwGetPrimaryMonitor() : 0,
				0);
		
		if(window == 0) {
			throw new IllegalStateException("Failed to create Window");
		}
		if(!fullScreen) {
			GLFWVidMode vid = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, (vid.width()-width)/2, (vid.height()-height)/2);
		}
		
		glfwShowWindow(window);
		
		glfwMakeContextCurrent(window);
		
		input = new InputManager(window);
		
		setLocalCallbacks();
	}
	
	public void update() {
	    	hasResized = false;
	    
		input.update();
		glfwPollEvents();
	}
	
	public long getWindow() {
		return window;
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
	
	public boolean shouldClose() {
		return glfwWindowShouldClose(window);
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setSize(int width,int height) {
		this.width = width;
		this.height = height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fullScreen) {
		this.fullScreen = fullScreen;
	}
	public InputManager getInput() {
	    return input;
	}
	
	public boolean hasResized() {
	    return hasResized;
	}
		
	
}
