package levels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Circle;
import game.Viewport;

public abstract class Level {

	private int difficulty;

	private double roomW; // in game units
	private double roomH; // in game units

	private static final String imageFolder = "Resources/Images/";
	private String backgroundImageLocation;
	private int backgroundImageW;
	private int backgroundImageH;

	private ArrayList<Wave> waveList = new ArrayList<Wave>();
	private ArrayList<Prop> propList = new ArrayList<Prop>();

	/*public Level() {
		this.roomW = 20;
		this.roomH = 16;
		// this.propList.add(new CardboardBox());
		// CardboardBox cb = new CardboardBox();
	}*/

	public Level(double roomW, double roomH) {
		this.roomW = roomW;
		this.roomH = roomH;
	}
	
	protected void addProp(Prop p){
		propList.add(p);
	}

	public void draw(Graphics2D g, Viewport vp) {
		for (Prop p : propList){
			p.draw(g, vp);
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
	 * @param c Circle of hitbox
	 * @return true if valid position
	 */
	public Boolean validPos(Circle c){
		for (Prop p : propList){
			if(c.intersects(p.getDestination()))
				return false;
		}
		
		return true;
		
	}
}
