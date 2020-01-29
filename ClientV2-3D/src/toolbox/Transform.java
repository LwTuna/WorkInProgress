package toolbox;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Transform {

    public Vector3f position;
    public Vector3f rotation;
    
    public Vector3f scale;

    public Transform(Vector3f translation, Vector3f rotation, int scale) {
	this.position = translation;
	this.rotation = rotation;
	this.scale = new Vector3f(scale,scale,scale);
    }

    public Transform(Vector3f translation, Vector3f rotation, Vector3f scale) {
	this.position = translation;
	this.rotation = rotation;
	this.scale = scale;
    }
    
    
    public Matrix4f getMatrix() {
	return Maths.createTransformationMatrix(this);
    }
    
}
