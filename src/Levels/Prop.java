package Levels;

import java.awt.Point;

public abstract class Prop {

	private static final String imageFolder = "../Resources/Images/";
	private String imageLocation; // e.g. "monster.png"
	
	private boolean hasHitbox;
	private int imageW;
	private int imageH;
	private boolean circleHitbox;
	private double hitboxTop, hitboxBottom, hitboxLeft, hitboxRight;
	

	
	public Prop() {

	}

	/**
	 * @return the hasHitbox
	 */
	protected boolean isHasHitbox() {
		return hasHitbox;
	}

	/**
	 * @param hasHitbox the hasHitbox to set
	 */
	protected void setHasHitbox(boolean hasHitbox) {
		this.hasHitbox = hasHitbox;
	}

	/**
	 * @return the full address of imageLocation
	 */
	protected String getImageLocationFull() {
		return imageFolder + imageLocation;
	}

	/**
	 * @param imageLocation the imageLocation to set
	 */
	protected void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	/**
	 * @return the imageW
	 */
	protected int getImageW() {
		return imageW;
	}

	/**
	 * @param imageW the imageW to set
	 */
	protected void setImageW(int imageW) {
		this.imageW = imageW;
	}

	/**
	 * @return the imageH
	 */
	protected int getImageH() {
		return imageH;
	}

	/**
	 * @param imageH the imageH to set
	 */
	protected void setImageH(int imageH) {
		this.imageH = imageH;
	}

	/**
	 * @return the circleHitbox
	 */
	protected boolean isCircleHitbox() {
		return circleHitbox;
	}

	/**
	 * @param circleHitbox the circleHitbox to set
	 */
	protected void setCircleHitbox(boolean circleHitbox) {
		this.circleHitbox = circleHitbox;
	}

	/**
	 * @return the imageLocation
	 */
	protected String getImageLocation() {
		return imageLocation;
	}

	/**
	 * @return the hitboxTop
	 */
	protected double getHitboxTop() {
		return hitboxTop;
	}

	/**
	 * @param hitboxTop the hitboxTop to set
	 */
	protected void setHitboxTop(double hitboxTop) {
		this.hitboxTop = hitboxTop;
	}

	/**
	 * @return the hitboxBottom
	 */
	protected double getHitboxBottom() {
		return hitboxBottom;
	}

	/**
	 * @param hitboxBottom the hitboxBottom to set
	 */
	protected void setHitboxBottom(double hitboxBottom) {
		this.hitboxBottom = hitboxBottom;
	}

	/**
	 * @return the hitboxLeft
	 */
	protected double getHitboxLeft() {
		return hitboxLeft;
	}

	/**
	 * @param hitboxLeft the hitboxLeft to set
	 */
	protected void setHitboxLeft(double hitboxLeft) {
		this.hitboxLeft = hitboxLeft;
	}

	/**
	 * @return the hitboxRight
	 */
	protected double getHitboxRight() {
		return hitboxRight;
	}

	/**
	 * @param hitboxRight the hitboxRight to set
	 */
	protected void setHitboxRight(double hitboxRight) {
		this.hitboxRight = hitboxRight;
	}
	
	/**
	 * @param hitboxRight the hitboxRight to set
	 */
	protected void setHitboxCoords(double hitboxTop, double hitboxBottom, double hitboxLeft, double hitboxRight) {
		this.hitboxTop = hitboxTop;
		this.hitboxBottom = hitboxBottom;
		this.hitboxLeft = hitboxLeft;
		this.hitboxRight = hitboxRight;
	}

}
