package model;

/**
 * PatatoMine creates a plant that explodes on contact with a zombie if it was planted 2 turns before
 * @author Abhinav Thukral
 * 
 */
public class PotatoMine extends Plant {

	private static final int COST = 7;
	private static final int HF = 30;
	//private static final int DF =  40;
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
		if(zombieAround(grid)){
			this.turn++;
		}
		return attack(grid);
	}

	private int attack(LevelData grid){
		if(this.turn > 1){
			return this.explode(grid);
		}
		else{
			return 0; 
		}
	}
	
	private int explode(LevelData grid){
		Actor target;
		int damage = 0;
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				if(grid.zombieAt(x + i, y + j)){
					target = grid.getActorAt(x + i, y + j);
					if(target != null){
						target.takeDamage(1000);
						damage = 2;
					}
				}
			}
		}
		this.takeDamage(1000);
		return damage;
	}
	
	private boolean zombieAround(LevelData grid){
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				if(grid.zombieAt(x + i, y + j)){
					return true;
				}
			}
		}
		return false;
	}
}
