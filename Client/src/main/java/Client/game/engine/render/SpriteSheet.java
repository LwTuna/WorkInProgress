package Client.game.engine.render;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    
    private BufferedImage src;
    
    public SpriteSheet (BufferedImage src) {
	this.src = src;
    }
    
    public BufferedImage crop(int x,int y,int width,int height) {
	return src.getSubimage(x, y, width, height);
    }
    
    public BufferedImage[] crop(int rows,int columns) {
	if(rows<=0 || columns <=0) throw new IllegalArgumentException("Rows or columns cannot be less or equal 0");
	BufferedImage[] arr = new BufferedImage[rows*columns];
	
	int subSizeX = getWidth() / columns;
	int subSizeY = getHeight() / rows;
	for(int y=0;y<rows;y++) {
	    for(int x=0;x<rows;x++) {
		 arr[y*columns + x] = crop(x*subSizeX, y*subSizeY, subSizeX, subSizeY) ;
	    }
	}
	return arr;
    }
    
    public Texture createTexture(int x,int y,int width,int height) {
	return new Texture(crop(x, y, width, height));
    }
    
    
    
    public int getWidth() {
	return src.getWidth();
    }
    public int getHeight() {
   	return src.getHeight();
    }
}
