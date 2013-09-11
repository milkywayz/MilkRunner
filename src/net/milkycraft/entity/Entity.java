package net.milkycraft.entity;

import net.milkycraft.Player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public interface Entity {

	public void die();
	public double getHealth();
	public void setHealth(double d);
	public void move(float x, float z);
	public float[] getLocation();
	public void target(Player p);
	public boolean isTargeting();
	public int getEntityId();
	public Image getImage();
	public void setImage(Image i);
	public void draw(Graphics g);
}
