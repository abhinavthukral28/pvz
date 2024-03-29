package model;


/**
 * This class creates a SunFlower plant
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class SunFlower extends Plant {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 25;
	// The solar cost of the plant
	private static final int COST = 1;
	// Default Sprite for the Plant
	private static final String DEFSPRITE = "images/sunflower.jpg";
	// Number of turns that have passed since the creation of the SunFlower
	private int turn;
	

	/**
	 * 
	 * @param level
	 */
	public SunFlower(int level) {
		super((HF * level), level, "F", COST, DEFSPRITE);
		turn = 3;
	}
	
	/** 
	 * act() method for SunFlower, generates suns on every third turn since the creation of the plant
	 * @return 5 if sun is generated else returns a 0
	 */
	public int act(LevelData grid) {
		if((this.turn % 3) == 0){
			turn++;
			return 5;
		}
		turn++;
		return 0;
	}
}
