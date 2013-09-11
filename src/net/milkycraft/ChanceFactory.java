package net.milkycraft;

import java.util.Random;

public class ChanceFactory {

	private final static Random r = new Random();
	
	public static Random getRandom(){
		return r;
	}
	
	public static boolean shouldDeflect(){
		return r.nextInt(100) < 75 ? true : false;
	}
}
