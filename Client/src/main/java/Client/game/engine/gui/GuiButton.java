package Client.game.engine.gui;

import org.joml.Matrix4f;
import org.joml.Vector2f;

import Client.game.engine.render.Texture;

public class GuiButton extends GuiElement{

    private Texture[] texture;
    
    
    public GuiButton(Texture[] texture, Vector2f position, Vector2f scale) {
	super( position, scale);
	this.texture = texture;
    }

    @Override
    public void onClick() {
    }

    public void render(GuiShader shader) {
	
	shader.bind();
	if(isHovering()) {
	    texture[1].bind(0);
	}else {
	    texture[0].bind(0);
	}
	Matrix4f matrix = GuiManager.createTransformationMatrix(position, scale);
	shader.setUniform("transformationMatrix", matrix);
	model.render();
    }
    
}
