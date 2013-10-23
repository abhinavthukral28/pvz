package pvz;

/**
 * @author Abhinav Thukral
 * @version 1.0;
 *
 */
public class DefZombie extends Actor {
	// Health Factor multiplies with level to increase max health (Experimental)
	private static final int HFDZ = 50; 
	// Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DFDZ = 10; 
		

	/**
	 * Constructor for DefZombie 
	 * @param tile
	 * @param level
	 * @param sprite
	 */
	public DefZombie(Tile tile,int level, String sprite) {
		super(tile, (HFDZ * level), level, "Z");
	}
	
	/* 
	 * act() method for DefZombie makes the zombie move or attack
	 * @returns 2 if the zombie attacks or returns 1 if zombie moves.
	 *  
	 */
	public int act(){
		if (tile.getNext() != null) {
			if (tile.getNext().isOccupied()) {
				Actor actor = tile.getNext().getOccupant();
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
	 * move() method moves the zombie to the next tile 
	 */
	private void move() {
		Tile nextTile = tile.getNext();
		if(nextTile != null){
			if(nextTile.isOccupied()){
				return;
			}
			else {
				super.tile = nextTile;
				return;
			}
			
		}
		
	}
	
	/**
	 * Attacks the Actor object passed causing damage
	 * @param actor
	 */
	private void attack(Actor actor) {
		actor.takeDamage(DFDZ * super.level);
	}
	

}
