package pvz;

/**
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class Shooter extends Actor {

	public Shooter(Tile tile, int maxHealth, int level) {
		super(tile, maxHealth, level, "S");
	}
	
	public int act() {
		return attack();
	}
	
	private int attack() {
		Tile tempTile = super.tile;
		while(tempTile != null){
			tempTile = tempTile.getPrevious();
			if(tempTile != null && tempTile.isOccupied()){
				Actor actor = tempTile.getOccupant();
				if (actor instanceof DefZombie) {
					actor.takeDamage(10);
					return 2;
				}
			}
				
		}
		return 0;
		
	}
	
	

}
