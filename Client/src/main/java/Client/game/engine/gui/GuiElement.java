package Client.game.engine.gui;

import org.joml.Vector2f;

import Client.game.assets.Assets;
import Client.game.engine.render.Model;

public abstract class GuiElement {

    protected Vector2f position,scale;
    protected Model model;
    
    protected boolean pressed = false;
    
    private boolean hovering = false;
    
    public GuiElement( Vector2f position, Vector2f scale) {
	this.position = position;
	this.scale = scale;
	this.model = Assets.model;
    }

    
    public abstract void onClick();
    
    
    
    public abstract void render(GuiShader shader);

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }


    public boolean isHovering() {
        return hovering;
    }


    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }


    protected BoundingBox getBounding() {
	return new BoundingBox(
		position.x - (scale.x),
		-position.y - (scale.y),
		scale.x*2,
		scale.y*2
	);
	
    }


    public boolean isPressed() {
        return pressed;
    }


    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
    
    
    
    
}
