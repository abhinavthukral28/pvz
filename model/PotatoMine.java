package model;

/**
 * PatatoMine creates a plant that explodes on contact with a zombie if it was planted 2 turns before
 * @author Abhinav Thukral
 * 
 */
public class PotatoMine extends Plant {

	private static final int COST = 7;
	private static final int HF = 30;
	private static final int DF =  40;
	// Default Sprite for the Plant
	private static final String DEFSPRITE = "images/potatoMine.jpg";
	private int turn;
	/**
	 * @param level
	 */
	public PotatoMine(int level) {
		super((HF*level), level, "P", COST, DEFSPRITE);
		turn = 0;
	}

	/** 
	 * Act method for this class
	 * @returns 0 for no movement, 1 for movement and 2 for successful attack
	 */
	@Override
	public int act(LevelData grid) {
		this.turn++;
		return attack(grid);
	}
	
	private int attack(LevelData grid){
		if(grid.zombieAt(this.x + 1, this.y) && this.turn > 2){
			grid.getActorAt(this.x + 1, this.y).takeDamage(DF);
			this.takeDamage(1000);
			return 2;
		}
		return 0;
	}
}
