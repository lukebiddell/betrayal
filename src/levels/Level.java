package levels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sql.rowset.CachedRowSet;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

import game.Circle;
import game.Viewport;

public class Level {

	private static final boolean dodebug = true;
	private int difficulty;

	private double roomW; // in game units
	private double roomH; // in game units

	private static final String imageFolder = "Resources/Images/";
	private String backgroundImageLocation;
	private int backgroundImageW;
	private int backgroundImageH;

	private ArrayList<Wave> waveList = new ArrayList<Wave>();
	private ArrayList<Tile> tileList = new ArrayList<Tile>();

	/*
	 * public Level() { this.roomW = 20; this.roomH = 16; //
	 * this.propList.add(new CardboardBox()); // CardboardBox cb = new
	 * CardboardBox(); }
	 */

	public Level(double roomW, double roomH) {
		this.roomW = roomW;
		this.roomH = roomH;
	}

	public Level(String xml) throws ParserConfigurationException {
		parseXML(xml);
	}

	private void debug(String s) {
		if (dodebug)
			System.out.println(s);

	}

	private void parseXML(String fileName) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File(fileName);

			Document doc = builder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList tilesetNodes = doc.getElementsByTagName("tileset");

			for (int i = 0; i < tilesetNodes.getLength(); i++) {
				Node tilesetNode = tilesetNodes.item(i);
				System.out.println("\nCurrent Element :" + tilesetNode.getNodeName());

				Element tilesetElement = (Element) tilesetNode;
				boolean collision = tilesetElement.getAttribute("collision").trim().contentEquals("1");
				System.out.println("Collision = " + collision);
				NodeList tileNodes = tilesetElement.getElementsByTagName("tile");
				;

				for (int j = 0; j < tileNodes.getLength(); j++) {
					// System.out.println("\nCurrent Element :" +
					// tilesetNode.getNodeName());
					Node tileNode = tileNodes.item(j);
					Element tileElement = (Element) tileNode;

					debug(tileElement.getAttribute("name"));

					int x = Integer.parseInt(tileElement.getAttribute("x").trim());
					int y = Integer.parseInt(tileElement.getAttribute("y").trim());
					char c = tileElement.getAttribute("char").charAt(0);

					debug("x = " + String.valueOf(x));
					debug("y = " + String.valueOf(y));
					debug("c = " + String.valueOf(c));
					debug("");
				}

			}

			NodeList mapNodes = doc.getElementsByTagName("map");

			for (int i = 0; i < mapNodes.getLength(); i++) {
				Node mapNode = mapNodes.item(i);
				System.out.println("\nCurrent Element :" + mapNode.getNodeName());

				Element mapElement = (Element) mapNode;
				int layer = Integer.parseInt(mapElement.getAttribute("layer").trim());

				System.out.println("Layer = " + layer);
				String mapStr = mapElement.getTextContent();
				debug(mapStr);
				
				
				
				String[] mapLines = mapStr.split("\\r?\\n");
				for (int temp = 0; temp < mapLines.length; temp++){
					System.out.println(mapLines[temp]);
				}
				
				
				/*
				 * for (int j = 0; j < tileNodes.getLength(); j++) { //
				 * System.out.println("\nCurrent Element :" + //
				 * tilesetNode.getNodeName()); Node tileNode =
				 * tileNodes.item(j); Element tileElement = (WElement) tileNode;
				 * 
				 * debug(tileElement.getAttribute("name"));
				 * 
				 * int x =
				 * Integer.parseInt(tileElement.getAttribute("x").trim()); int y
				 * = Integer.parseInt(tileElement.getAttribute("y").trim());
				 * char c = tileElement.getAttribute("char").charAt(0);
				 * 
				 * debug("x = " + String.valueOf(x)); debug("y = " +
				 * String.valueOf(y)); debug("c = " + String.valueOf(c));
				 * debug(""); }
				 */

			}

		} catch (ParserConfigurationException e) {
			// Error creating builder
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// Error getting input
			e.printStackTrace();
		} catch (SAXException e) {
			// Error parsing
			e.printStackTrace();
		} catch (IOException e) {
			// Error parsing
			e.printStackTrace();
		}

	}

	protected void addTile(Tile t) {
		tileList.add(t);
	}

	public void draw(Graphics2D g, Viewport vp) {
		for (Tile t : tileList) {
			t.draw(g, vp);
		}
		return;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public double getRoomW() {
		return roomW;
	}

	public double getRoomH() {
		return roomH;
	}

	public String getImageLocation() {
		return imageFolder + backgroundImageLocation;
	}

	public Image getBackgroundImage() {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(imageFolder + backgroundImageLocation));
		} catch (IOException e) {
		}
		return image;
	}

	public int getBackgroundImageW() {
		return backgroundImageW;
	}

	public int getBackgroundImageH() {
		return backgroundImageH;
	}

	/**
	 * @param c
	 *            Circle of hitbox
	 * @return true if valid position
	 */
	public Boolean validPos(Circle c) {
		for (Tile t : tileList) {
			if (c.intersects(t.getDestination()))
				return false;
		}

		return true;

	}
}
