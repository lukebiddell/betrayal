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
	
	
	public Random rand;
	
	public double maxSpawnTime;
	public double minSpawnTime;
	public double timeUntilSpawn;
	
	public int score = 0;
	
	public void spawnEntity(Entity e){
		entitiesWaiting.add(e);
	}
	public void spawnMonster(Monster e){
		monstersWaiting.add(e);
	}
	
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
		viewport.ppu = 180.0;
		
		
		rand = new Random();
		maxSpawnTime = 4.3;
		minSpawnTime = 1.1;
	}
	
	public void update(double delta, int w, int h){
		keyboard.poll();
		mouse.poll();
		
		player.update(delta, this);
		
		viewport.screenW = w;
		viewport.screenH = h;
		viewport.update(delta);
		
		if(keyboard.keyDown(KeyEvent.VK_UP)) viewport.ppu+=15.0*delta;
		if(keyboard.keyDown(KeyEvent.VK_DOWN)) viewport.ppu-=15.0*delta;
		
		if(mouse.isPressed(0) && player.hp>0){
			player.weapon[0].use(viewport.toGameCoord(mouse.getPos()));
		}
		if(mouse.isPressed(1) && player.hp>0){
			player.weapon[1].use(viewport.toGameCoord(mouse.getPos()));
		}
		
		monsters.addAll(monstersWaiting);
		monstersWaiting = new LinkedList<Monster>();
		entities.addAll(entitiesWaiting);
		entitiesWaiting = new LinkedList<Entity>();
		
		int l = player.weapon.length;
		for(int i=0;i<l;i++)player.weapon[i].update(delta);
		
		ListIterator<Monster> mit = monsters.listIterator(0);
		while(mit.hasNext()){Monster m = mit.next(); m.update(delta, this); if(m.disposable()) mit.remove();}
		ListIterator<Entity> eit = entities.listIterator(0);
		while(eit.hasNext()){Entity e = eit.next(); e.update(delta, this); if(e.disposable()) eit.remove();}
		
		
		if(timeUntilSpawn <= 0)
		{
			timeUntilSpawn = minSpawnTime + rand.nextDouble()*(maxSpawnTime - minSpawnTime);
			spawnMonster(new Monster(this));
		}
		else timeUntilSpawn -= delta;
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
		
		g.setColor(Color.BLACK);
		g.fillRect(30,60,100,10);
		g.setColor(Color.RED);
		g.fillRect(30,60,(int)(100 * player.hp / player.maxHp),10);
	}
}
