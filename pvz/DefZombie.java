package pvz;

/**
 * @author Abhinav Thukral
 * @version 1.0;
 *
 */
public class DefZombie extends Actor {
	private static final int HFDZ = 50; // Health Factor multiplies with level to increase max health
	private static final int DFDZ = 10; // Damage Factor multiplies with level to increase damage
	private static final int SFDZ = 1;	// Speed Factor for speed
		

	public DefZombie(int x, int y,int level, String sprite) {
		super(x, y, (HFDZ * level), level, "T");
	}
	/**
	 * still to be properly implemented based on the grid
	 */
	public void move() {
		super.x = super.x + 1 * SFDZ;
		super.y = super.y + 1 * SFDZ;
	}
	
	public void attack(Actor actor) {
		actor.takeDamage(DFDZ * super.level);
	}
	

}
