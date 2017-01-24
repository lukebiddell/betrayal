import java.awt.*;
import java.awt.geom.Point2D;

public class Viewport{
	private Point2D.Double pos;
	public double w;
	public double h;
	
	public double ppu;
	public int screenW;
	public int screenH;
	
	public Game game;
	public Player p;
	
	public Viewport(Game game, Player p){
		pos = new Point2D.Double(0,0);
		w = 0;
		h = 0;
		
		ppu = 1.0;
		screenW = 0;
		screenH = 0;
		
		this.game = game;
		this.p = p;
	}
	
	public void update(double delta){
		final double W = (double) screenW;
		final double H = (double) screenH;
		if(W<=game.roomW*ppu) pos.x = Math.min(Math.max(p.pos.x - W/(2*ppu), 0), game.roomW - W/ppu);
		else pos.x = (game.roomW - W/ppu)/2;
		if(H<=game.roomH*ppu) pos.y = Math.min(Math.max(p.pos.y - H/(2*ppu), 0), game.roomH - H/ppu);
		else pos.y = (game.roomH - H/ppu)/2;
		w = W/ppu;
		h = H/ppu;
	}
	
	public Point toScreenCoord(Point2D.Double gameCoord){
		return new Point((int)((gameCoord.x-pos.x)*ppu), (int)((gameCoord.y-pos.y)*ppu));
	}
	
	public Point2D.Double toGameCoord(Point screenCoord){
		return new Point2D.Double(screenCoord.x/ppu+pos.x, screenCoord.y/ppu+pos.y);
	}
	
	public void drawCircle(Point2D.Double c, double r, Color col, Graphics2D g){
		g.setColor(col);
		Point coord = toScreenCoord(new Point2D.Double(c.x-r, c.y-r));
		g.fillOval(coord.x, coord.y, scaleToScreen(2*r), scaleToScreen(2*r));
	}
	
	public int scaleToScreen(double val){return (int)(val*ppu);}
	public double scaleToGame(int val){return val/ppu;}
}
