package toolbox;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Camera;

public class Maths {


    public static Matrix4f createTransformationMatrix(Transform transform) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        Matrix4f.translate(transform.position, matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(transform.rotation.x), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(transform.rotation.y), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(transform.rotation.z), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(transform.scale, matrix, matrix);
        return matrix;
    }
	public static Matrix4f createViewMatrix(Camera camera) {
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), viewMatrix,
				viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;
	}
}
