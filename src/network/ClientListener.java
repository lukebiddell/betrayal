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
import game.SpritesheetEnum;
import game.ClientWindow;

public class ClientListener extends Thread {

	private DataInputStream in;
	public ClientWindow panel;
	public Graphics2D g;

	public static final int inputSize = 10;
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
				
				if(input[0]>=0){
				
					Spritesheet sprs = SpritesheetEnum.getSprite(input[0]);
				
					g.drawImage(sprs.img,
						input[1], input[2], input[3], input[4],
						sprs.offsetW + sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6], sprs.offsetW + sprs.spriteW * (input[5] + 1) - 1, sprs.offsetH + sprs.spriteH * (input[6] + 1) - 1,
						null);
				}
					
				else if(input[0]==-1) panel.paintImmediately(panel.getBounds());
				else if(input[0]==-2){
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillOval(input[4], input[5], input[6], input[7]);
				}
				else if(input[0]==-3){
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillRect(input[4], input[5], input[6], input[7]);
				}
				else if(input[0]==-4){
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillArc(input[4], input[5], input[6], input[7], input[8], input[9]);
				}
			}
		}
		catch (IOException e) {
			System.out.println(e + " : Connection was lost");
		}
	}
}
