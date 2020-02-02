package renderEngine;

import entities.Entity;
import entities.Light;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import shaders.StaticShader;
import shaders.TerrainShader;
import terrain.Terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glClearColor;

public class MasterRenderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000f;

    private static float skyRed=0.5f,skyGreen=0.5f,skyBlue=0.8f;


    private Matrix4f projectionMatrix;

    private StaticShader entityShader = new StaticShader();
    private EntityRenderer entityRenderer;

    private Map<TexturedModel, List<Entity>> entities = new HashMap<>();
    private List<Terrain> terrains = new ArrayList<>();

    private TerrainRenderer terrainRenderer;
    private TerrainShader terrainShader = new TerrainShader();

    public MasterRenderer() {
        enableCulling();
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        createProjectionMatrix();
        entityRenderer = new EntityRenderer(entityShader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }


    public static void enableCulling(){
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
    }
    public static void disableCulling(){
        glDisable(GL_CULL_FACE);
    }

    public void render(Light sun, Camera camera) {

        prepare();
        entityShader.start();
        entityShader.loadSkyColor(skyRed,skyGreen,skyBlue);
        entityShader.loadLight(sun);
        entityShader.loadViewMatrix(camera);
        entityRenderer.render(entities);
        entityShader.stop();

        terrainShader.start();
        terrainShader.loadSkyColor(skyRed,skyGreen,skyBlue);
        terrainShader.loadLight(sun);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();


        terrains.clear();

        entities.clear();
    }

    public void processTerrain(Terrain e) {
        terrains.add(e);
    }

    public void processEntity(Entity e) {
        TexturedModel entityModel = e.getModel();
        List<Entity> batch = entities.get(entityModel);
        if (batch != null)
            batch.add(e);
        else {
            List<Entity> newBatch = new ArrayList<>();
            newBatch.add(e);
            entities.put(entityModel, newBatch);
        }
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }

    public void prepare() {
        glEnable(GL_DEPTH_TEST);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(skyRed, skyGreen, skyBlue, 1);
    }

    public void cleanUp() {
        entityShader.cleanUp();
        terrainShader.cleanUp();
    }
}
