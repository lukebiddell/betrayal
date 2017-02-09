package levels;

import java.util.ArrayList;

public abstract class Level {

	private int difficulty;
	
	private double roomW; // in game units
	private double roomH; // in game units
	
	private String backgroundImageLocation;
	private int backgroundImageW;
	private int backgroundImageH;
	
	private ArrayList<Wave> waveList = new ArrayList<Wave>();
	private ArrayList<Prop> propList = new ArrayList<Prop>();

	public Level() {
		this.roomW = 20;
		this.roomH = 16;
		//this.propList.add(new CardboardBox());
		//CardboardBox cb = new CardboardBox();
	}
	
	public Level(double roomW, double roomH) {
		this.roomW = roomW;
		this.roomH = roomH;
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
	
	public String getBackgroundImageLocation() {
		return backgroundImageLocation;
	}
	
	public int getBackgroundImageW() {
		return backgroundImageW;
	}
	
	public int getBackgroundImageH() {
		return backgroundImageH;
	}

}
