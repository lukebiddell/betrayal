package lobby;

import java.io.DataInputStream;
import java.io.IOException;
import menus.HostLobby;
import menus.LobbyMember;

/*
 * @author Sam Dowell
 */
public class LobbyListener extends Thread {

	private DataInputStream in;
	private boolean running;
	private LobbyMember member;
	private HostLobby owner;

	public LobbyListener(DataInputStream in, LobbyMember member) {
		this.in = in;
		this.running = true;
		this.member = member;
		this.owner = null;
		
	}

	public LobbyListener(DataInputStream in, HostLobby owner) {
		this.in = in;
		this.running = true;
		this.member = null;
		this.owner = owner;
	}

	@Override
	public void run() {
		try {
			while (running) {
				String message = in.readUTF();

				if(this.member == null){
					if(message.equals("**LEAVE**")){
						String name = in.readUTF();
						this.owner.removePlayer(name);
					}
				} else {
					if(message.equals("**STARTGAME**")){
						this.member.startGame();	
					} else if (message.equals("**RESET**")){
						this.member.leave();
					}
					
				}
			}
		} catch (IOException e) {
			System.out.println(e + " : Connection was lost");

		}
	}

	public void reset() {
		this.running = false;
		this.interrupt();
		
	}
}

