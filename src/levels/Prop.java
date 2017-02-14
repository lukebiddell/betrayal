package levels;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;

import game.Circle;
import game.Viewport;

public abstract class Prop extends game.Entity {

	private static final String imageFolder = "../Resources/Images/";
	private String imageLocation; // e.g. "monster.png"
	
	//private boolean hasHitbox;
	private double width; //internal width not number of pixels
	private double height;
	//private boolean circleHitbox;
	//private double hitboxTop, hitboxBottom, hitboxLeft, hitboxRight;
	private Circle hitboxCircle;
	private Point2D.Double coordinates;
	private boolean disposable;

	
	public Prop() {
			
	}

	@Override
	public boolean disposable() {
		// TODO Auto-generated method stub
		return disposable;
	}

	@Override
	public void draw(Graphics2D g, Viewport viewport) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D.Double getPos(){
		return coordinates;
		
	}
	
	public Image getImage() {
		BufferedImage image = null;
		try {
		    image = ImageIO.read(new File(imageFolder + imageLocation));
		} catch (IOException e) {
		}
		return image;
	}
	

	/**
	 * @return the full address of imageLocation
	 */
	public String getImageLocationFull() {
		return imageFolder + imageLocation;
	}

	/**
	 * @param imageLocation the imageLocation to set
	 */
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int imageW) {
		this.width = imageW;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int imageH) {
		this.height = imageH;
	}

	/**
	 * @return the imageLocation
	 */
	public String getImageLocation() {
		return imageLocation;
	}

/*	/**
	 * @param hitboxRight the hitboxRight to set
	 *//*
	public void setHitboxCoords(double hitboxTop, double hitboxBottom, double hitboxLeft, double hitboxRight) {
		this.hitboxTop = hitboxTop;
		this.hitboxBottom = hitboxBottom;
		this.hitboxLeft = hitboxLeft;
		this.hitboxRight = hitboxRight;
	}*/

	public Point2D.Double getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point2D.Double coordinates) {
		this.coordinates = coordinates;
	}

}
