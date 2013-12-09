/**
 * 
 */
package model;

/**
 * @author Abhinav Thukral
 * ExplosiveZombie creates a zombie which has double the attack power of DefZombie and when the Zombie has less than 20, it also kills the next plant in line
 *
 */
public class ExplosiveZombie extends Zombie {
	// Default Health Factor multiplies with level to increase max health
	private static final int HF = 50; 
	// Default Damage Factor multiplies with level to increase damage 
	private static final int DF = 20;
	// Default Sprite for the Zombie
	private static final String DEFSPRITE = "images/HealthyExplosiveZombie.jpg";
	// Cracked Sprite for the Zombie
	private static final String CRACKEDSPRITE = "images/damagedExplosiveZombie.png";
	// boolean frozen to see if the zombie has been frozen
	private boolean isFrozen;
	/**
	 * @param level
	 */
	public ExplosiveZombie(int level) {
		super((HF * level), level, "ZE", DEFSPRITE, CRACKEDSPRITE);
		isFrozen = false;
	}

	/* (non-Javadoc)
	 * @see model.Zombie#attack(model.Actor)
	 */
	@Override
	protected void attack(Actor actor) {
		actor.takeDamage(DF * 2);
	}

	/** 
	 * Act method for this class
	 * @returns 0 for no movement, 1 for movement and 2 for successful attack
	 */
	@Override
	public int act(LevelData grid) {
		if (super.currHealth <= 0.2 * super.maxHealth && !this.isFrozen){
			explode(grid);
			this.takeDamage(1000);
			return 2;
		}
		int move = super.move(grid);
		if(move == 0){
			if(grid.inBounds(x-1, y)){
				if (grid.plantAt(this.x - 1, this.y)) {
					attack(grid.getActorAt(this.x -1, y));
					return 2;
				}
			}
			else{
				return -1;
			}
		}
		else{
			return move;
		}
		return move;
	}


	/**
	 * causes Actor to explode
	 * @param tempTile
	 * @return 
	 */
	 
		private int explode(LevelData grid){
			int x = this.x;
			while(grid.inBounds(x, this.y)){
				if(grid.plantAt(x, this.y)) {
					grid.getActorAt(x, this.y).takeDamage(1000);
					return 2;
				}
				x--;
			}
			return 0;
		}
			

	/**
	 * @param isFrozen the isFrozen to set
	 */
	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
}
