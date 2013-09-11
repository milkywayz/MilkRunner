package net.milkycraft.level;

import java.awt.Point;

import net.milkycraft.Player;

import org.newdawn.slick.Image;

public interface Level {

	public Image getScene();
	public Point getSpawnPoint();
	public Player getPlayer();
	public void setPlayer(Player p);
	public String getName();
}
