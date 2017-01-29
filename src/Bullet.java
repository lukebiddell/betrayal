import java.awt.*;
import java.awt.geom.Point2D;

public class Bullet extends Projectile{
	
	public Bullet(Player player, Point2D.Double pos, Point2D.Double to, double size, double damage, double speed, double immunity){
		super(damage, new CircleSector(size, new Point2D.Double(pos.x, pos.y)), new Point2D.Double(0,0), immunity, player, false, 0, true);
		addBehaviour(new LinearMotion(speed, to, pos));
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(getPos(), getSize(), Color.CYAN, g);
	}
}
