package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import game.*;

public class MainServer extends Thread{

	//private Map<String, Server> upConnections;
 
 private int port;
 private Game game;
 
	public MainServer(int port, Game game) {
  this.port = port;
	 this.game = game;
	}
	
	public void run(){
	 ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			String name = getLocalName();

			System.out.println("To connect to this server use this name : " + name);
			System.out.println("Waiting for connection...");

			while (true) {

				Socket clientSocket = serverSocket.accept();
				System.out.println("connection to " + clientSocket.getLocalAddress());
				//new Server(clientSocket).start();


    KeyboardInput keyboard = new KeyboardInput();
    MouseInput mouse = new MouseInput();
    
    Player p = new Player(game, keyboard, mouse);
		  p.viewport = new Viewport(game, p);
		  game.players.add(p);
		
		  Server server = new Server(clientSocket, p);
		
		  p.viewport.server = server;
		
		  server.start();


			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private String getLocalName() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return addr.getHostName();
	}
}
