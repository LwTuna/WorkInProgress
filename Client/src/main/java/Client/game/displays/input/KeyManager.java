package Client.game.displays.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class KeyManager implements KeyListener{

	private Map<Integer,Boolean> keyDown = new HashMap<>();
	
	private Map<Integer,Boolean> AkeyDown = new HashMap<>();
	
	public void tick() {
		for(Entry<Integer,Boolean> entry:keyDown.entrySet()) {
			AkeyDown.putIfAbsent(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyDown.put(e.getKeyCode(), true);		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyDown.put(e.getKeyCode(), false);
		
	}

	
	public boolean isKeyDown(int keyCode) {
		return keyDown.getOrDefault(keyCode, false);
	}
}
