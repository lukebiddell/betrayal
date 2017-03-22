package lobby;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LobbyServerClient extends Thread {

	private Socket socket;
	private LobbySender sender;
	private LobbyListener listener;
	public LobbyServerClient(Socket socket){
		this.socket = socket;
		this.sender = null;
		this.listener = null;
	}
	
	public void run(){
		try {

			this.sender = new LobbySender(new DataOutputStream(socket.getOutputStream()));
			this.listener = new LobbyListener(new DataInputStream(socket.getInputStream()));
			sender.start();
			listener.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}
