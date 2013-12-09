/**
 * 
 */
package model;

/**
 * SnowShooter freezes the poleZombies and prevents them from jumping and slows down there speed.
 * It also deals double damage to regular zombies.
 * @author Abhinav Thukral
 *
 */
public class SnowShooter extends Plant {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 40;
	// Default Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DF = 10; 
	// The solar cost of the plant
	private static final int COST = 4;
	// Default Sprite for the Plant
	private static final String DEFSPRITE = "images/peashooterFreeze.jpg";

	/**
	 * @param level
	 */
	public SnowShooter(int level) {
		super((HF*level), level, "SS", COST, DEFSPRITE);
	}

	/**
	 * Act method for this class
	 * @returns 0 for no movement, 1 for movement and 2 for successful attack
	 */
	@Override
	public int act(LevelData grid) {
		return attack(grid);
	}

	/**
	 * attack() iterates through the grid to check if there is any zombie in the vicinity
	 * @return 2 if zombie successfully attacked else returns 0
	 */
	private int attack(LevelData grid) {
		// iterates through the grid to till it reaches the end of the grid
		int x = this.x;
		Actor zombie;
		while(grid.inBounds(x, this.y)){
			if(grid.zombieAt(x, this.y)) {
				zombie = grid.getActorAt(x, this.y);
				if(zombie instanceof PoleZombie){
					((PoleZombie) zombie).setFrozen(true);
					zombie.takeDamage(DF * super.level);
					return 2;
				}
				else if(zombie instanceof ExplosiveZombie){
					((ExplosiveZombie) zombie).setFrozen(true);
					zombie.takeDamage(DF * super.level);
					return 2;
				}
				else{
					zombie.takeDamage(2 * DF);
					return 2;
				}
			}
			x++;
		}
		return 0;	
	}
}
