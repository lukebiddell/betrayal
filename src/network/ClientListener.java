package network;

import java.io.DataInputStream;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import game.KeyboardInput;
import game.MouseInput;
import game.Player;
import game.Spritesheet;
import game.SPRITESHEET;
import game.ClientWindow;

public class ClientListener extends Thread {

	private DataInputStream in;
	public ClientWindow panel;
	public Graphics2D g;

	private static int inputSize = 7;
	int[] input;

	public ClientListener(DataInputStream in, ClientWindow panel) {
		this.in = in;
		this.panel = panel;
		
		input = new int[inputSize];
	}

	public void run() {
		try {
			while (true) {
				for(int i=0;i<inputSize;i++) input[i] = in.readInt();
				panel.paintImmediately(panel.getBounds());
				
				Spritesheet sprs = SPRITESHEET.getSprite(input[0]);
				
				g.drawImage(sprs.img,
					input[1], input[2], input[3], input[4],
					sprs.offsetW + sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6], sprs.offsetW + sprs.spriteW * (input[5] + 1) - 1, sprs.offsetH + sprs.spriteH * (input[6] + 1) - 1,
					null);
			}
		}
		catch (IOException e) {
			System.out.println(e + " : Connection was lost");
		}
	}
}
