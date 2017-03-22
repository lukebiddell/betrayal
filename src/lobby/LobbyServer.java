package lobby;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import menus.Lobby;

public class LobbyServer extends Thread {

	private int port;
	private Lobby lobby;
	
	//private Map<InetAddress,String> = new
	public LobbyServer(int port, Lobby lobby){
		//this.connections = new LinkedList<Server>();
		this.port = port;
		this.lobby  = lobby;
		}

	public void run() { 
		try {
			ServerSocket socket = new ServerSocket(port);
		
			while (true){
			
		
			Socket clientSocket = socket.accept();
			LobbyServerClient s = new LobbyServerClient(clientSocket);
			this.lobby.updatePlayers(clientSocket.getInetAddress(), new DataInputStream(clientSocket.getInputStream()).readUTF());
			s.start();
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
