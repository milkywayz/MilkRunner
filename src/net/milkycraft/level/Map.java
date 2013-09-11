package net.milkycraft.level;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import net.milkycraft.Barrier;
import net.milkycraft.Player;
import net.milkycraft.entity.Entity;
import net.milkycraft.sprite.Sprite;

public class Map extends BasicLevel {
	
	private List<Barrier> barriers = new CopyOnWriteArrayList<Barrier>();
	private List<Entity> entities = new CopyOnWriteArrayList<Entity>();
	private List<Sprite> sprites = new CopyOnWriteArrayList<Sprite>();
	
	public Map(Image image, Player p, Point point, String name) {
		super(image, p, point, name);		
	}

	public Map(String path, Player p, Point point, String name) throws SlickException {
		super(new Image(path), p, point, name);	
	}

	public List<Barrier> getBarriers() {
		return barriers;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public List<Sprite> getSprites() {
		return sprites;
	}
	
	public void removeBarrier(Barrier b) {
		this.barriers.remove(b);
	}
	
	public void removeEntity(Entity e) {
		this.entities.remove(e);
	}
	
	public void removeSprite(Sprite s) {
		this.sprites.remove(s);
	}
	
	public void addBarrier(Barrier b) {
		this.barriers.add(b);
	}
	
	public void addSprite(Sprite s) {
		this.sprites.add(s);
	}
	
	public void addEntity(Entity e) {
		this.entities.add(e);
	}

	@Override
	public String toString() {
		return "Map [getScene()=" + getScene() + ", getPlayer()=" + getPlayer()
				+ ", getSpawnPoint()=" + getSpawnPoint() + ", getName()=" + getName()
				+ ", toString()=" + super.toString() + "]";
	}	
}
