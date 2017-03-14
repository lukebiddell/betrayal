package network;

import java.net.DatagramSocket;
import java.net.InetAddress;

import game.Player;


//import game.Player;

public class UDPServer {

	
	private ServerSender sender;
	private Listener listener;
	private Player player;
	
	public UDPServer(DatagramSocket socket, int p, InetAddress inetAddress, Player player) {

	
		this.sender = new ServerSender(p, inetAddress, socket);
		this.player = player;
		this.player.viewport.server = this;
		this.listener = new Listener(socket, player);
		
		sender.start();;
		listener.start();
		
	}
	
	
	public void addToQueue(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		sender.addToQueue(a, b, c, d, e, f, g, h, i, j);
	}

	

	
}

