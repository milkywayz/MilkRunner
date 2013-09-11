package net.milkycraft.sprite;

import java.awt.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public interface Sprite {

	public Image getImage();
	public void setImage(Image i);
	public Point getPoint();
	public void setLocation(int x, int z);
	public void draw(Graphics g);
}
