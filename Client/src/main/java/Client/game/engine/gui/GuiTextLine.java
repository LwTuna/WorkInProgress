package Client.game.engine.gui;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.joml.Vector2f;

import Client.game.engine.render.Texture;

public class GuiTextLine extends GuiElement{

    private String line;
    
    private Texture texture;
    
    private Font font;
    
    public GuiTextLine(String line,Vector2f position, Vector2f scale,Font font) {
	super(position, scale);
	this.font = font;
	setText(line);
    }

    private void setText(String text) {
	this.line = text;
	
	Canvas c = new Canvas();
	FontMetrics fm = c.getFontMetrics(font);
	int width = fm.stringWidth(line);
	int height = fm.getLeading()+fm.getAscent();
	
	BufferedImage image = new BufferedImage(width+1, height, BufferedImage.TYPE_INT_ARGB);
	Graphics g = image.getGraphics();
	FontMetrics metrics = g.getFontMetrics(font);
	    
	int x = 0 + (width - metrics.stringWidth(text)) / 2;
	int y = 0 + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
	g.setFont(font);
	g.drawString(text, x, y);
	image.getGraphics().dispose();
	
	texture = new Texture(image);
//	File outputfile = new File("image.png");
//	try {
//	    ImageIO.write(image, "png", outputfile);
//	} catch (IOException e) {
//	    e.printStackTrace();
//	}
    }

    @Override
    public void onClick() {  }

    @Override
    public void render(GuiShader shader) {
	shader.bind();
	texture.bind(0);
	shader.setUniform("transformationMatrix", GuiManager.createTransformationMatrix(position, scale));
	model.render();
    }

    
    
}
