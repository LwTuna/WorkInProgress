package Server.game.world;

public class Vector2i {

	public int x,y;

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i(String s) {
		String[] split = s.split(";");
		x = Integer.parseInt(split[0]);
		y = Integer.parseInt(split[1]);
	}
	
	
	
	public String toString() {
		return x+";"+y;
	}
}
