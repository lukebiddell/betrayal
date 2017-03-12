package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

import game.Player;

public class Server{

	private Sender sender;
	private Listener listener;
	private Socket clientSocket;
	private Player p;

	public Server(Socket clientSocket, Player p) {
		this.sender = null;
		this.listener = null;
		this.clientSocket = clientSocket;
		this.p = p;
		this.p.viewport.server = this;
		
		
		try {

			this.sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()));
			this.listener = new Listener(new DataInputStream(clientSocket.getInputStream()), p);
			sender.start();
			listener.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public void run() {
		try {

			this.sender = new Sender(new DataOutputStream(clientSocket.getOutputStream()));
			this.listener = new Listener(new DataInputStream(clientSocket.getInputStream()), p);
			sender.start();
			listener.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} */
	
	public void addToQueue(Integer input) {

		sender.addToQueue(input);

	}
}
