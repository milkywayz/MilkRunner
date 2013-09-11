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

public class BreakableBarrier extends Barrier {

	private int health;

	public BreakableBarrier(int x, int y, Dimension d, int health) {
		super(x, y, d, "X");
		this.health = health;
	}

	public BreakableBarrier(int x, int z, int xx, int zz, int health) {
		this(x, z, new Dimension(max(x, xx) - min(x, xx), max(z, zz) - min(z, zz)), health);
	}

	public boolean isVisible() {
		return health > 0 ? true : false;
	}

	@Override
	public boolean makesCollision(Point p) {
		return this.isVisible() ? r.contains(p) : false;
	}

	@Override
	public boolean bulletCollision(Bullet b, int tx, int tz) {
		if(!this.isVisible()){
			return false;
		}		
		if (r.contains(tx, tz)) {
			b.setGone(true);
			this.health -= 1;
			return true;
		}
		Point p = b.getPoint();
		if (r.contains(p.getX(), p.getY())) {
			b.setGone(true);
			this.health -= 1;
			return true;
		}
		return false;
	}

	@Override
	public boolean playerCollision(Player p, int tx, int tz) {
		if(!this.isVisible()){
			return false;
		}
		Image i = p.getImage();
		Rectangle rect = new Rectangle(p.getX(), p.getZ(), i.getWidth(), i.getHeight());
		if (r.contains(tx, tz)) {
			return true;
		}
		if (r.contains(p.getX(), p.getZ())) {
			return true;
		}
		if (r.contains(rect.getMaxX(), rect.getMaxY())) {
			return true;
		}
		if (r.contains(rect.getMinX(), rect.getMinY())) {
			return true;
		}
		return false;
	}

	public Color getColor(){
		if(health >= 5) {
			return Color.green;
		} else if(health >= 4) {
			return Color.yellow;
		} else if(health >= 3) {
			return Color.orange;
		} else if(health >= 2) {
			return Color.pink;
		} else if(health >= 1) {
			return Color.red;
		} else if(health >= 0) {
			return Color.black;
		}
		return Color.white;
	}
	@Override
	public void draw(Graphics g) {
		if (this.isVisible()) {
			g.setColor(this.getColor());
			g.fillRect(r.x, r.y, r.width, r.height);
			g.setColor(Color.white);
		}
	}
}
