package Client.game.states;

import java.awt.Graphics;

import org.json.JSONObject;

import Client.App;

public abstract class State {

	private static State currentState;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}
	
	protected App app;
	public State(App app) {
		this.app = app;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public abstract void onServerMessage(JSONObject object);
	public abstract void onDisconnect();

	protected int convertX(double per) {
		return (int) (per*app.getWidth());
	}
	protected int convertY(double per) {
		return (int) (per*app.getHeight());
	}
	
	
}
