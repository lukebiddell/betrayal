package game;
import java.awt.geom.Point2D;

public class LinearMotion implements Behaviour{

	public Point2D.Double dir;
	
	/**
	 * 
	 * @param speed		The speed of object
	 * @param towards	The position of player
	 * @param pos		The position of mouse cursor
	 */
	public LinearMotion(double speed, Point2D.Double towards, Point2D.Double pos){
		final double r = Math.sqrt((pos.x - towards.x)*(pos.x - towards.x) + (pos.y - towards.y)*(pos.y - towards.y));
		dir = new Point2D.Double((towards.x-pos.x)*speed/r, (towards.y-pos.y)*speed/r);
	}
	
	public boolean disposable(){
		return false;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		pos.x += dir.x*delta;
		pos.y += dir.y*delta;
	}
	
	
	public Behaviour clone(){
		LinearMotion lm = new LinearMotion(3, new Point2D.Double(1,1), new Point2D.Double(2,2));
		lm.dir = dir;
		return lm;
	}
}
