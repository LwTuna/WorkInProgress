package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/shaders/vertexShader.vert",
	    FRAGMENT_FILE="src/shaders/fragmentShader.frag";
    
    private int location_transformationMatrix;
    
    public StaticShader() {
	super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
	super.bindAttribute(0, "position");
	super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
	location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    public void loadTransformationMatrixx(Matrix4f matrix) {
	super.loadMatrix(location_transformationMatrix, matrix);
    }
    
}