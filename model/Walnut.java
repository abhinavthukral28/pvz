/**
 * 
 */
package model;


/**
 * Walnut class creates a walnut which is a defensive plant such that several attacks by a zombie are required to break it
 * @author Abhinav Thukral
 */
public class Walnut extends Plant {

	private static final int HF = 100;
	private static final int COST = 20;
	private boolean cracked;

	public Walnut(int maxHealth, int level, String sprite) {
		super(HF * level, level, "o", COST);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see pvz.Plant#getCost()
	 */
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see pvz.Actor#act()
	 */
	@Override
	int act() {
		return 0;
		
	}



	/* (non-Javadoc)
	 * @see pvz.Actor#takeDamage(int)
	 */
	@Override
	public int takeDamage(int damage) {

		super.takeDamage(damage);
		if (super.currHealth < (HF * 0.4)){
			this.cracked = false;
		}
		return super.currHealth;
	}

	public boolean isCracked() {
		return cracked;
	}

}
