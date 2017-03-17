package levels;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.Animation;
import game.Circle;
import game.Game;
import game.Spritesheet;
import game.Viewport;
import game.SpritesheetEnum;

public abstract class Tile extends game.Entity {

	private boolean collision;
	// private double width; //internal width not number of pixels
	// private double height;
	// private boolean circleHitbox;
	// private double hitboxTop, hitboxBottom, hitboxLeft, hitboxRight;
	// private Circle hitboxCircle;
	// private Point2D.Double coordinates;

	private Rectangle2D.Double dest;
	private Spritesheet ss;
	private Animation anim;
	private ArrayList<Point2D> positions;

	public Tile() {
		positions = new ArrayList<Point2D>();
	}

	public Tile(int col, int row) {
		positions = new ArrayList<Point2D>();
	}
	
	@Override
	public Double getPos() {
		// TODO Auto-generated method stub
		return null;
	}

	protected Rectangle2D.Double getDestination() {
		return dest;
	}

	protected void setDestination(Rectangle2D.Double dest) {
		this.dest = dest;
	}

	protected Spritesheet getSpritesheet() {
		return ss;
	}

	protected void setSpritesheet(Spritesheet ss) {
		this.ss = ss;
	}

	protected Animation getAnimation() {
		return anim;
	}

	protected void setAnimation(Animation anim) {
		this.anim = anim;
	}

	public Spritesheet getSprite() {
		return ss;
	}

	@Override
	public boolean disposable() {
		return false;
	}

	@Override
	public void draw(Graphics2D g, Viewport viewport) {
		double height = viewport.game.roomH;
		viewport.drawSprite(dest, anim, g);
	}
}
