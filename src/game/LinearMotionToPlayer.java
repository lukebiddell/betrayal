package game;
import java.awt.geom.Point2D;
import java.util.NoSuchElementException;

public class LinearMotionToPlayer extends LinearMotion{
	
	boolean live = false;
	double speed;
	
	public LinearMotionToPlayer(double speed, Point2D.Double towards, Point2D.Double pos){
		super(speed, towards, pos);
		this.speed = speed;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		if(!live){
			
			try{
				Player tow = game.players.peek();
				if(tow == null) throw new NoSuchElementException();
			
				dir = (new LinearMotion(speed, tow.getPos(), pos)).dir;
			}
			catch(NoSuchElementException e){System.err.println("please dont leave exceptions empty");}
			live = true;
		}
		pos.x += dir.x*delta;
		pos.y += dir.y*delta;
	}
	
	public Behaviour clone(){
		return new LinearMotionToPlayer(speed, new Point2D.Double(), new Point2D.Double());
	}
}
