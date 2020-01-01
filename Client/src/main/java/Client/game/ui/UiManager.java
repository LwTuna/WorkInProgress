package Client.game.ui;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import Client.App;

public class UiManager {

	private List<UiElement> components = new ArrayList<>();
	
	private UiElement focused = null;
	
	private App app;
	public UiManager(App app) {
		this.app = app;
	}
	
	
	public void tick() {
		for(UiElement component : components) {
			component.tick();
		}
	}
	
	public void render(Graphics g) {
		for(UiElement component : components) {
			component.render(g);
		}
	}
	
	public void add(UiElement component) {
		components.add(component);
	}
	
	public void remove(UiElement component) {
		components.remove(component);
		if(getFocused() == component) {
			component.setFocused(false);
			focused = null;
		}
	}
	
	public void clear() {
		components.clear();
		focused = null;
	}
	
	public void setFocused(UiElement component) {
		if(focused !=null) {
			focused.setFocused(false);
			focused = component;
			focused.setFocused(true);
		}
	}
	public UiElement getFocused() {
		return focused;
	}
	public boolean hasFocusedElement() {
		return focused != null;
	}
}
