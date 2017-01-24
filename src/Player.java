import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;

public class Player{
	public Point2D.Double pos;
	public double size;
	public double speed;
	public Game game;
	
	public Weapon weapon;
	
	public Player(Game game){
		this.game = game;
		pos = new Point2D.Double(1.0,1.0);
		size = 0.5;
		speed = 1.0;
		weapon = new Sword();
	}
	
	public void update(double delta){
		if(game.keyboard.keyDown(KeyEvent.VK_W))pos.y = Math.max(pos.y - speed*delta, size);
		if(game.keyboard.keyDown(KeyEvent.VK_A))pos.x = Math.max(pos.x - speed*delta, size);
		if(game.keyboard.keyDown(KeyEvent.VK_S))pos.y = Math.min(pos.y + speed*delta, game.roomH-size);
		if(game.keyboard.keyDown(KeyEvent.VK_D))pos.x = Math.min(pos.x + speed*delta, game.roomW-size);
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircle(pos, size, Color.RED, g);
	}
}
