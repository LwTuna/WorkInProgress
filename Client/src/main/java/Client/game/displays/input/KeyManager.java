package Client.game.displays.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KeyManager implements KeyListener{

	private Map<Integer,Boolean> keyDown = new HashMap<>();
	
	private Map<Integer,Boolean> AkeyDown = new HashMap<>();
	
	private List<Character> typed = new ArrayList<>();	
	private List<Character> Atyped = new ArrayList<>();	
	public void tick() {
		for(Entry<Integer,Boolean> entry:keyDown.entrySet()) {
			AkeyDown.put(entry.getKey(), entry.getValue());
		}
		Atyped.clear();
		for(Character c:typed) {
			Atyped.add(c);
		}
		typed.clear();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		typed.add(e.getKeyChar());
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
	
	public List<Character> lastTyped(){
		return Atyped;
	}
}
