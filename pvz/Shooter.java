package pvz;

/**
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class Shooter extends Actor {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 40;
	// Default Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DF = 10; 
	private static final int COST = 50;
	
	/**
	 * @param tile
	 * @param level
	 */
	public Shooter(int level) {
		super((HF * level), level, "S", true);
	}
	
	/**
	 * @return 2 if zombie successfully attacked else returns 0
	 */
	public int act() {
		return attack();
	}
	
	/**
	 * attack() iterates through the grid to check if there is any zombie in the vicinity
	 * @return 2 if zombie successfully attacked else returns 0
	 */
	private int attack() {
		// iterates through the grid to till it reaches the end of the grid
		Tile tempTile = super.tile;
		while(tempTile != null){
			tempTile = tempTile.getRight();
			if(tempTile != null && tempTile.isOccupied()){
				Actor actor = tempTile.getOccupant();
				if (actor instanceof DefZombie) {
					actor.takeDamage(DF * super.level);
					return 2;
				}
			}
				
		}
		return 0;
		
	}
	
	public int getCost(){
		return COST;
	}
	
	

}
