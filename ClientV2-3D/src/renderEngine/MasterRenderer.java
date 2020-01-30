package renderEngine;

import entities.Entity;
import entities.Light;
import models.TexturedModel;
import shaders.StaticShader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MasterRenderer {

    private StaticShader entityShader = new StaticShader();
    private Renderer renderer = new Renderer(entityShader);

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();

    public void render(Light sun, Camera camera){
        renderer.prepare();
        entityShader.start();
        entityShader.loadLight(sun);
        entityShader.loadViewMatrix(camera);
        renderer.render(entities);
        entityShader.stop();
        entities.clear();
    }

    public void processEntity(Entity e){
        TexturedModel entityModel = e.getModel();
        List<Entity> batch = entities.get(e);
        if(batch != null)
            batch.add(e);
        else{
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(e);
            entities.put(entityModel,newBatch);
        }
    }


    public void cleanUp(){
        entityShader.cleanUp();
    }
}
