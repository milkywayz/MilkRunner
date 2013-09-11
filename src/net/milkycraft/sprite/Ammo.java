package net.milkycraft.sprite;

import java.awt.Point;

import net.milkycraft.Asset;
import net.milkycraft.AssetHandler;

import org.newdawn.slick.SlickException;

public class Ammo extends Pickupable implements Amountable {

	private int amount;

	public Ammo(Point p, int a) throws SlickException {
		super(AssetHandler.getImage(Asset.AMMO), p);
		this.setAmount(a);
	}

	public Ammo(int x, int z, int a) throws SlickException {
		this(new Point(x, z), a);
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
