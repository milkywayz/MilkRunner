package net.milkycraft.sprite;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Projectile implements Sprite {

	private Image image;
	private Point point;
	private Point destination;
	private float speed;
	
	public Projectile(Image image, Point point, Point destination, float speed) {
		this.image = image;
		this.point = point;
		this.destination = destination;
		this.speed = speed;
	}
	
	@Override
	public Image getImage() {
		return image;
	}

	@Override
	public void setImage(Image i) {
		this.image = i;
	}

	@Override
	public Point getPoint() {
		return point;
	}

	@Override
	public void setLocation(int x, int z) {
		this.point.setLocation(x, z);
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, point.x, point.y);
	}
	
	public Point getDestination(){
		return destination;
	}
	
	public void setDestination(int x, int z) {
		this.destination.setLocation(x, z);
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
