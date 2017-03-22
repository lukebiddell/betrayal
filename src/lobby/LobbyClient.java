package lobby;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class LobbyClient {


	private LobbySender sender;
	private LobbyListener listener;
	private boolean connected;

	public LobbyClient() {
		this.sender = null;
		this.listener = null;
		this.connected = false;
	}

	public boolean connect(int port, String name) {
		try {

			Socket server = new Socket(name, port);
			sender = new LobbySender(new DataOutputStream(server.getOutputStream()));
			listener = new LobbyListener(new DataInputStream(server.getInputStream()));
			sender.start();
			listener.start();
			return true;
		} catch (IOException e) {
			System.out.println("couldnt connect to specified host, connection refused.");
			e.printStackTrace();
			return false;
		}
	}
	
	public void addToQueue(String input){
		sender.addToQueue(input);
	}
	
}
