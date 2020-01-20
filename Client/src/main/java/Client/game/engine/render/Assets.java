package Client.game.engine.render;

public class Assets {

    public static Texture playerTexture;
    public static Model model;
    
    public static void init() {
	model = new Model(
		 new float[] {
				-1,1,0,
				1,1,0,
				1,-1,0,
				-1,-1,0
		}, new float[] {
				0,0,1,0,1,1,0,1
		}, new int[] {
				0,1,2,
				2,3,0
		});
	playerTexture = new Texture("test2.png");
    }
    
}
