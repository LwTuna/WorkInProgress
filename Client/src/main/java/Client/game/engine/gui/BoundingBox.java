package Client.game.engine.gui;

public class BoundingBox {

    public float x,y,width,height;

    public BoundingBox(float x, float y, float width, float height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
    }

    public boolean contains(double xR, double yR) {
	if(xR>x && xR < x+width) {
	    if(yR>y && yR < y+height) {
		return true;
	    }
	}
	return false;
    }
    
    @Override
    public String toString() {
        return x+"/"+y+"/"+width+"/"+height;
    }
}
