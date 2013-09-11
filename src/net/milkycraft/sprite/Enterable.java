package net.milkycraft.sprite;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Enterable implements Sprite {

	private Image image;
	private Point point;
	
	public Enterable(Image image, Point point) {
		this.image = image;
		this.point = point;
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

}
