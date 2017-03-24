package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import game.Player;

public class Server extends Thread{

	
	private ServerSender sender;
	private ServerListener listener;
	private Player player;
	
	
	public Server(DatagramSocket socket, int p, InetAddress inetAddress, Player player) {

	

		this.player = player;
		this.player.viewport.server = this;
		
		this.sender = new ServerSender(p, inetAddress, socket);
		this.listener = new ServerListener(socket, player);
		
		player.game.getLevel().sendData(player);

	}
	public void run(){

		listener.start();
		sender.start();
	}
	
	
	public void addToQueue(int[] ints) {
		
		sender.addToQueue(ints);
	}
	
public void addToQueue(int[][] ints) {
		for(int i = 0; i < ints.length; i++){
			sender.addToQueue(ints[i]);
		}
		
	}
}

//public class Server{
//
//	private Sender sender;
//	private ServerListener listener;
//	private Socket clientSocket;
//	private Player p;
//
//	public Server(Socket clientSocket, Player p) {
//		this.sender = null;
//		this.listener = null;
//		this.clientSocket = clientSocket;
//		this.p = p;
//		this.p.viewport.server = this;
//		
//		
//		try {
//
//			this.sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()));
//			this.listener = new ServerListener(new DataInputStream(clientSocket.getInputStream()), p);
//			sender.start();
//			listener.start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/*public void run() {
//		try {
//
//			this.sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()));
//			this.listener = new Listener(new DataInputStream(clientSocket.getInputStream()), p);
//			sender.start();
//			listener.start();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	} */
//	
//	public void addToQueue(Integer input) {
//
//		sender.addToQueue(input);
//
//	}
//}
