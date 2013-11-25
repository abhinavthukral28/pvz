package model;

/**
 * PatatoMine creates a plant that explodes on contact with a zombie if it was planted 2 turns before
 * @author Abhinav Thukral
 * 
 */
public class PatatoMine extends Plant {

	private static final int COST = 7;
	private static final int HF = 30;
	private static final int DF =  40;
	private int turn;
	/**
	 * @param level
	 */
	public PatatoMine(int level) {
		super((HF*level), level, "P", COST);
		turn = 0;
	}

	/* (non-Javadoc)
	 * @see model.Actor#act()
	 */
	@Override
	public int act() {
		this.turn++;
		return attack();
	}
	
	private int attack(){
		Tile tile = super.tile.getRight();
		if(tile != null && tile.isOccupied() && this.turn > 2){
			if(!(tile.getOccupant().isFriendly())){
				tile.getOccupant().takeDamage(DF);
				return 2;
			}
			
		}
		
		return 0;
	}
	

}
