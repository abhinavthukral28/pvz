package pvz;


/**
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class SunFlower extends Actor {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 25;
	// The solar cost of the plant
	private static final int COST = 30;
	// Number of turns that have passed since the creation of the SunFlower
	private int turn;
	

	/**
	 * 
	 * @param tile
	 * @param level
	 */
	public SunFlower(int level) {
		super((HF * level), level, "F", true);
		turn = 3;
	}
	
	/** 
	 * act() method for SunFlower, generates suns on every third turn since the creation of the plant
	 * @return 5 if sun is generated else returns a 0
	 */
	public int act() {
		if((this.turn % 3) == 0){
			turn++;
			return 5;
		}
		turn++;
		return 0;
	}
	
	public int getCost(){
		return COST;
	}
	
}
