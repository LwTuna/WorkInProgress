package Client.game.displays;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display{

	private Canvas canvas;
	private JFrame frame;
	
	public Display(String title,int width,int height,boolean fullscreen) {
		frame = new JFrame(title);
		if(fullscreen) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setUndecorated(true);
			
		}else {
			frame.setSize(width, height);
		}
		frame.setPreferredSize(new Dimension(frame.getWidth(),frame.getHeight()));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas = new Canvas();
		canvas.setSize(frame.getWidth(), frame.getHeight());
		frame.add(canvas);
		frame.pack();
		
		frame.setVisible(true);
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getHeight();
	}
	
	
}
