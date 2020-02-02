package renderEngine;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Matrix4f;
import shaders.TerrainShader;
import terrain.Terrain;
import terrain.TerrainTexturePack;
import textures.ModelTexture;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class TerrainRenderer {

    private TerrainShader shader;

    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.connectTextureUnits();
        shader.stop();
    }

    public void render(List<Terrain> terrains){
        for(Terrain terrain:terrains){
            prepareTerrain(terrain);
            loadModelMatrix(terrain);

            glDrawElements(GL_TRIANGLES, terrain.getModel().getVertexCount(), GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        }
    }


    public void prepareTerrain(Terrain terrain) {
        RawModel model = terrain.getModel();
        glBindVertexArray(model.getVaoID());

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        bindTextures(terrain);
        shader.loadShineVariables(1,0);

    }

    private void bindTextures(Terrain terrain){
        TerrainTexturePack texturePack = terrain.getTexture();
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D,texturePack.getBackGroundTexture().getTextureId());
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D,texturePack.getrTexture().getTextureId());
        glActiveTexture(GL_TEXTURE2);
        glBindTexture(GL_TEXTURE_2D,texturePack.getgTexture().getTextureId());
        glActiveTexture(GL_TEXTURE3);
        glBindTexture(GL_TEXTURE_2D,texturePack.getbTexture().getTextureId());
        glActiveTexture(GL_TEXTURE4);
        glBindTexture(GL_TEXTURE_2D,terrain.getBlendMap().getTextureId());

    }

    private void unbindTexturedModel() {
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);
    }

    private void loadModelMatrix(Terrain e) {
        Matrix4f transformationMatrix = e.getTransform().getMatrix();
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
