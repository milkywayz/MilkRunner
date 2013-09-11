package net.milkycraft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkycraft.entity.Direction;
import net.milkycraft.entity.Entity;
import net.milkycraft.level.Map;
import net.milkycraft.sprite.Bullet;
import net.milkycraft.sprite.Pickupable;
import net.milkycraft.sprite.Sprite;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.command.MouseButtonControl;

public class MilkRunner extends BasicGame implements InputProviderListener {

	private static List<Map> maps = new ArrayList<Map>();
	public static Map current;
	private static int map = 0;
	private Command pickup = new BasicCommand("pickup");
	private Command attack = new BasicCommand("attack");
	private InputProvider provider;
	private Input input;

	public MilkRunner(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		input = gc.getInput();
		gc.setTargetFrameRate(120);
		gc.setShowFPS(false);
		provider = new InputProvider(gc.getInput());
		provider.addListener(this);
		provider.bindCommand(new KeyControl(Input.KEY_SPACE), pickup);
		provider.bindCommand(new MouseButtonControl(Input.MOUSE_LEFT_BUTTON), attack);
		File mapDir = new File("levels");
		if(!mapDir.exists()){
			mapDir.mkdir();
		}
		for(File map : mapDir.listFiles()){
			if(map.isDirectory()){
				Map m = MapLoader.loadMap(map);
				if(m != null){
					maps.add(m);
					if(current == null){
						current = m;
					} 
				}
			}
		}
	}
	
	public static void nextLevel(Player p) throws SlickException {
		map++;
		Map newMap = maps.get(map);
		double h = p.getHealth();
		int am = p.getAmmo();
		int pnts = p.getPoints();
		Player pl = new Player(newMap, "milkywayz", h);
		pl.setAmmo(am);
		pl.setPoints(pnts);
		current.setPlayer(pl);		
		current = newMap;
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		input = gc.getInput();
		if (!current.getPlayer().isAlive()) {
			Player p = new Player(current, "milkywayz", 100);
			current.setPlayer(p);
		}
		Player p = current.getPlayer();
		if (input.isKeyDown(Input.KEY_W)) {
			MoveUtil.doMove(p, Direction.NORTH);
		}
		if (input.isKeyDown(Input.KEY_A)) {
			MoveUtil.doMove(p, Direction.WEST);
		}
		if (input.isKeyDown(Input.KEY_S)) {
			MoveUtil.doMove(p, Direction.SOUTH);
		}
		if (input.isKeyDown(Input.KEY_D)) {
			MoveUtil.doMove(p, Direction.EAST);
		}
		MoveUtil.rotatePlayer(p, input.getAbsoluteMouseX(), input.getAbsoluteMouseY());
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(current == null) return;
		Player p = current.getPlayer();
		g.drawImage(current.getScene(), 0, 0);
		p.draw(g);
		for (Barrier b : current.getBarriers()) {
			b.draw(g);
		}
		for (Sprite s : current.getSprites()) {
			if (s instanceof Pickupable) {
				if (((Pickupable) s).isPickedUp()) {
					continue;
				}
			}
			if (s instanceof Bullet) {
				Bullet b = (Bullet) s;
				long a = b.getAge();
				b.setAge(a + 1);
				if (b.isGone() || b.getAge() > 1000) {
					current.getSprites().remove(s);
					continue;
				}
				if (b.isShot()) {
					b.move();
				}
			}
			s.draw(g);
		}
		for (Entity e : current.getEntities()) {
			if (e.getHealth() > 0) {
				e.draw(g);
			} else {
				current.getEntities().remove(e);
			}
		}
	}

	public static void main(String[] args) {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MilkRunner("MilkRunner"));
			appgc.setDisplayMode(640, 480, false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(MilkRunner.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void controlPressed(Command c) {	
		if(current == null) return;
		Player p = current.getPlayer();
		if (c == pickup) {
			MoveUtil.pickUp(current);
		} else if (c == attack) {
			int a = p.getAmmo();
			if (a == 0) {
				p.setMessage("No ammo! Collect ammo");
				return;
			}
			int x = input.getAbsoluteMouseX();
			int z = input.getAbsoluteMouseY();
			try {
				Bullet b = new Bullet(p.getX(), p.getZ(), x, z);
				p.setAmmo(a - 1);
				current.getSprites().add(b);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void controlReleased(Command c) {
		//
	}
}