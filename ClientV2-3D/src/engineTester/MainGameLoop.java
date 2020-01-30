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

        MasterRenderer renderer = new MasterRenderer();

        ModelData data = OBJLoader.loadOBJ("stone");
        RawModel model = loader.loadToVAO(data.getVertices(),data.getTextureCoords(),data.getNormals(),data.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("stone"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel,new Transform(new Vector3f(0,0,-10),new Vector3f(0,180,0),1));
		entity.getModel().getTexture().setShineDamper(10);
        entity.getModel().getTexture().setReflectivity(0.1f);
        Light sun = new Light(new Vector3f(0,5,-5),new Vector3f(1,1,1));
		Camera camera = new Camera();
        while (!Display.isCloseRequested()) {
            camera.move();

            renderer.processEntity(entity);

            renderer.render(sun,camera);

            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }

}
