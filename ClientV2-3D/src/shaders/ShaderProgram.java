package shaders;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public abstract class ShaderProgram {

    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    
    public ShaderProgram(String vertexFile,String fragmentFile) {
	vertexShaderID = loadShader(vertexFile, GL_VERTEX_SHADER);
	fragmentShaderID = loadShader(fragmentFile, GL_FRAGMENT_SHADER);
	programID = glCreateProgram();
	glAttachShader(programID, vertexShaderID);
	glAttachShader(programID, fragmentShaderID);
	glLinkProgram(programID);
	glValidateProgram(programID);
	
	bindAttributes();
	getAllUniformLocations();
    }
    
    protected abstract void getAllUniformLocations();
    
    protected int getUniformLocation(String uniformName) {
	return glGetUniformLocation(programID, uniformName);
    }
    
    public void start() {
	glUseProgram(programID);
    }
    
    public void stop() {
	glUseProgram(0);
    }
    
    public void cleanUp() {
	stop();
	glDetachShader(programID, vertexShaderID);
	glDetachShader(programID, fragmentShaderID);
	glDeleteShader(vertexShaderID);
	glDeleteShader(fragmentShaderID);
	glDeleteProgram(programID);
    }
    
    protected abstract void bindAttributes();
    
    protected void bindAttribute(int attribute,String variableName) {
	glBindAttribLocation(programID, attribute, variableName);
    }
    
    protected void loadFloat(int location,float value) {
	glUniform1f(location, value);
    }
    protected void loadVector3f(int location,Vector3f value) {
	glUniform3f(location, value.x, value.y, value.z);
    }
    
    protected void loadVector2f(int location,Vector2f value) {
	glUniform2f(location, value.x, value.y);
    }
    protected void loadMatrix(int location,Matrix4f value) {
	value.store(matrixBuffer);
	matrixBuffer.flip();
	glUniformMatrix4(location, false, matrixBuffer);
    }
    protected void loadBoolean(int location,boolean value) {
	glUniform1f(location, (value) ? 1 : 0);
    }
    
    protected void loadFloat(int location,int value) {
	glUniform1i(location, value);
    }
    
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("//\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = glCreateShader(type);
        glShaderSource(shaderID, shaderSource);
        glCompileShader(shaderID);
        if(glGetShaderi(shaderID, GL_COMPILE_STATUS )== GL_FALSE){
            System.out.println(glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }
}
