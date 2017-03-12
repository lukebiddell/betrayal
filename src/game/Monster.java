package game;

import weapons.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import audio.SFX;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Monster extends Entity{
	public Point2D.Double pos;
	public double size;
	public Circle hitbox;
	public double hp;
	public double maxHp;
	public boolean dead;
	public Game game;
	
	public Projectile lastHitBy;
	public TreeMap<Projectile, Double> immunities;
	
	private Random random;
	
	public Animation anim;
	
	public Monster(Game game){
		super();
		random = new Random();
		this.game = game;
		pos = new Point2D.Double(random.nextDouble()*game.roomW, random.nextDouble()*game.roomH);
		size = 0.1;
		hp = 100;
		maxHp = hp;
		dead = false;
		hitbox = new Circle(size, pos);
		lastHitBy = null;
		immunities = new TreeMap<Projectile, Double>();
		
		anim = new Animation(SpritesheetEnum.MONSTER,0,0,0.5,Animation.AnimationMode.LOOP);
		
		try{
		 Player towards = game.players.peek();
		 if(towards == null) throw new NoSuchElementException();
			//addBehaviour(new LinearHome(0.18, towards.getPos()));
		}
		catch(NoSuchElementException e){}
		
		addBehaviour(new JumpAround(2.8, 0.4));	
	}
	
	public void hit(Projectile p){
		if(!immunities.containsKey(p)){
			//decrease damage
			hp -= p.damage;
			
			//apply knockback
			addBehaviour(new Knockback(p.getKnockback(), 0.1));
			
			//make it immune to this object
			immunities.put(p, new Double(p.immunityTime));
		}
		lastHitBy = p;
	}
	
	public boolean disposable(){
		return dead;
	}
	
	public Point2D.Double getPos(){
		return pos;
	}
	
	public void update(double delta, Game game){
		if(hp<=0){
			SFX deathSound = new SFX(100, "/Music/SFX_Monster_3.wav");
			deathSound.play();
			dead = true; 
			game.score++;
			try{
				if(random.nextDouble()<0.33)game.spawnEntity(new Fairy(7.0, 0.1, 10, 0.2, 2.8, new Point2D.Double(pos.x, pos.y), lastHitBy.player, game));
				if(random.nextDouble()<0.1){
					Weapon w = null;
					double wchance = random.nextDouble();
					if(wchance<0.33){
						w = new Sword(game,null);
					} else if(wchance<0.66){
						w = new Laser(game,null);
					} else {
						w = new Gun(game,null);
					}
					game.spawnEntity(new WeaponDrop((Point2D.Double)(pos.clone()),w));
				}
			}
			catch(NullPointerException e){}
		}
		
		super.update(delta, game);
		
		Iterator<Map.Entry<Projectile, Double>> pIt = immunities.entrySet().iterator();
		LinkedList<Projectile> imtorem = new LinkedList<Projectile>();
		while(pIt.hasNext()){
			Map.Entry<Projectile, Double> e = pIt.next();
			e.setValue(e.getValue() - delta);
			if(e.getValue()<=0)imtorem.add(e.getKey());
		}
		Iterator<Projectile> imtoremit = imtorem.iterator();
		while(imtoremit.hasNext())immunities.remove(imtoremit.next());
		
		anim.update(delta);
	}
	
	public void draw(Graphics2D g, Viewport viewport){
		double hpBarH = 0.08;
	
		viewport.drawCircleSprite(pos, size, anim, g);
		//Point coord = viewport.toScreenCoord(new Point2D.Double(pos.x-size, pos.y-size));
		
		//g.setColor(Color.BLACK);
		//g.fillRect(coord.x, coord.y-viewport.scaleToScreen(hpBarH), viewport.scaleToScreen(2*size), viewport.scaleToScreen(hpBarH));
		viewport.drawRect(new Point2D.Double(pos.x-size, pos.y-size-hpBarH), 2*size, hpBarH, Color.BLACK, g);
		//g.setColor(Color.RED);
		//g.fillRect(coord.x, coord.y-viewport.scaleToScreen(hpBarH), viewport.scaleToScreen(2*size*hp/maxHp), viewport.scaleToScreen(hpBarH));
		viewport.drawRect(new Point2D.Double(pos.x-size, pos.y-size-hpBarH), 2*size*hp/maxHp, hpBarH, Color.RED, g);
	}
}
