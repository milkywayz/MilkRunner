package net.milkycraft.sprite;

import java.awt.Point;
import java.awt.Rectangle;

import net.milkycraft.Asset;
import net.milkycraft.AssetHandler;
import net.milkycraft.Player;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Portal extends Enterable {

	private Rectangle rect;

	private Portal(Image image, Point point) {
		super(image, point);
		rect = new Rectangle(point.x, point.y, image.getWidth(), image.getHeight());
	}
	
	public Portal(Point point) throws SlickException{
		this(AssetHandler.getImage(Asset.PORTAL), point);
	}

	public Rectangle getBounds() {
		return rect;
	}
	
	public boolean playerCollision(Player p, int tx, int tz) {
		Image i = p.getImage();
		Rectangle r = new Rectangle(p.getX(), p.getZ(), i.getWidth(), i.getHeight());
		if(rect.contains(tx, tz)){
			return true;
		}
		if(rect.contains(p.getX(), p.getZ())){
			return true;
		}
		if(rect.contains(r.getMaxX(), r.getMaxY())){
			return true;
		}
		if(rect.contains(r.getMinX(), r.getMinY())){
			return true;
		}
		return false;
	}
}
