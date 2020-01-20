package Client.game.engine.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

import Client.game.assets.Assets;
import Client.game.engine.io.Window;
import Client.game.engine.render.Model;

public class GuiManager {

    

    private List<GuiElement> guis;
    
    
    private GuiShader shader;
    
    public GuiManager() {
	shader = new GuiShader();
	guis = new ArrayList<GuiElement>();
    }
    public void render() {
	for(GuiElement gui:guis) {
	    gui.render(shader);
	}
    }
    
    public void update(Window window) {
	 double xR = window.getInput().getMouseX();
	 xR -= window.getWidth()/2;
	 xR /= window.getWidth()/2;
	 double yR = window.getInput().getMouseY();
	 yR -= window.getHeight()/2;
	 yR /= window.getHeight()/2;
	for(GuiElement gui:guis) {
	    
	   BoundingBox r = gui.getBounding();
	   gui.setHovering(r.contains(xR, yR));
	}
    }
    
    public List<GuiElement> getGuis() {
        return guis;
    }
    public void setGuis(List<GuiElement> guis) {
        this.guis = guis;
    }
    public static Matrix4f createTransformationMatrix(Vector2f translation, Vector2f scale) {
	Matrix4f matrix = new Matrix4f();
	matrix.identity();
	matrix.translate(new Vector3f(translation,0), matrix);
	matrix.scale(new Vector3f(scale.x, scale.y, 1f), matrix);
	return matrix;
    }
    
}
