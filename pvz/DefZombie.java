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
		

	public DefZombie(Tile tile,int level, String sprite) {
		super(tile, (HFDZ * level), level, "T");
	}
	
	public void act(){
		
	}
	/**
	 * still to be properly implemented based on the grid
	 */
	private void move() {
		Tile nextTile = tile.getNextTile();
		if(nextTile != null){
			if(nextTile.isOccupied()){
				return;
			}
			else {
				super.tile = nextTile;
				return;
			}
			
		}
		
	}
	
	private void attack(Actor actor) {
		actor.takeDamage(DFDZ * super.level);
	}
	

}
