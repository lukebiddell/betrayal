package network;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import game.ClientWindow;

public class UDPClient {

	private DatagramSocket socket;
	private int port;
	private InetAddress inetAddress;
	private Sender sender;
	private ClientListener listener;
	private ClientWindow window;

	public UDPClient(DatagramSocket socket, int serverPort, InetAddress serverAddress) {
		this.socket = socket;
		this.port = serverPort;
		this.inetAddress = serverAddress;
		this.sender = new Sender(serverPort, inetAddress, socket);
		this.window = new ClientWindow(sender);
		this.listener = new ClientListener(socket, window);
		window.cl = listener;
		sender.start();
		listener.start();
	}

	public void addToQueue(int a, int b, int c) {
		sender.addToQueue(a, b, c);
	}

}