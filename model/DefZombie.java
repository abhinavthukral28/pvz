package model;

/**
 * Class DefZombie is the basic zombie for the game
 * @author Abhinav Thukral
 * @version 1.0;
 *
 */
public class DefZombie extends Actor {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 50; 
	// Default Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DF = 10; 
		

	/**
	 * Constructor for DefZombie 
	 * @param tile
	 * @param level
	 */
	public DefZombie(int level) {
		super((HF * level), level, "Z", false);
	}
	
	/** 
	 * act() method for DefZombie makes the zombie move or attack
	 * @returns 2 if the zombie attacks or returns 1 if zombie moves.
	 *  
	 */
	public int act(){
		if (tile.getLeft() != null) {
			if (tile.getLeft().isOccupied()) {
				Actor actor = tile.getLeft().getOccupant();
				if (actor instanceof DefZombie) {
					return 0;
				}
				attack(actor);
				return 2;
			}
			else{
				move();
				return 1;
			}
		}
		return 0;
		
	}
	/**
	 * move() method moves the zombie to the left in the grid
	 */
	private void move() {
		//the next tile is the tile to the right, so zombies walk backwards, to the left
		Tile nextTile = tile.getLeft();				
		if(nextTile != null){
			if(nextTile.isOccupied()){
				return;
			}
			else {
				super.tile.setOccupant(null);
				this.setTile(nextTile);
				super.tile.setOccupant(this);
				return;
			}
			
		}
		
	}
	
	/**
	 * Attacks the Actor object passed causing damage
	 * @param actor
	 */
	private void attack(Actor actor) {
		actor.takeDamage(DF * super.level);
	}
	/**
	 * @return 0 as in this version the player does not get to play zombie, Maybe implement it in future versions
	 */
	public int getCost(){
		return 0;
	}
	

}
