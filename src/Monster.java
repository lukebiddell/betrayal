import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;

public class Monster{
	public Point2D.Double pos;
	public double size;
	public Circle hitbox;
	public double hp;
	public double maxHp;
	public boolean dead;
	public Game game;
	
	private Random random;
	
	public Monster(Game game){
		random = new Random();
		this.game = game;
		pos = new Point2D.Double(random.nextDouble()*game.roomW, random.nextDouble()*game.roomH);
		size = 0.2;
		hp = 100;
		maxHp = hp;
		dead = false;
		hitbox = new Circle(size, pos);
	}
	
	public static void spawn(Game game){
		game.monstersWaiting.add(new Monster(game));
	}
	
	public void hit(double damage){
		hp-=damage;
	}
	
	public void update(double delta){
		if(hp<=0 && !dead){dead = true; Monster.spawn(game); game.score++; Fairy.spawn(6.5, 0.1, 8, 1.0, new Point2D.Double(pos.x, pos.y), game.player, game);}
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		double hpBarH = 0.08;
	
		viewport.drawCircle(pos, size, (dead?Color.BLUE:Color.WHITE), g);
		Point coord = viewport.toScreenCoord(new Point2D.Double(pos.x-size, pos.y-size));
		
		if(!dead){
			g.setColor(Color.BLACK);
			g.fillRect(coord.x, coord.y-viewport.scaleToScreen(hpBarH), viewport.scaleToScreen(2*size), viewport.scaleToScreen(hpBarH));
			g.setColor(Color.RED);
			g.fillRect(coord.x, coord.y-viewport.scaleToScreen(hpBarH), viewport.scaleToScreen(2*size*hp/maxHp), viewport.scaleToScreen(hpBarH));
    	}
	}
}
