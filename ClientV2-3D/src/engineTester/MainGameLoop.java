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
import terrain.Terrain;
import textures.ModelTexture;
import toolbox.Transform;

public class MainGameLoop {


    public static void main(String[] args) {
        DisplayManager.createDisplay(1280, 720, "Testo new Projekto");

        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();

        Light sun = new Light(new Vector3f(0, 100, 0), new Vector3f(1, 1, 1));
        Camera camera = new Camera();
        camera.getPosition().y += 2;
        //Stone Model
        ModelData data = OBJLoader.loadOBJ("stone");
        RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
        ModelTexture texture = new ModelTexture(loader.loadTexture("stone"));
        TexturedModel texturedModel = new TexturedModel(model, texture);
        Entity entity = new Entity(texturedModel, new Transform(new Vector3f(0, 2, -10), new Vector3f(0, 180, 0), 1));
        entity.getModel().getTexture().setShineDamper(10);
        entity.getModel().getTexture().setReflectivity(0.1f);

        ModelData buschelData = OBJLoader.loadOBJ("grassBuschel");
        Entity grassBuschel = new Entity(
                new TexturedModel(loader.loadToVAO(buschelData.getVertices(), buschelData.getTextureCoords(), buschelData.getNormals(), buschelData.getIndices()),
                        new ModelTexture(loader.loadTexture("grassBuschel"))),
                new Transform(new Vector3f(0, 0, -5),
                        new Vector3f(0, 45, 0),
                        1));
        grassBuschel.getModel().getTexture().setHasTransparency(true);
        grassBuschel.getModel().getTexture().setUseFakeLighting(true);

        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        while (!Display.isCloseRequested()) {
            camera.move();
            entity.increaseRotation(0, 0.4f, 0);
            renderer.processEntity(entity);
            renderer.processEntity(grassBuschel);
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            renderer.render(sun, camera);

            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }

}
