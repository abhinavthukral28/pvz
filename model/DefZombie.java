package model;

/**
 * Class DefZombie is the basic zombie for the game
 * @author Abhinav Thukral
 * @version 1.5
 *
 */
public class DefZombie extends Zombie {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 50; 
	// Default Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DF = 10; 
	// Default Sprite for the Zombie
	private static final String DEFSPRITE = "images/damagedExplosiveZombie.png";
	// Cracked Sprite for the Zombie
	private static final String CRACKEDSPRITE = "images/damagedZombie.png";


	/**
	 * Constructor for DefZombie 
	 * @param tile
	 * @param level
	 */
	public DefZombie(int level) {
		super((HF * level), level, "Z", DEFSPRITE, CRACKEDSPRITE);
	}

	/** 
	 * act() method for DefZombie makes the zombie move or attack
	 * @returns 2 if the zombie attacks or returns 1 if zombie moves.
	 *  
	 */
	public int act(LevelData grid){
		int move = super.move(grid);
		if(move == 0){						//only true if there is a plant at x-1, y
			//if (grid.inBounds(x-1, y)){		//redundant
				Actor target;
				//if(grid.plantAt(x-1, y)){	//redundant
					target = grid.getActorAt(x-1, y);
					attack(target);
					return 2;
				//}
			//}
//			Actor actor = grid.getActorAt(this.x - 1, y);
//			if (actor instanceof Zombie) {
//				return 0;
//			}
//			attack(actor);
//			return 2;	
//			}
//			else{
//				return -1;
//			}
		}
		else{
			return move;
		}
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
	protected int move(LevelData grid){
		return super.move(grid);
	}


}
