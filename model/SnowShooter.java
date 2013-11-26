/**
 * 
 */
package model;

/**
 * SnowShooter freezes the poleZombies and prevents them from jumping and slows down there speed.
 * It also deals double damage to regular zombies.
 * @author Abhinav Thukral
 *
 */
public class SnowShooter extends Plant {
	// Default Health Factor multiplies with level to increase max health (Experimental)
		private static final int HF = 40;
		// Default Damage Factor multiplies with level to increase damage (Experimental)
		private static final int DF = 10; 
		// The solar cost of the plant
		private static final int COST = 4;
		// Default Sprite for the Plant
		private static final String DEFSPRITE = "images/peashooterFreeze.jpg";
		
	/**
	 * @param level
	 */
	public SnowShooter(int level) {
		super((HF*level), level, "SS", COST, DEFSPRITE);
	}

	/* (non-Javadoc)
	 * @see model.Actor#act()
	 */
	@Override
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
				if (actor instanceof Zombie) {
					if(actor instanceof PoleZombie){
						((PoleZombie) actor).setFrozen(true);
						actor.takeDamage(DF * super.level);
						return 2;
					}
					else{
						actor.takeDamage(2 * DF * super.level);
						return 2;
					}
					
				}
			}
				
		}
		return 0;
		
	}

}
