/**
 * 
 */
package model;

/**
 * @author Abhinav Thukral
 * ExplosiveZombie creates a zombie which has double the attack power of DefZombie and when the Zombie dies, it also kills the next plant in line
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
	}

	/* (non-Javadoc)
	 * @see model.Zombie#attack(model.Actor)
	 */
	@Override
	protected void attack(Actor actor) {
		actor.takeDamage(DF * super.level);
	}

	/** 
	 * Act method for this class
	 * @returns 0 for no movement, 1 for movement and 2 for successful attack
	 */
	@Override
	public int act(LevelData grid) {
		int move = super.move(grid);
		if(move == 0){
			if(grid.inBounds(x-1, y)){
				Actor actor = grid.getActorAt(this.x -1 , y);
				if (actor instanceof Zombie) {
					return 0;
				}
				attack(actor);
				return 2;	
			}
			else{
				return move;
			}
		}
		else{
			return -1;
		}
	}

	/**
	 * overriding the Actor's take damage to cause an explosion when it dies.
	 */
	public int takeDamage(int damage){
		super.takeDamage(damage);
		if(!isAlive()){
			explode(x, y);
		}
		return super.currHealth;
	}

	/**
	 * causes Actor to explode
	 * @param tempTile
	 */
	/* 
		private void explode(LevelData grid){
			int x = this.x;
			while(grid.At(x, this.y)){
				if(grid.zombieAt(x, this.y)) {
					grid.getActorAt(x, this.y).takeDamage(DF * super.level);
					return 2;
				}
				x++;
				if(!grid.inBounds(x, y)){
					break;
				}
			}
			return 0;
		}
				while(tempTile != null){
					tempTile = tempTile.getLeft();
					if(tempTile != null && tempTile.isOccupied()){
						Actor actor = tempTile.getOccupant();
						if (actor instanceof Plant) {
							actor.takeDamage(1000);
							return;
						}
					}
				}
		}
 */

	/**
	 * @param isFrozen the isFrozen to set
	 */
	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}
}
