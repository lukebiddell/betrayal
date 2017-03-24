package network;

import java.io.DataInputStream;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import game.KeyboardInput;
import game.MouseInput;
import game.Player;
import game.Spritesheet;
import game.SpritesheetEnum;
import game.ClientWindow;

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
	private ClientWindow panel;
	public Graphics2D g;
	int[] info;
	int tilemapW = 0;
	int tilemapH = 0;

	@SuppressWarnings("unchecked")
	LinkedList<Integer>[][] tilemap = new LinkedList[0][0];
	private int input1is5counter = 0;

	int mapX = 0;
	int mapY = 0;
	int tileSize = 10;

	public static final int inputSize = 10;
	public static final int infoSize = 2;
	int[] input;

	Logger logger;
	
	public ClientListener(DatagramSocket socket, ClientWindow panel) {
		logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  

	    try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("LevelLog.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.info("Logger for debugging levels");  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		
		this.panel = panel;
		this.input = new int[inputSize];
		this.info = new int[infoSize];
		this.socket = socket;
		this.recievedData = new byte[inputSize * 4];
		this.packet = new DatagramPacket(recievedData, recievedData.length);
		// this.g = (Graphics2D)(panel.getGraphics());
	}

	// @SuppressWarnings("unchecked")
	public void run() {
		while (true) {
			try {
				socket.receive(packet);
				recievedData = packet.getData();
				input = ByteConversion.toInts(recievedData);
				logger.info(Arrays.toString(input));
				/*
				 * for (int i = 0; i < input.length; i++) { if(input[0] == -6)
				 * System.err.println(input[i]); }
				 */

				if (input[0] >= 0) {

					Spritesheet sprs = SpritesheetEnum.getSprite(input[0]);

					g.drawImage(sprs.img, input[1], input[2], input[3], input[4],
							sprs.offsetW + sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6],
							sprs.offsetW + sprs.spriteW * (input[5] + 1) - 1,
							sprs.offsetH + sprs.spriteH * (input[6] + 1) - 1, null);
				}

				else if (input[0] == -1) {

					g.setColor(Color.WHITE);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
					g.drawString("Global killcount: " + info[0], 30, 30);
					g.drawString("Experience: " + info[1], 30, 80);

					panel.paintImmediately(panel.getBounds());
				} else if (input[0] == -2) {
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillOval(input[4], input[5], input[6], input[7]);
				} else if (input[0] == -3) {
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillRect(input[4], input[5], input[6], input[7]);
				} else if (input[0] == -4) {
					g.setColor(new Color(input[1], input[2], input[3]));
					g.fillArc(input[4], input[5], input[6], input[7], input[8], input[9]);
				} else if (input[0] == -5) {
					info[input[1]] = input[2];
				} else if (input[0] == -6) {
					if (input[1] == 0) {
						tilemapW = input[2];
					}
					if (input[1] == 1) {
						tilemapH = input[2];
						// System.out.println(tilemapW);

						// System.out.println(tilemapH);

						tilemap = new LinkedList[tilemapW][tilemapH];
						System.err.println("tilemapW = " + tilemapW + " | tilemapH = " + tilemapH);
						int debugCounter = 0;
						for (int i = 0; i < tilemapW - 1; i++) {
							for (int j = 0; j < tilemapH - 1; j++) {
								tilemap[i][j] = new LinkedList<Integer>();
								debugCounter++;
							}
						}

						System.out.println("counter = " + debugCounter);
					}
					if (input[1] == 2) {
						mapX = input[2];
					}
					if (input[1] == 3) {
						mapY = input[2];
					}
					if (input[1] == 4) {
						tileSize = input[2];
					}
					if (input[1] == 5) {
						System.out.println(Arrays.toString(input));
						tilemap[input[2]][input[3]].add(input[4]);
						// System.out.println(++input1is5counter + ": input2 = "
						// + input[2] + " | input3 = " + input[3] + " | input4 =
						// " + input[4]);
					}
				} else if (input[0] == -7) {// drawing started

					for (int i = 0; i < tilemap.length; i++) {
						// System.out.println("i = " + i);
						for (int j = 0; j < tilemap[i].length; j++) {
							// System.out.println("j = " + j);
							for (Integer tileID : tilemap[i][j]) {
								System.out.println(i + "," + j + ": " + tileID);
							}
						}
					}

					for (int i = 0; i < tilemapW; i++) {
						for (int j = 0; j < tilemapH; j++) {

							Spritesheet ss = SpritesheetEnum.getSprite(SpritesheetEnum.TERRAIN);

							ListIterator<Integer> li = tilemap[i][j].listIterator();
							while (li.hasNext()) {
								// System.out.println("i = " + i + " j = " + j +
								// " hasNext = " + li.hasNext());
								// System.out.println("------------asaghuisdfhgiusdfhguisfg------------------");
								int ac = li.next();
								g.drawImage(ss.img, mapX + i * tileSize, mapY + j * tileSize, mapX + (i + 1) * tileSize,
										mapY + (j + 1) * tileSize, ss.offsetW + (ac % ss.spriteCol) * ss.spriteW,
										ss.offsetH + (ac / ss.spriteCol) * ss.spriteH,
										ss.offsetW + (ac % ss.spriteCol + 1) * ss.spriteW,
										ss.offsetH + (ac / ss.spriteCol + 1) * ss.spriteH, null);
							}
						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassCastException e2) {
				System.out.println("caught classcastException");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
/*
 * public class ClientListener extends Thread {
 * 
 * private DataInputStream in; public ClientWindow panel; public Graphics2D g;
 * public int[] info;
 * 
 * public static final int inputSize = 10; public static final int infoSize = 2;
 * int[] input;
 * 
 * public ClientListener(DataInputStream in, ClientWindow panel) { this.in = in;
 * this.panel = panel;
 * 
 * input = new int[inputSize]; info = new int[infoSize]; }
 * 
 * public void run() { try { while (true) { //while(g == null);
 * 
 * for(int i=0;i<inputSize;i++) input[i] = in.readInt();
 * 
 * if(input[0]>=0){
 * 
 * Spritesheet sprs = SpritesheetEnum.getSprite(input[0]);
 * 
 * g.drawImage(sprs.img, input[1], input[2], input[3], input[4], sprs.offsetW +
 * sprs.spriteW * input[5], sprs.offsetH + sprs.spriteH * input[6], sprs.offsetW
 * + sprs.spriteW * (input[5] + 1) - 1, sprs.offsetH + sprs.spriteH * (input[6]
 * + 1) - 1, null); }
 * 
 * else if(input[0]==-1) {
 * 
 * g.setColor(Color.WHITE); g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
 * g.drawString("Global killcount: " + info[0], 30, 30);
 * g.drawString("Experience: " + info[1], 30, 80);
 * 
 * panel.paintImmediately(panel.getBounds()); } else if(input[0]==-2){
 * g.setColor(new Color(input[1], input[2], input[3])); g.fillOval(input[4],
 * input[5], input[6], input[7]); } else if(input[0]==-3){ g.setColor(new
 * Color(input[1], input[2], input[3])); g.fillRect(input[4], input[5],
 * input[6], input[7]); } else if(input[0]==-4){ g.setColor(new Color(input[1],
 * input[2], input[3])); g.fillArc(input[4], input[5], input[6], input[7],
 * input[8], input[9]); } else if(input[0]==-5){ info[input[1]]=input[2]; } } }
 * catch (IOException e) { System.out.println(e + " : Connection was lost"); } }
 * }
 */
