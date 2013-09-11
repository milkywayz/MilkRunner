package net.milkycraft;

public enum Asset {
	ZOMBIE("zombie.png"), 
	MILK("milk.png"), 
	PLAYER("player.png"),
	AMMO("ammo.png"),
	BULLET("bullet.png"),
	PORTAL("portal.png"),
	MAP_1("map_1.png");
	
	private final String path;
	Asset(String path){
		this.path = path;
	}
	
	public String getPath(){
		return path;
	}
}
