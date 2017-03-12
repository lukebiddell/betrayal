package game;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import network.Server;
import levels.Prop;

public class Viewport{
	private Point2D.Double pos;
	public double w;
	public double h;
	
	public double ppu;
	public int screenW;
	public int screenH;
	
	public Game game;
	public Player p;
	
	public Server server;
	
	public Viewport(Game game, Player p){
		pos = new Point2D.Double(0,0);
		w = 0;
		h = 0;
		
		ppu = 180.0;
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
	
	public void drawRect(Point2D.Double pos, double wi, double he, Color col, Graphics2D g){
		//g.setColor(col);
		Point coord = toScreenCoord(pos);
		server.addToQueue(-3);
		server.addToQueue(col.getRed());
		server.addToQueue(col.getGreen());
		server.addToQueue(col.getBlue());
		server.addToQueue(coord.x);
		server.addToQueue(coord.y);
		server.addToQueue(scaleToScreen(wi));
		server.addToQueue(scaleToScreen(he));
		server.addToQueue(0);
		server.addToQueue(0);
		
	}
	
	public void drawCircle(Point2D.Double c, double r, Color col, Graphics2D g){
		//g.setColor(col);
		Point coord = toScreenCoord(new Point2D.Double(c.x-r, c.y-r));
		//g.fillOval(coord.x, coord.y, scaleToScreen(2*r), scaleToScreen(2*r));
		server.addToQueue(-2);
		server.addToQueue(col.getRed());
		server.addToQueue(col.getGreen());
		server.addToQueue(col.getBlue());
		server.addToQueue(coord.x);
		server.addToQueue(coord.y);
		server.addToQueue(scaleToScreen(2*r));
		server.addToQueue(scaleToScreen(2*r));
		server.addToQueue(0);
		server.addToQueue(0);
		
	}
	
	public void drawCircleSector(Point2D.Double c, double r, double arcStart, double arcLen, Color col, Graphics2D g){
		//g.setColor(col);
		Point coord = toScreenCoord(new Point2D.Double(c.x-r, c.y-r));
		//g.fillOval(coord.x, coord.y, scaleToScreen(2*r), scaleToScreen(2*r));
		server.addToQueue(-4);
		server.addToQueue(col.getRed());
		server.addToQueue(col.getGreen());
		server.addToQueue(col.getBlue());
		server.addToQueue(coord.x);
		server.addToQueue(coord.y);
		server.addToQueue(scaleToScreen(2*r));
		server.addToQueue(scaleToScreen(2*r));
		server.addToQueue(-(int)(arcStart*180/Math.PI));
		server.addToQueue(-(int)(arcLen*180/Math.PI));
		
	}
	
	public void drawCircleSprite(Point2D.Double c, double r, Animation a, Graphics2D g){
		Point coord = toScreenCoord(new Point2D.Double(c.x-r, c.y-r));
		
		/*Spritesheet ss = SpritesheetEnum.getSprite(a.ss);
		
		g.drawImage(ss.img,
			coord.x, coord.y, coord.x + scaleToScreen(2*r) - 1, coord.y + scaleToScreen(2*r) - 1,
			ss.offsetW + ss.spriteW * a.frame, ss.offsetH + ss.spriteH * a.set, ss.offsetW + ss.spriteW * (a.frame + 1) - 1, ss.offsetH + ss.spriteH * (a.set + 1) - 1,
			null);*/
		server.addToQueue(a.ss);
		server.addToQueue(coord.x);
		server.addToQueue(coord.y);
		server.addToQueue(coord.x + scaleToScreen(2*r) - 1);
		server.addToQueue(coord.y + scaleToScreen(2*r) - 1);
		server.addToQueue(/*a.ss.offsetW + a.ss.spriteW * */a.frame);
		server.addToQueue(/*a.ss.offsetH + a.ss.spriteH * */a.set);
		//server.addToQueue(a.ss.offsetW + a.ss.spriteW * (a.frame + 1) - 1);
		//server.addToQueue(a.ss.offsetH + a.ss.spriteH * (a.set + 1) - 1);
		server.addToQueue(0);
		server.addToQueue(0);
		server.addToQueue(0);
	}
	
	
	public void drawSprite(Rectangle2D.Double dest, Animation a, Graphics2D g){
		Point coord = toScreenCoord(new Point2D.Double(dest.x, dest.y));
		
		/*Spritesheet ss = SpritesheetEnum.getSprite(a.ss);
		
		g.drawImage(ss.img,
			coord.x, coord.y, coord.x + scaleToScreen(dest.width) - 1, coord.y + scaleToScreen(dest.height) - 1,
			ss.offsetW + ss.spriteW * a.frame, ss.offsetH + ss.spriteH * a.set, ss.offsetW + ss.spriteW * (a.frame + 1) - 1, ss.offsetH + ss.spriteH * (a.set + 1) - 1,
			null);*/
		server.addToQueue(a.ss);
		server.addToQueue(coord.x);
		server.addToQueue(coord.y);
		server.addToQueue(coord.x + scaleToScreen(dest.width) - 1);
		server.addToQueue(coord.y + scaleToScreen(dest.height) - 1);
		server.addToQueue(/*a.ss.offsetW + a.ss.spriteW * */a.frame);
		server.addToQueue(/*a.ss.offsetH + a.ss.spriteH * */a.set);
		server.addToQueue(0);
		server.addToQueue(0);
		server.addToQueue(0);
	}
	
	
/*	public void drawProp(Prop p, Graphics2D g){
		Point coord = toScreenCoord(new Point2D.Double(p.getCoordinates().x, p.getCoordinates().y));
		g.drawImage(p.getImage(), coord.x, coord.y, null);
	}*/
	
	public int scaleToScreen(double val){return (int)(val*ppu);}
	public double scaleToGame(int val){return val/ppu;}
}
