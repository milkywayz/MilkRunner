package net.milkycraft.level;

import java.awt.Point;

import net.milkycraft.Player;

import org.newdawn.slick.Image;

public class BasicLevel implements Level {

	private Image scene;
	private Player player;
	private Point spawn;
	private String name;
	
	public BasicLevel(Image image, Player p, Point point, String name) {
		this.player = p;
		this.scene = image;
		this.spawn = point;
		this.name = name;
	}
	@Override
	public Image getScene() {
		return scene;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player p) {
		this.player = p;
	}
	@Override
	public Point getSpawnPoint() {
		return spawn;
	}
	@Override
	public String getName() {
		return name;
	}
}
