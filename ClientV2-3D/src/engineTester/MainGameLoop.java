package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class MainGameLoop {

    
    public static void main(String[] args) {
	DisplayManager.createDisplay(1280, 720, "Testo new Projekto");
	
	Loader loader = new Loader();
	Renderer renderer = new Renderer();
	RawModel model = loader.loadToVao(new float[] {
		-0.5f, 0.5f, 0f,
		    -0.5f, -0.5f, 0f,
		    0.5f, -0.5f, 0f,
		    0.5f, -0.5f, 0f,
		    0.5f, 0.5f, 0f,
		    -0.5f, 0.5f, 0f});
	while(!Display.isCloseRequested()) {
	    renderer.prepare();
	    
	    renderer.render(model);
	    DisplayManager.updateDisplay();
	}
	
	loader.cleanUp();
	DisplayManager.closeDisplay();
    }
    
}
