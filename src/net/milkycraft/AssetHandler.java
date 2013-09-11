package net.milkycraft;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AssetHandler {

	public static String getAsset(Asset e) {
		return "assets/" + e.getPath();
	}

	public static Image getImage(Asset e) throws SlickException {
		return new Image(getAsset(e));
	}

}
