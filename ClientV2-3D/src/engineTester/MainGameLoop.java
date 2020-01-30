package engineTester;

import entities.Entity;
import entities.Light;
import models.loading.Loader;
import models.loading.ModelData;
import models.loading.OBJLoader;
import org.lwjgl.opengl.Display;

import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import shaders.StaticShader;
import textures.ModelTexture;
import toolbox.Transform;

public class MainGameLoop {


    public static void main(String[] args) {
        DisplayManager.createDisplay(1280, 720, "Testo new Projekto");

        Loader loader = new Loader();

        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);


        ModelData data = OBJLoader.loadOBJ("stone");
        RawModel model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),data.getNormals(),data.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("stone"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel,new Transform(new Vector3f(0,0,-10),new Vector3f(0,180,0),1));
        Light light = new Light(new Vector3f(0,1,-5),new Vector3f(1,1,1));
		Camera camera = new Camera();
        while (!Display.isCloseRequested()) {

            renderer.prepare();
            camera.move();

            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            entity.increaseRotation(0,-0.4f,0);
            renderer.render(entity,shader);

            shader.stop();
            DisplayManager.updateDisplay();
        }
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }

}
