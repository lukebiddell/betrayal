package levels;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Prop {

	private static final String imageFolder = "../Resources/Images/";
	private String imageLocation; // e.g. "monster.png"
	
	private boolean hasHitbox;
	private int imageW;
	private int imageH;
	private boolean circleHitbox;
	private double hitboxTop, hitboxBottom, hitboxLeft, hitboxRight;
	private Point2D.Double coordinates;

	
	public Prop() {

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
	 * @return the hasHitbox
	 */
	public boolean isHasHitbox() {
		return hasHitbox;
	}

	/**
	 * @param hasHitbox the hasHitbox to set
	 */
	public void setHasHitbox(boolean hasHitbox) {
		this.hasHitbox = hasHitbox;
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
	 * @return the imageW
	 */
	public int getImageW() {
		return imageW;
	}

	/**
	 * @param imageW the imageW to set
	 */
	public void setImageW(int imageW) {
		this.imageW = imageW;
	}

	/**
	 * @return the imageH
	 */
	public int getImageH() {
		return imageH;
	}

	/**
	 * @param imageH the imageH to set
	 */
	public void setImageH(int imageH) {
		this.imageH = imageH;
	}

	/**
	 * @return the circleHitbox
	 */
	public boolean isCircleHitbox() {
		return circleHitbox;
	}

	/**
	 * @param circleHitbox the circleHitbox to set
	 */
	public void setCircleHitbox(boolean circleHitbox) {
		this.circleHitbox = circleHitbox;
	}

	/**
	 * @return the imageLocation
	 */
	public String getImageLocation() {
		return imageLocation;
	}

	/**
	 * @return the hitboxTop
	 */
	public double getHitboxTop() {
		return hitboxTop;
	}

	/**
	 * @param hitboxTop the hitboxTop to set
	 */
	public void setHitboxTop(double hitboxTop) {
		this.hitboxTop = hitboxTop;
	}

	/**
	 * @return the hitboxBottom
	 */
	public double getHitboxBottom() {
		return hitboxBottom;
	}

	/**
	 * @param hitboxBottom the hitboxBottom to set
	 */
	public void setHitboxBottom(double hitboxBottom) {
		this.hitboxBottom = hitboxBottom;
	}

	/**
	 * @return the hitboxLeft
	 */
	public double getHitboxLeft() {
		return hitboxLeft;
	}

	/**
	 * @param hitboxLeft the hitboxLeft to set
	 */
	public void setHitboxLeft(double hitboxLeft) {
		this.hitboxLeft = hitboxLeft;
	}

	/**
	 * @return the hitboxRight
	 */
	public double getHitboxRight() {
		return hitboxRight;
	}

	/**
	 * @param hitboxRight the hitboxRight to set
	 */
	public void setHitboxRight(double hitboxRight) {
		this.hitboxRight = hitboxRight;
	}
	
	/**
	 * @param hitboxRight the hitboxRight to set
	 */
	public void setHitboxCoords(double hitboxTop, double hitboxBottom, double hitboxLeft, double hitboxRight) {
		this.hitboxTop = hitboxTop;
		this.hitboxBottom = hitboxBottom;
		this.hitboxLeft = hitboxLeft;
		this.hitboxRight = hitboxRight;
	}

	public Point2D.Double getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Point2D.Double coordinates) {
		this.coordinates = coordinates;
	}

}
