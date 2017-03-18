package network;

import java.io.DataInputStream;
import java.io.IOException;

import game.KeyboardInput;
import game.MouseInput;
import game.Player;

public class Listener extends Thread {

	private DataInputStream in;

	public Player p;

	public Listener(DataInputStream in, Player p) {
		this.in = in;
		this.p = p;
	}

	public void run() {
		try {
			while (true) {
				int flag = in.readInt();
				int keycodeOrCoordX = in.readInt();
				int coordY = in.readInt();
				switch(flag){
				case 0: //key released
					p.keyboard.keyCodeReleased(keycodeOrCoordX);
					break;
				case 1: //key pressed
					p.keyboard.keyCodePressed(keycodeOrCoordX);
					break;
				case 2: //lmb released
					p.mouse.mouseCodeReleased(0);
					break;
				case 3: //lmb pressed
					p.mouse.mouseCodePressed(0);
					break;
				case 4: //rmb released
					p.mouse.mouseCodeReleased(1);
					break;
				case 5: //rmb pressed
					p.mouse.mouseCodePressed(1);
					break;
				case 6: //mouse moved
					p.mouse.setPos(keycodeOrCoordX, coordY);
					break;
				case 7: //window resized
					p.viewport.screenW = keycodeOrCoordX;
					p.viewport.screenH = coordY;
					break;
				default:
				}
			}
		}
		catch (IOException e) {
			System.out.println(e + " : Connection was lost");

		}
	}
}
