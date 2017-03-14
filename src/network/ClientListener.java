package network;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;

import game.ClientWindow;
import game.Spritesheet;
import game.SpritesheetEnum;

public class ClientListener extends Thread {
	private DatagramSocket socket;
	private DatagramPacket packet;
	private byte[] recievedData;
	public ClientWindow panel;
	public Graphics2D g;

	public static final int inputSize = 10;
	int[] input = new int[inputSize];

	public ClientListener(DatagramSocket socket, ClientWindow panel) {
		this.socket = socket;
		this.recievedData = new byte[inputSize * 4];
		this.packet = new DatagramPacket(recievedData, recievedData.length);
		this.panel = panel;
	}

	public void run() {
		while (true) {
			try {
				socket.receive(packet);
				recievedData = packet.getData();

				input = toInts(recievedData);

				if (input[0] >= 0) {

					Spritesheet sprs = SpritesheetEnum.getSprite(input[0]);

					g.drawImage(sprs.img, input[1], input[2], input[3], input[4],
							sprs.offsetW + sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6],
							sprs.offsetW + sprs.spriteW * (input[5] + 1) - 1,
							sprs.offsetH + sprs.spriteH * (input[6] + 1) - 1, null);
				}

				else if (input[0] == -1)
					panel.paintImmediately(panel.getBounds());
				else if (input[0] == -2) {
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillOval(input[4], input[5], input[6], input[7]);
				} else if (input[0] == -3) {
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillRect(input[4], input[5], input[6], input[7]);
				} else if (input[0] == -4) {
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillArc(input[4], input[5], input[6], input[7], input[8], input[9]);
				}

				System.out.println("testing");
				for (int i = 0; i < input.length; i++) {
					System.out.println(input[i]);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private int[] toInts(byte[] data) { // this is currently presuming the
		// input is of size 10
		int[] ints = new int[inputSize];
		byte[] temp = new byte[4]; // byte array for one integer;
		int count = 0;
		// this sucks, but the next 10 loops are for assigning the byte arrays
		// into integers
		ByteBuffer b = ByteBuffer.wrap(temp);
		for (int i = 0; i < 4; i++) {
			temp[i] = data[count];
			count++;
		}
		count = 0;
		ints[0] = b.getInt();
		b.rewind();

		for (int i = 4; i < 8; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[1] = b.getInt();
		b.rewind();

		for (int i = 8; i < 12; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();

		for (int i = 12; i < 16; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[2] = b.getInt();
		b.rewind();

		for (int i = 16; i < 20; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[3] = b.getInt();
		b.rewind();

		for (int i = 20; i < 24; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[4] = b.getInt();
		b.rewind();
		for (int i = 24; i < 28; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[5] = b.getInt();
		b.rewind();
		for (int i = 24; i < 28; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[6] = b.getInt();
		b.rewind();
		for (int i = 24; i < 28; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[7] = b.getInt();
		b.rewind();
		for (int i = 24; i < 28; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[8] = b.getInt();
		b.rewind();
		for (int i = 24; i < 28; i++) {
			temp[count] = data[i];
			count++;
		}
		count = 0;
		ints[10] = b.getInt();
		b.rewind();
		return ints;

	}
}
