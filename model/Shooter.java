package model;

/**
 * The Shooter class creates a pea shooting plant
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class Shooter extends Plant {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 40;
	// Default Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DF = 10; 
	// The solar cost of the plant
	private static final int COST = 2;
	// Default Sprite for the Plant
	private static final String DEFSPRITE = "images/peashooter.jpg";
	
	/**
	 * @param level
	 */
	public Shooter(int level) {
		super((HF * level), level, "S", COST, DEFSPRITE);
	}
	
	/**
	 * @return 2 if zombie successfully attacked else returns 0
	 */
	public int act(LevelData grid) {
		return attack(grid);
	}
	
	/**
	 * attack() iterates through the grid to check if there is any zombie in the vicinity
	 * @return 2 if zombie successfully attacked else returns 0
	 */
	private int attack(LevelData grid) {
		// iterates through the grid to till it reaches the end of the grid
		int x = 0;
		while(grid.actorAt(x, this.y)){
			if(grid.zombieAt(x, this.y)) {
				grid.getActorAt(x, this.y).takeDamage(DF * super.level);
				return 2;
			}
			x++;
		}
		return 0;
	}
}
