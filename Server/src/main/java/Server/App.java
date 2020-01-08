/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Server;

import java.net.InetSocketAddress;

import Server.game.Game;
import Server.net.Server;

public class App {
    
	private static final int port = 8888;
	
	private Server server;
	
	private Game game;

	public App() {
		game = new Game(this);
		game.start();
		server = new Server(new InetSocketAddress("localhost",port),this);
		Thread thread = new Thread(server);
		thread.start();
		
		
		
	}
	
	
	
	
    public Server getServer() {
		return server;
	}




	public Game getGame() {
		return game;
	}




	public static void main(String[] args) {
        new App();
    }
}
