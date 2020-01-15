package Client.game.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model {

	private int vCount;
	private int vID;
	private int tID;
	private int iID;
	private Texture texture;
	
	
	
	public Model(float[] verticies,float[] textureCoords,int[] indicies,Texture texture) {
		this.texture = texture;
		vCount = indicies.length;
		
		vID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vID);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(verticies), GL_STATIC_DRAW);
		
		tID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, tID);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(textureCoords), GL_STATIC_DRAW);
		
		iID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, createBuffer(indicies), GL_STATIC_DRAW);
		
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}

	public void render(int sampler) {
		texture.bind(sampler);
		
		//enable
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		//vertices
		glBindBuffer(GL_ARRAY_BUFFER, vID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		//textures
		glBindBuffer(GL_ARRAY_BUFFER, tID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		//indices
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iID);
		glDrawElements(GL_TRIANGLES, vCount,GL_UNSIGNED_INT,0);
		
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		//disable
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
	}
	public void render() {
		render(0);
	}
	private FloatBuffer createBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private IntBuffer createBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
}
