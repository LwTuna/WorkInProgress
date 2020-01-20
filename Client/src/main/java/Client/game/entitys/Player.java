package Client.game.entitys;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import Client.game.assets.Assets;
import Client.game.engine.io.Window;
import Client.game.engine.render.Camera;
import Client.game.engine.render.Texture;
import Client.game.world.World;

public class Player extends Entity{

    public Player(Transform transform) {
	super(Assets.playerTexture, transform);
    }
    
    
    @Override
    public void update(float delta, Window window, Camera camera, World world) {
        
        if(window.getInput().isKeyDown(GLFW.GLFW_KEY_A)) {
            move(new Vector2f(-5f * delta,0));
	}
	if(window.getInput().isKeyDown(GLFW.GLFW_KEY_D)) {
	    move(new Vector2f(5f* delta,0));
	}
	if(window.getInput().isKeyDown(GLFW.GLFW_KEY_W)) {
	    move(new Vector2f(0,5f* delta));
	}
	if(window.getInput().isKeyDown(GLFW.GLFW_KEY_S)) {
	    move(new Vector2f(0,-5f* delta));
	}
	camera.getPosition().lerp(transform.pos.mul(-world.getScale(), new Vector3f()),0.06f);
    }
    
}
