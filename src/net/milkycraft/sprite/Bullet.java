package net.milkycraft.sprite;

import java.awt.Point;

import net.milkycraft.Asset;
import net.milkycraft.AssetHandler;
import net.milkycraft.ChanceFactory;
import net.milkycraft.MoveUtil;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet extends Projectile implements Reflectable, Ageable, Movable {

	private long age;
	private boolean isShot;
	private boolean isGone;
	private boolean isReflected;
	private float dx;
	private float dy;
	
	private Bullet(Image image, Point point, Point destination, float speed) {
		super(image, point, destination, speed);	
		this.isShot = true;
		this.isReflected = false;
		this.isGone = false;
		this.age = 0;
		recalculateVector(destination.x, destination.y);
	}
	
	public Bullet(int x, int z, int xx, int zz) throws SlickException {
		this(AssetHandler.getImage(Asset.BULLET), new Point(x,z), new Point(xx,zz), 5F);
	}
	
	public Bullet(Point p, Point d, float s) throws SlickException {
		this(AssetHandler.getImage(Asset.BULLET), p, d, s);
	}
	
	@Override
	public void move() {
		if (isGone) {
			return;
		}
		int x = super.getPoint().x;
		int z = super.getPoint().y;
		int toX = super.getPoint().x += dx;
		int toZ = super.getPoint().y += dy;
		java.awt.Point p = new java.awt.Point(toX, toZ);
		if (!MoveUtil.canMove(this, p)) {
			if(ChanceFactory.shouldDeflect()) {
				this.reflect();
				return;
			}
			this.setGone(true);		
		} else {
			if (this.isReflected()) {
				MoveUtil.checkCollide(this, p);
			}
			x += dx;
			z += dy;
			super.setLocation(x, z);
		}
		if (x > 640 || x < 0) {
			this.setGone(true);
		}
		if (z > 480 || z < 0) {
			this.setGone(true);
		}
	}

	public boolean isShot() {
		return isShot;
	}

	public void setShot(boolean isShot) {
		this.isShot = isShot;
	}

	public boolean isGone() {
		return isGone;
	}

	public void setGone(boolean isGone) {
		this.isGone = isGone;
	}

	@Override
	public boolean isReflected() {
		return isReflected;
	}

	public void setReflected(boolean isReflected) {
		this.isReflected = isReflected;
	}

	@Override
	public void reflect() {		
		this.setReflected(true);
		Point des = super.getDestination();
		super.setDestination(-des.x, -des.y);
		recalculateVector();
	}

	public void recalculateVector(int destX, int destY) {
		Point p = super.getPoint();
		float rad = (float) (Math.atan2(destX - p.x, p.y - destY));
		this.dx = (float) Math.sin(rad) * super.getSpeed();
		this.dy = -(float) Math.cos(rad) * super.getSpeed();
	}

	public void recalculateVector() {
		Point des = super.getDestination();
		recalculateVector(des.x, des.y);
	}

	@Override
	public long getAge() {
		return age;
	}

	@Override
	public void setAge(long i) {
		this.age = i;
	}

	@Override
	public void age() {
		age++;
	}
}
