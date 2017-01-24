import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class Game{
	
	public boolean isRunning;
	
	public KeyboardInput keyboard;
	public MouseInput mouse;
	
	public double roomW;//in game units
	public double roomH;//in game units
	public Player player;
	public List<Monster> monsters;
	public List<Entity> entities;
	public List<Monster> monstersWaiting;
	public List<Entity> entitiesWaiting;
	
	private Viewport viewport;
	
	
	public int score = 0;
	
	public Game(KeyboardInput keyboard, MouseInput mouse){
		isRunning = true;
		this.keyboard = keyboard;
		this.mouse = mouse;
		
		player = new Player(this);
		monsters = new LinkedList<Monster>();
		entities = new LinkedList<Entity>();
		monstersWaiting = new LinkedList<Monster>();
		entitiesWaiting = new LinkedList<Entity>();
		
		roomW = 5.0;
		roomH = 3.0;
		
		viewport = new Viewport(this, player);
		viewport.ppu = 100.0;
		
		Monster.spawn(this);
		Monster.spawn(this);
		Monster.spawn(this);
	}
	
	public void update(double delta, int w, int h){
		keyboard.poll();
		mouse.poll();
		
		player.update(delta);
		
		viewport.screenW = w;
		viewport.screenH = h;
		viewport.update(delta);
		
		if(keyboard.keyDown(KeyEvent.VK_UP)) viewport.ppu+=15.0*delta;
		if(keyboard.keyDown(KeyEvent.VK_DOWN)) viewport.ppu-=15.0*delta;
		
		if(mouse.isPressedOnce(0)){
			player.weapon.use(this, player, viewport.toGameCoord(mouse.getPos()));
		}
		
		monsters.addAll(monstersWaiting);
		monstersWaiting = new LinkedList<Monster>();
		entities.addAll(entitiesWaiting);
		entitiesWaiting = new LinkedList<Entity>();
		
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext())mit.next().update(delta);
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext()){Entity e = eit.next(); e.update(delta, this); if(e.disposable()) eit.remove();}
		
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,viewport.screenW,viewport.screenH);
		
		g.setColor(Color.GREEN);
		Point roomCoord = viewport.toScreenCoord(new Point2D.Double(0,0));
		g.fillRect(roomCoord.x, roomCoord.y, (int)(roomW*viewport.ppu), (int)(roomH*viewport.ppu));
		
		player.draw(g, viewport);
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext())mit.next().draw(g, viewport);
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext())eit.next().draw(g, viewport);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString(Integer.toString(score), 30, 30);
	}
}
