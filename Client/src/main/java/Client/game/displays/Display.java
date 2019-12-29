package Client.game.displays;

import javax.swing.JFrame;

import Client.Logger;

public class Display extends JFrame{

	
	
	public Display(String title,int width,int height,boolean fullscreen) {
		super(title);
		if(fullscreen) {
			setExtendedState(MAXIMIZED_BOTH);
			setUndecorated(true);
			
		}else {
			setSize(width, height);
		}
		setResizable(false);
		setVisible(true);
		
	}
	
}
