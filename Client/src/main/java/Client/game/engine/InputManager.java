package Client.game.engine;

import static org.lwjgl.glfw.GLFW.*;

public class InputManager {

	private long window;

	public InputManager(long window) {
		this.window = window;
	}
	
	
	public boolean isKeyDown(int key) {
	    return glfwGetKey(window, key) == 1;
	}
	
	public boolean isMouseDown(int button) {
	    return glfwGetMouseButton(window, button) == 1;
	}
	
}
