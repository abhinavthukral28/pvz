package model;



public abstract class Zombie extends Actor {
	protected String crackedSprite;

	public Zombie(int maxHealth, int level, String string, String sprite, String crackedSprite) {
		super(maxHealth, level, string, false, sprite);
		this.crackedSprite = crackedSprite;
	}
	
	
	/**
	 * Attacks the Actor object passed causing damage
	 * @param actor
	 */
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
	
	 public String getSprite(){
		 if(super.isCracked()){
			 return crackedSprite;
		 }
		 else{
			 return super.sprite;
		 }
		 
	 }
	
}