package toolbox;

import java.util.Vector;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Maths {

    
    public static Matrix4f createTransformationMatrix(Transform transform) {
	Matrix4f matrix = new Matrix4f();
	matrix.setIdentity();
	Matrix4f.translate(transform.translation, matrix, matrix);
	Matrix4f.rotate((float) Math.toRadians(transform.rotation.x),new Vector3f(1,0,0),matrix,matrix);
	Matrix4f.rotate((float) Math.toRadians(transform.rotation.y),new Vector3f(0,1,0),matrix,matrix);
	Matrix4f.rotate((float) Math.toRadians(transform.rotation.z),new Vector3f(0,0,1),matrix,matrix);
	Matrix4f.scale(transform.scale,matrix, matrix);
	return matrix;
    }
    
}
