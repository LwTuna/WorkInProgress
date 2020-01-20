package Client.game.engine.io;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class InputManager {

	private long window;
	
	private boolean[] keys,mouse;
	private double mouseX = 0,mouseY = 0;

	public InputManager(long window) {
		this.window = window;
		keys = new boolean[348];
		mouse = new boolean[3];
		for(int i=0;i<mouse.length;i++) {
		    mouse[i] = false;
		}
		for(int i=0;i<keys.length;i++) {
			keys[i] = false;
		}
		glfwSetCursorPosCallback(window, (win, xpos, ypos)->{
		    mouseX = xpos;
		    mouseY = ypos;
		});
	}
	
	public double getMouseX() {
	    return mouseX;
	}
	public double getMouseY() {
	    return mouseY;
	}
	public boolean isKeyDown(int key) {
	    return glfwGetKey(window, key) == 1;
	}
	
	public boolean isMouseDown(int button) {
	    return glfwGetMouseButton(window, button) == 1;
	}
	
	public boolean mouseReleased(int button) {
	    return !isMouseDown(button) && mouse[button];
	}
	
	public boolean isKeyPressed(int key) {
		return isKeyDown(key)&&!keys[key];
	}
	public boolean isKeyReleased(int key) {
		return !isKeyDown(key)&&keys[key];
	}
	public void update() {
		for(int i=32;i<keys.length;i++) {
			keys[i] = isKeyDown(i);
		}
		
		for(int i=0;i<mouse.length;i++) {
		    	mouse[i] = isMouseDown(i);
		}
	}
	
}
