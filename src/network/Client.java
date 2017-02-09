package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

	private Sender sender;
	private Listener listener;
	private Socket server;

	/**
	 * 
	 */
	public Client() {
		this.sender = null;
		this.listener = null;
		this.server = null;
	}

	/**
	 * main method for the class, the client will try to connect to a specified
	 * server socket, and open input and output streams to it
	 * 
	 * @param port
	 *            The port number you wish to connect to
	 * @param name
	 *            The name of the server on the network
	 * @return returns true if a connection is successfully established. False
	 *         otherwise.
	 */
	public boolean connect(int port, String name) {
		try {

			this.server = new Socket(name, port);
			sender = new Sender(new DataOutputStream(server.getOutputStream()));
			listener = new Listener(new DataInputStream(server.getInputStream()));
			sender.start();
			listener.start();
			return true;
		} catch (IOException e) {
			System.out.println("couldnt connect to specified host, connection refused.");
			e.printStackTrace();
			return false;
		}
	}

	public void addToQueue(byte[] input) {
		if (input.length == 4 && sender != null) {
			sender.addToQueue(input);
		}
	}

}
