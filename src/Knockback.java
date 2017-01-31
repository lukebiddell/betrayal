import java.awt.geom.Point2D;

public class Knockback implements Behaviour{
	
	Point2D.Double displacement;
	Point2D.Double pos;
	double time;
	double timer;
	
	public Knockback(Point2D.Double displacement, Point2D.Double pos, double time){
		this.displacement = displacement;
		this.pos = pos;//no ref pls
		this.time = time;
		timer = 0;
	}


	public boolean disposable(){
		return timer >= time;
	}
	
	public void update(Game game, Point2D.Double pos, double delta){
		timer += delta;
		
		pos.x = this.pos.x + (timer/time) * displacement.x;
		pos.y = this.pos.y + (timer/time) * displacement.y;
	}
}
