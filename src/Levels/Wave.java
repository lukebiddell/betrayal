package Levels;

import java.util.ArrayList;

public abstract class Wave {
	ArrayList<ArrayList<Game.Monster>> gateList = new ArrayList<ArrayList<Game.Monster>>();
	
	public Wave() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Game.Monster> getMonsters (int gateID ){
		return gateList.get(gateID);		
	}

}
