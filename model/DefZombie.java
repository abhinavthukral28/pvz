package model;

/**
 * Class DefZombie is the basic zombie for the game
 * @author Abhinav Thukral
 * @version 1.0;
 *
 */
public class DefZombie extends Zombie {
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
		super((HF * level), level, "Z");
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
				if (actor instanceof Zombie) {
					return 0;
				}
				attack(actor);
				return 2;
			}
			else{
				super.move();
				return 1;
			}
		}
		return 0;
		
	}
	
	/**
	 * Attacks the Actor object passed causing damage
	 * @param actor
	 */
	protected void attack(Actor actor) {
		actor.takeDamage(DF * super.level);
	}
	/**
	 * @return 0 as in this version the player does not get to play zombie, Maybe implement it in future versions
	 */
	protected int move(){
		return super.move();
	}
	

}
