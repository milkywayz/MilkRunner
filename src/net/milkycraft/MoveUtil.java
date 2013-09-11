package net.milkycraft;

import java.awt.Point;
import java.awt.Rectangle;

import net.milkycraft.entity.Direction;
import net.milkycraft.level.Map;
import net.milkycraft.sprite.Ammo;
import net.milkycraft.sprite.Bullet;
import net.milkycraft.sprite.Milk;
import net.milkycraft.sprite.Pickupable;
import net.milkycraft.sprite.Portal;
import net.milkycraft.sprite.Sprite;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MoveUtil {

	public static void doMove(Player p, Direction d) {
		int x = p.getPoint().x;
		int z = p.getPoint().y;
		int[] dd = d.getMove();
		int tx = dd[0] + x;
		int tz = dd[1] + z;
		if (canMove(p, tx, tz)) {
			p.move(tx, tz);
		}
	}

	public static double getDistance(Player p, int x, int z) {
		return Math.sqrt(Math.pow((x - p.getX()), 2) - Math.pow((z - p.getZ()), 2));
	}

	public static void pickUp(Map s) {
		Player p = s.getPlayer();
		int r = 25;
		for (int a = p.getX() - r; a < p.getX() + r; a++) {
			for (int b = p.getZ() - r; b < p.getZ() + r; b++) {
				for (Sprite spr : s.getSprites()) {
					Point point = spr.getPoint();
					if (point.x == a && point.y == b) {
						if(spr instanceof Pickupable) {
							if(((Pickupable) spr).isPickedUp()) {
								continue;
							}
							if (spr instanceof Milk) {
								Milk m = (Milk)spr;
								p.addPoints(2);
								p.addHealth(2.2);
								m.setPickedUp(true);
								p.setMessage("Milk! Health and points! :D");
							} else if(spr instanceof Ammo) {
								Ammo ammo = (Ammo)spr;
								ammo.setPickedUp(true);
								p.addAmmo(ammo.getAmount());
								p.setMessage("Picked up " + ammo.getAmount() + " bullets!");
							}
						}						
					}
				}
			}
		}
	}

	public static void checkCollide(Bullet bull, Point m) {
		Player p = MilkRunner.current.getPlayer();
		Image i = p.getImage();
		Rectangle rect = new Rectangle(p.getX(), p.getZ(), i.getWidth(), i.getHeight());
		if (rect.contains(m)) {
			p.setMessage("Hit by a bullet!");
			bull.setGone(true);
			p.damage(5);
		}
	}

	public static boolean canMove(Bullet bull, Point m) {
		for (Barrier b : MilkRunner.current.getBarriers()) {
			if (b.bulletCollision(bull, m.x, m.y)) {
				return false;
			}
		}
		return true;
	}

	public static void rotatePlayer(Player p, float x, float z) {
		float xD = x - p.getX();
		float yD = z - p.getZ();
		double angleToTurn = Math.toDegrees(Math.atan2(yD, xD));
		if (angleToTurn < 0) {
			angleToTurn += 360;
		}
		p.getImage().setRotation((float) angleToTurn);
	}

	private static boolean canMove(Player p, int x, int z) {
		for (Barrier b : MilkRunner.current.getBarriers()) {
			if (b.playerCollision(p, x, z)) {
				p.setMessage("ouch!");
				p.damage(1.2D);
				return false;
			}
		}
		for(Sprite s : MilkRunner.current.getSprites()){
			if(s instanceof Portal) {
				if(((Portal) s).playerCollision(p, x, z)) {
					try {
						MilkRunner.nextLevel(p);
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (x > 624 || x < 0) {
			return false;
		}
		if (z > 464 || z < 0) {
			return false;
		}
		return true;
	}
}
