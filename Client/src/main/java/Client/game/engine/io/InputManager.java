package Client.game.engine.io;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {

	private long window;
	
	private boolean[] keys;

	public InputManager(long window) {
		this.window = window;
		keys = new boolean[348];
		for(int i=0;i<keys.length;i++) {
			keys[i] = false;
		}
	}
	
	
	public boolean isKeyDown(int key) {
	    return glfwGetKey(window, key) == 1;
	}
	
	public boolean isMouseDown(int button) {
	    return glfwGetMouseButton(window, button) == 1;
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
	}
	
}
