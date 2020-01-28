package renderEngine;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL13.*;

import org.lwjgl.opengl.GL13;

import models.RawModel;
import models.TexturedModel;

import static org.lwjgl.opengl.GL20.*;

public class Renderer {

    public void prepare() {
	glClearColor(0, 0, 1, 1);
	glClear(GL_COLOR_BUFFER_BIT);
    }
    public void render(TexturedModel texturedModel) {
	RawModel model = texturedModel.getRawModel();
	glBindVertexArray(model.getVaoID());
	
	glEnableVertexAttribArray(0);
	glEnableVertexAttribArray(1);
	
	glActiveTexture(GL_TEXTURE0);
	glBindTexture(GL_TEXTURE_2D, texturedModel.getTexture().getTextureID());
	glDrawElements(GL_TRIANGLES, model.getVertexCount(),GL_UNSIGNED_INT,0);
	
	glDisableVertexAttribArray(0);
	glDisableVertexAttribArray(1);
	
	glBindVertexArray(0);
    }
}
