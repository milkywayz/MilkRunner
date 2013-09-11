package net.milkycraft;

import java.awt.Point;

import net.milkycraft.level.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player implements Locable {

	private Image player;
	private String name;
	private String message;
	private double health;
	private Point point;
	private int points;
	private int ammo;

	public Player(String name) throws SlickException {
		this(name, 0, 0, 100);
	}
	
	public Player(Map map, String name, double health) throws SlickException {
		this(name, map.getSpawnPoint().x, map.getSpawnPoint().y, health);
	}

	protected Player(String name, int x, int z, double h) throws SlickException {
		this.player = AssetHandler.getImage(Asset.PLAYER);
		this.points = 0;
		this.ammo = 0;
		this.point = new Point(x,z);
		this.health = h;
		this.name = name;
		this.message = "";
	}

	public Image getImage() {
		return player;
	}

	public void setImage(Image i) {
		this.player = i;
	}

	public double getHealth() {
		return Math.round(health);
	}

	public void addHealth(double d) {
		this.health += d;
	}

	public boolean isAlive() {
		return health > 0 ? true : false;
	}

	public void damage(double amount) {
		if (health - amount <= 0) {
			health = 0;
		} else {
			this.health -= amount;
		}
	}

	public void addPoints(int i) {
		this.points += i;
	}
	
	public void setPoints(int i) {
		this.points = i;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String x) {
		this.message = x;
	}

	public void draw(Graphics g) {
		int x = (int) point.getX();
		int z = (int) point.getY();
		g.drawImage(player, x, z);
		g.drawString("x: " + x + " z: " + z, 10, 5);
		g.drawString("Health: " + this.getHealth(), 10, 20);
		g.drawString("Points: " + points, 10, 35);
		g.drawString("Ammo: " + ammo, 10, 50);
		g.drawString(message, x + 25, z + 1);
	}

	public void move(int x, int z) {
		this.setPoint(x, z);
		//Player collision with entities?
	}

	public int getPoints() {
		return points;
	}

	public String getName() {
		return name;
	}
	
	public int getX(){
		return point.x;
	}
	
	public int getZ(){
		return point.y;
	}

	@Override
	public Point getPoint() {
		return point;
	}

	@Override
	public void setPoint(int x, int z) {
		this.point = new Point(x,z);
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
	
	public void addAmmo(int ammo) {
		this.ammo += ammo;
	}
}
