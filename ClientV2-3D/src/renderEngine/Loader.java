package renderEngine;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;


public class Loader {

    private List<Integer> vaos = new ArrayList<>();
    private List<Integer> vbos = new ArrayList<>();
    
    public RawModel loadToVao(float[] positions) {
	int vaoID = createVAO();
	storeDataInAttributeList(0, positions);
	unbindVAO();
	return new RawModel(vaoID, positions.length/3);
    }
    
    private int createVAO() {
	int vaoID = glGenVertexArrays();
	vaos.add(vaoID);
	glBindVertexArray(vaoID);
	return vaoID;
    }
    
    private void storeDataInAttributeList(int attributeNumber,float[] data) {
	int vboID = glGenBuffers();
	vbos.add(vboID);
	glBindBuffer(GL_ARRAY_BUFFER, vboID);
	
	FloatBuffer buffer = storeDataInFloatBuffer(data);
	glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	
	glVertexAttribPointer(attributeNumber, 3, GL_FLOAT, false, 0,0);
	
	glBindBuffer(GL_ARRAY_BUFFER, 0);
	
	
    }
    
    public void cleanUp() {
	for(int vao:vaos) {
	    glDeleteVertexArrays(vao);
	}
	vaos.clear();
	for(int vbo:vbos) {
	    glDeleteBuffers(vbo);
	}
	vbos.clear();
    }
    
    private void unbindVAO() {
	glBindVertexArray(0);
    }
    
    
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
	FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	return buffer;
    }
}
