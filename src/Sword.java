import java.awt.geom.Point2D;

public class Sword implements Weapon{
	public double damage;
	public double len;
	public double arc;
	
	public Sword(){
		damage = 30;
		len = 0.75;
		arc = Math.PI/2;
	}
	
	public void use(Game game, Player p, Point2D.Double pos){
		double arcStart = Math.atan2(pos.y-p.pos.y,pos.x-p.pos.x)-arc/2;
		while(arcStart<0)arcStart += 2*Math.PI;
		game.entitiesWaiting.add(new SwordSwing(game, p.pos, damage, len, arcStart, arc, 0.6));
	}
}
