package game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.event.KeyEvent;

public class FirstAid extends Entity{

	public double heals;
	public Circle hitbox;
	public boolean delete;
	public Animation anim;
	
	public FirstAid(double heals, Point2D.Double pos, double size){
		this.heals = heals;
		this.hitbox = new Circle(size, pos);
		delete = false;
		anim = new Animation(SpritesheetEnum.HP,0,0,0.1,Animation.AnimationMode.PLAYONCE);
	}
	
	@Override
	public boolean disposable(){
		return delete;
	}
	
	@Override
	public Point2D.Double getPos(){
		return hitbox.center;
	}
	
	@Override
	public void playerInteracted(Player p, int event){
		if(event == KeyEvent.VK_E && p.hitbox.intersects(hitbox)){
			p.hp = Math.min(p.hp + heals, p.maxHp);
			delete = true;
		}
	}
	
	@Override
	public void draw(Graphics2D g, Viewport viewport){
		viewport.drawCircleSprite(getPos(), hitbox.getRadius(), anim,g);
	}
	
	@Override
	public Entity clone(){
		FirstAid fa = new FirstAid(heals, (Point2D.Double)(getPos().clone()), hitbox.getRadius());
		cloneBehaviour(fa);
		return fa;
	}
}
