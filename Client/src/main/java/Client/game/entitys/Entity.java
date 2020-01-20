package Client.game.entitys;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import Client.game.assets.Assets;
import Client.game.engine.io.Window;
import Client.game.engine.render.Camera;
import Client.game.engine.render.Model;
import Client.game.engine.render.Texture;
import Client.game.engine.shaders.Shader;
import Client.game.world.World;

public abstract class Entity{

    private Texture texture;
    
    protected Transform transform;
    
    public Entity(Texture texture,Transform transform) {
	this.transform = transform;
	this.texture = texture;
    }
    
    
    public void move(Vector2f direction) {
	transform.pos.add(new Vector3f(direction,0));
    }
    
    public abstract void update(float delta, Window window, Camera camera, World world) ;
    
    public void render(Shader shader,Camera camera,World world) {
	Matrix4f target = camera.getProjection();
	target.mul(world.getWorldMatrix());
	shader.bind();
	shader.setUniform("sampler", 0);
	shader.setUniform("projection", transform.getProjection(target));
	texture.bind(0);
	Assets.model.render();
    }
    
    
    
}
