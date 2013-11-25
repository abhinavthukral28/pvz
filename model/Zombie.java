package model;

public abstract class Zombie extends Actor {

	public Zombie(int maxHealth, int level, String sprite) {
		super(maxHealth, level, sprite, false);
	}
	protected abstract void attack(Actor actor);
	
	/**
	 * move() method moves the zombie to the left in the grid
	 */
	 protected int move() {
		//the next tile is the tile to the right, so zombies walk backwards, to the left
		Tile nextTile = tile.getLeft();				
		if(nextTile != null){
			if(nextTile.isOccupied()){
				return 0;
			}
			else {
				super.tile.setOccupant(null);
				this.setTile(nextTile);
				super.tile.setOccupant(this);
				return 1;
			}
		}
		return -1;
	}
	
	
}