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
		this.cracked = false;
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
			this.cracked = true;
		}
		return super.currHealth;
	}
/**
 * isCracked tells if the walnut is near 'death' such that the GUI can be updated
 * @return
 */
	public boolean isCracked() {
		return cracked;
	}

}
