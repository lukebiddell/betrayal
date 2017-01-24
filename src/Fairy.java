import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Fairy implements Entity{
	
	public double lifetime;
	public double size;
	public double damage;
	public double acceleration;
	public Player player;
	public Game game;
	
	public Point2D.Double pos;
	public Point2D.Double speed;
	public Circle hitbox;
	
	public Fairy(double lifetime, double size, double damage, double acceleration, Point2D.Double pos, Player player, Game game){
		this.lifetime = lifetime;
		this.size = size;
		this.damage = damage;
		this.acceleration = acceleration;
		this.pos = pos;
		this.player = player;
		this.game = game;
		
		speed = new Point2D.Double(0,0);
		hitbox = new Circle(size, pos);
	}
	
	public boolean disposable(){
		return lifetime<=0;
	}
	public void update(double delta, Game game){
		lifetime -= delta;
		
		final double dx = player.pos.x - pos.x;
		final double dy = player.pos.y - pos.y;
		
		final double d = Math.sqrt(dx*dx+dy*dy);
		
		speed.x += dx*acceleration*delta/d;
		speed.y += dy*acceleration*delta/d;
		
		pos.x += speed.x*delta;
		pos.y += speed.y*delta;
		
		ListIterator<Monster> mit = game.monsters.listIterator(0);
		while(mit.hasNext()){
			Monster m = mit.next(); 
			if(hitbox.intersects(m.hitbox))m.hit(damage);
		}
	}
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(pos, size, Color.YELLOW, g);
	}
	
	public static void spawn(double lifetime, double size, double damage, double acceleration, Point2D.Double pos, Player player, Game game){
		game.entitiesWaiting.add(new Fairy(lifetime, size, damage, acceleration, pos, player, game));
	}
}
