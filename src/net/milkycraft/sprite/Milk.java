package net.milkycraft.sprite;

import java.awt.Point;

import net.milkycraft.Asset;
import net.milkycraft.AssetHandler;

import org.newdawn.slick.SlickException;

public class Milk extends Pickupable implements Amountable {

	private int amount;
	
	public Milk(int x, int z) throws SlickException {
		super(AssetHandler.getImage(Asset.MILK), new Point(x,z));
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
