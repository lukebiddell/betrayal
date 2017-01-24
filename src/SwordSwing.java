import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class SwordSwing implements Entity{
	
	public Point2D.Double pos;
	public double damage;
	public double len;
	public double arcStart;
	public double arc;
	public double lifetime;
	public CircleSector hitbox;
	
	public SwordSwing(Game game, Point2D.Double pos, double damage, double len, double arcStart, double arc, double lifetime){
		this.pos = pos;
		this.damage = damage;
		this.len = len;
		this.arcStart = arcStart;
		this.arc = arc;
		this.lifetime = lifetime;
		this.hitbox = new CircleSector(len,pos,arcStart,arc);
		ListIterator<Monster> mit = game.monsters.listIterator(0);
		while(mit.hasNext()){
			Monster m = mit.next(); 
			if(hitbox.intersects(m.hitbox))m.hit(damage);
		}
	}
	
	public boolean disposable(){
		return lifetime<=0;
	}
	public void update(double delta, Game game){
		lifetime-=delta;
	}
	public void draw(Graphics2D g, Viewport viewport){
		g.setColor(Color.CYAN);
		Point coord = viewport.toScreenCoord(new Point2D.Double(pos.x-len, pos.y-len));
		g.fillArc(coord.x, coord.y, viewport.scaleToScreen(2*len), viewport.scaleToScreen(2*len), -(int)(arcStart*180/Math.PI), -(int)(arc*180/Math.PI));
	}
}
