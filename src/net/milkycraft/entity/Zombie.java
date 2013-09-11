package net.milkycraft.entity;

import net.milkycraft.Asset;
import net.milkycraft.AssetHandler;
import net.milkycraft.Player;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Zombie implements Entity {

	private Image image;
	private String message;
	private double health;
	private boolean targetting;
	public float x;
	public float z;
	private int id;

	public Zombie(int id, int x, int z) throws SlickException {
		this(id, x, z, 20);
	}

	public Zombie(int id, float x, float z, double health) throws SlickException {
		this.image = AssetHandler.getImage(Asset.ZOMBIE);
		this.message = "";
		this.health = health;
		this.targetting = false;
		this.id = id;
		this.x = x;
		this.z = z;
	}

	@Override
	public void die() {
		this.health = 0;
	}

	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void setHealth(double d) {
		this.health = d;
	}

	@Override
	public void move(float x, float z) {
		if (!this.targetting) {
			this.x = x;
			this.z = z;
		}
	}


	@Override
	public float[] getLocation() {
		return new float[] { x, z };
	}

	@Override
	public void target(Player p) {
		this.targetting = true;

	}

	@Override
	public int getEntityId() {
		return id;
	}

	@Override
	public Image getImage() {
		return image;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setImage(Image i) {
		this.image = i;
	}

	@Override
	public boolean isTargeting() {
		return this.targetting;
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, z);
		g.drawString(message, x + 25, z + 1);
	}
}
