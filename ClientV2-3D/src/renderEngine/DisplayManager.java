package renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;

public class DisplayManager {

    
    private static final int FPS_CAP = 120;
    
    public static void createDisplay(int width,int height,String title) {
	
	ContextAttribs attribs = new ContextAttribs(3,2);
	attribs.withForwardCompatible(true);
	attribs.withProfileCore(true);
	try {
	    Display.setDisplayMode(new DisplayMode(width, height));
	    Display.setTitle(title);
	    
	    Display.setResizable(true);
	    Display.create(new PixelFormat(),attribs);
	} catch (LWJGLException e) {
	    e.printStackTrace();
	}
	
	glViewport(0, 0, width, height);
	
    }
    public static void updateDisplay() {
	
	Display.sync(FPS_CAP);
	Display.update();
    }
    public static void closeDisplay() {
	
	Display.destroy();
    }
    
}
