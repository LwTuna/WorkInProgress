package Client.game.states;

import java.awt.Graphics;


import org.json.JSONObject;

import Client.App;
import Client.game.ui.UiManager;

public abstract class State {

	private static State currentState;
	protected static UiManager manager;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState,App app) {
		if(manager == null) {
			manager = new UiManager(app);
		}
		manager.clear();
		currentState.onOpen();
		State.currentState = currentState;
	}
	
	public static UiManager getManager() {
		return manager;
	}

	/**
	 * Invoked when changing states, use it for adding UiElements
	 */
	protected abstract void onOpen();

	protected App app;
	/**
	 * Constructor for an abstract State 
	 * Use onOpen for adding uielements
	 * @param app The app using the states
	 */
	public State(App app) {
		this.app = app;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public abstract void onServerMessage(JSONObject object);
	public abstract void onDisconnect();
	

	public int convertX(double per) {
		return (int) (per*app.getWidth());
	}
	public int convertY(double per) {
		return (int) (per*app.getHeight());
	}
	
	
}
