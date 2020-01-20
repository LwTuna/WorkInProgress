package Client.game.assets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Client.Logger;

public class ImageLoader {

    public static BufferedImage load(String file) {
	try {
	    return ImageIO.read(new File("./res/"+file));
	}catch(IOException ex) {
	    Logger.err(ex);
	    return null;
	}
    }
    
}
