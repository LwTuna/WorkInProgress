package Client.game.engine.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import Client.game.engine.render.Texture;

public class GuiButton extends GuiElement{

    private Texture[] texture;
    
    private List<OnClickListener> listeners;
    
    public GuiButton(Texture[] texture, Vector2f position, Vector2f scale) {
	super( position, scale);
	if(texture.length != 3) throw new IllegalArgumentException("Texture[] length must be 3");
	this.texture = texture;
	listeners = new ArrayList<>();
	
    }

    @Override
    public void onClick() {
	for(OnClickListener listener:listeners) {
	    listener.onClick(new OnClickEvent());
	}
    }

    public void render(GuiShader shader) {
	
	shader.bind();
	
	if(isHovering()) {
	    if(isPressed()) {
		texture[2].bind(0);
	    }else {
		texture[1].bind(0);
	    }
	}else {
	    texture[0].bind(0);
	}
	Matrix4f matrix = GuiManager.createTransformationMatrix(position, scale);
	shader.setUniform("transformationMatrix", matrix);
	model.render();
    }
    
    
    public void add(OnClickListener listener) {
	listeners.add(listener);
    }
    
    public void remove(OnClickListener listener) {
	listeners.remove(listener);
    }
    
    public void clearOnClickListeners() {
	listeners.clear();
    }
}
