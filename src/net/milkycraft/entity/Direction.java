package net.milkycraft.entity;

public enum Direction {

	NORTH(0,-1), SOUTH(0, 1), EAST(1,0), WEST(-1,0);
	
	int x,z;
	 Direction(int x, int z) {
		this.x = x;
		this.z = z;
	}
	 
	 public int[] getMove() {
		 return new int[]{x,z};
	 }
}
