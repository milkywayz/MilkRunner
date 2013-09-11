package net.milkycraft;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import net.milkycraft.sprite.Bullet;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Barrier {

	protected Rectangle r;
	protected String str;
	
	public Barrier(int x, int z, int xx, int zz, String str) {
		this(x, z, new Dimension(max(x, xx) - min(x, xx), max(z, zz) - min(z, zz)), str);
	}

	public Barrier(int x, int y, Dimension d, String str2) {
		r = new Rectangle(x, y, d.width, d.height);
		this.str = str2;
	}
	
	public boolean makesCollision(Point p){
		return r.contains(p);
	}
	
	public boolean bulletCollision(Bullet b, int tx, int tz) {
		if(r.contains(tx, tz)){
			return true;
		}
		Point p = b.getPoint();
		if(r.contains(p.getX(), p.getY())){
			return true;
		}	
		return false;
	}
	
	public boolean playerCollision(Player p, int tx, int tz) {
		Image i = p.getImage();
		Rectangle rect = new Rectangle(p.getX(), p.getZ(), i.getWidth(), i.getHeight());
		if(r.contains(tx, tz)){
			return true;
		}
		if(r.contains(p.getX(), p.getZ())){
			return true;
		}
		if(r.contains(rect.getMaxX(), rect.getMaxY())){
			return true;
		}
		if(r.contains(rect.getMinX(), rect.getMinY())){
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		g.fillRect(r.x, r.y, r.width, r.height);
		g.setColor(Color.black);
		g.drawString(str, (float) r.getCenterX(), (float)r.getCenterY());
		g.setColor(Color.white);
	}
}
