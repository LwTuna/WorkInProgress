package entities;

import models.TexturedModel;
import toolbox.Transform;

public class Entity {

    private TexturedModel model;
    private Transform transform;

    public Entity(TexturedModel model, Transform transform) {
        this.model = model;
        this.transform = transform;
    }

    public void increasePosition(float dx,float dy, float dz){
        transform.position.x += dx;
        transform.position.y += dy;
        transform.position.z += dz;
    }

    public void increaseRotation(float dx,float dy,float dz){
        transform.rotation.x += dx;
        transform.rotation.y += dy;
        transform.rotation.z += dz;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
