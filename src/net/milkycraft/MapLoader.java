package net.milkycraft;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;

import net.milkycraft.level.Map;
import net.milkycraft.sprite.Ammo;
import net.milkycraft.sprite.Milk;
import net.milkycraft.sprite.Sprite;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.newdawn.slick.Image;

public class MapLoader {

	public static Map loadMap(File sa) {
		Map m = null;
		try {
			for (File f : sa.listFiles()) {
				if (f.getName().endsWith(".map")) {
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(new FileReader(f));
					org.json.simple.JSONObject json = (org.json.simple.JSONObject) obj;
					JSONObject jso = new JSONObject(json.toJSONString());
					JSONArray spawn = jso.getJSONArray("spawn");
					JSONArray sja = jso.getJSONArray("sprites");
					JSONArray bja = jso.getJSONArray("barriers");
					File asset = new File(sa, "assets" + File.separator
							+ jso.getString("asset") + ".png");
					Player p = new Player("player");
					int[] s = new int[] { spawn.getInt(0), spawn.getInt(1) };
					m = new Map(asset.getAbsolutePath(), p, new Point(s[0], s[1]),
							jso.getString("name"));
					p.setPoint(s[0], s[1]);
					for (int i = 0; i < sja.length(); i++) {
						JSONObject lobj = sja.getJSONObject(i);
						Point pp = new Point(lobj.getInt("x"), lobj.getInt("y"));
						JSONArray pl = lobj.getJSONArray("payload");
						Sprite spr = null;
						if (lobj.getString("type").equals("milk")) {
							spr = new Milk(pp.x, pp.y);
							Milk milk = (Milk) spr;
							milk.setAmount(pl.getInt(1));
						} else if (lobj.getString("type").equals("ammo")) {
							spr = new Ammo(pp, pl.getInt(0));
						}
						spr.setImage(new Image(asset.getAbsolutePath()));
						m.addSprite(spr);
					}

					for (int i = 0; i < bja.length(); i++) {
						JSONObject lobj = bja.getJSONObject(i);
						int x = lobj.getInt("x");
						int y = lobj.getInt("y");
						int w = lobj.getInt("w");
						int h = lobj.getInt("h");
						Barrier b = new Barrier(x, y, w, h, "" + i);
						m.addBarrier(b);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Loaded map " + m.toString());
		return m;
	}
}
