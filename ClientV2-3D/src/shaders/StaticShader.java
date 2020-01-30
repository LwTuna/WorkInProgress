package shaders;

import entities.Light;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Camera;
import toolbox.Maths;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/shaders/vertexShader.vert",
            FRAGMENT_FILE = "src/shaders/fragmentShader.frag";

    private int location_transformationMatrix,
            location_projectionMatrix,
            location_viewMatrix,
            location_lightPosition,
            location_lightColor;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColor = super.getUniformLocation("lightColor");

    }

    public void loadLight(Light light){
        super.loadVector3f(location_lightColor,light.getColour());
        super.loadVector3f(location_lightPosition,light.getPosition());

    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) { super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));}
}
