/**
 * 
 */
package model;

/**
 * @author Abhinav Thukral
 *
 */
public class PatatoMine extends Plant {

	private static final int COST = 7;
	/**
	 * @param maxHealth
	 * @param level
	 * @param sprite
	 */
	public PatatoMine(int maxHealth, int level) {
		super(maxHealth, level, "P", COST);
	}

	/* (non-Javadoc)
	 * @see model.Actor#act()
	 */
	@Override
	int act() {
		
		return 0;
	}

}
