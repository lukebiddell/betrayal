package network;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import game.*;

public class MainServer extends Thread {

	private Game game;
	private int port; // port to listen for new connections
	private ArrayList<UDPServer> connections; // list of all
															// connections

	public MainServer(int port, Game game) {
		this.connections = new ArrayList<UDPServer>();
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


				Socket clientSocket = serverSocket.accept(); // accept a new
																// connection,
																// allocates
																// ports for
																// connection

				int p = clientSocket.getLocalPort(); // gets local port of
														// connection

				int p2 = clientSocket.getPort(); // gets remote port of the
													// connection
				InetAddress ip = clientSocket.getInetAddress(); // gets the Inet
																// address of
																// client
																// connection

				clientSocket.close(); // closes the socket so can be used for as
										// Datagram Socket
				DatagramSocket socket = new DatagramSocket(p); // create a
																// Datagram
																// socket using
																// the port
																// selected from
																// serversocket.accept()
				KeyboardInput keyboard = new KeyboardInput();
				MouseInput mouse = new MouseInput();
				Player player = new Player(game, keyboard, mouse);
				player.viewport = new Viewport(game, player);
				if(player.viewport != null)System.out.print("its not null");
				game.players.add(player);
				connections.add(new UDPServer(socket, p2, ip, player));
																// creates a new
																// UDPServer
																// with the
																// Datagram
																// Server object
																// while adding
																// it to
																// connections
				//game.players.add(player);
			}} catch (IOException e) {

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

	public void broadcastMessage(int a, int b, int c, int d, int e, int f, int g,int h ,int i,int  j) {
		for (int x = 0; i < connections.size(); x++) {
			connections.get(x).addToQueue(a, b, c, d, e, f, g, h, i, j);
			System.out.println("testing broadcastMessage");	
		} 
		
	}
	}
