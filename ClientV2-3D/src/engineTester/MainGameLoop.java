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
import org.newdawn.slick.opengl.TextureLoader;
import renderEngine.*;
import shaders.StaticShader;
import terrain.Terrain;
import terrain.TerrainTexture;
import terrain.TerrainTexturePack;
import textures.ModelTexture;
import toolbox.Transform;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glViewport;

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
        texture.setShineDamper(10);
        texture.setReflectivity(0.1f);

        List<Entity> entityList = new ArrayList<>();

        for(int i=0;i<1000;i++){
            int x = (int) (Math.random() * -2048d)+1024;
            int z = (int) (Math.random() * -1024d);

            entityList.add(new Entity(texturedModel, new Transform(new Vector3f(x, 2, z), new Vector3f(0, 180, 0), 1)));
        }



        ModelData buschelData = OBJLoader.loadOBJ("grassBuschel");
        Entity grassBuschel = new Entity(
                new TexturedModel(loader.loadToVAO(buschelData.getVertices(), buschelData.getTextureCoords(), buschelData.getNormals(), buschelData.getIndices()),
                        new ModelTexture(loader.loadTexture("grassBuschel"))),
                new Transform(new Vector3f(0, 0, -5),
                        new Vector3f(0, 45, 0),
                        1));
        grassBuschel.getModel().getTexture().setHasTransparency(true);
        grassBuschel.getModel().getTexture().setUseFakeLighting(true);

        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("terrain/blendMap"));
        TerrainTexturePack texturePack = new TerrainTexturePack(
                new TerrainTexture(loader.loadTexture("terrain/grass")),
                new TerrainTexture(loader.loadTexture("terrain/floweryGrass")),
                new TerrainTexture(loader.loadTexture("terrain/dirt")),
                new TerrainTexture(loader.loadTexture("terrain/path"))
        );
        Terrain terrain = new Terrain(0, -1, loader,texturePack,blendMap);
        Terrain terrain2 = new Terrain(-1, -1, loader,texturePack,blendMap);
        while (!Display.isCloseRequested()) {
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
            camera.move();

            for(Entity e : entityList){
                e.increaseRotation(0.1f,0.3f,0.2f);
                renderer.processEntity(e);
            }
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
