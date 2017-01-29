import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ListIterator;
import java.util.LinkedList;

public class Player extends Entity{
	public Point2D.Double pos;
	public double size;
	public double speed;
	public Game game;
	public Circle hitbox;
	
	public double maxHp;
	public double hp;
	public double immunityTime;
	public double maxImmunityTime;
	
	public Weapon[] weapon;
	
	public Player(Game game){
		super();
		this.game = game;
		pos = new Point2D.Double(1.0,1.0);
		size = 0.35;
		speed = 1.0;
		weapon = new Weapon[2];
		weapon[0] = new Sword(game, this);
		weapon[1] = new Gun(game, this);
		hitbox = new Circle(size, pos);
		maxHp = 100;
		hp = maxHp;
		maxImmunityTime = 0.7;
		immunityTime = 0;
	}
	
	@Override
	public void update(double delta, Game game){
		super.update(delta, game);
		if(hp>0){
			if(game.keyboard.keyDown(KeyEvent.VK_W))pos.y = Math.max(pos.y - speed*delta, size);
			if(game.keyboard.keyDown(KeyEvent.VK_A))pos.x = Math.max(pos.x - speed*delta, size);
			if(game.keyboard.keyDown(KeyEvent.VK_S))pos.y = Math.min(pos.y + speed*delta, game.roomH-size);
			if(game.keyboard.keyDown(KeyEvent.VK_D))pos.x = Math.min(pos.x + speed*delta, game.roomW-size);
		
			if(immunityTime>0)immunityTime -= delta;
			ListIterator<Monster> mit = game.monsters.listIterator(0);
			while(mit.hasNext()){
				Monster m = mit.next(); 
				if(hitbox.intersects(m.hitbox)){if(immunityTime<=0){hp -= 10; immunityTime = maxImmunityTime;}}
			}
		}
	}
	
	@Override
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(pos, size, ((hp>0)?Color.RED:Color.BLUE), g);
	}
	
	@Override
	public Point2D.Double getPos(){
		return pos;
	}
	
	@Override
	public boolean disposable(){
		return false;
	}
}
