package model;

/**
 * PoleZobie creates a Zombie that can 
 * @author Abhinav Thukral
 *
 */
public class PoleZombie extends Zombie {
	// Default Health Factor multiplies with level to increase max health (Experimental)
	private static final int HF = 50; 
	// Default Damage Factor multiplies with level to increase damage (Experimental)
	private static final int DF = 10; 
	// false if zombie can jump
	private boolean jumped;

	/**
	 * @param level
	 */
	public PoleZombie(int level) {
		super((HF * level), level, "PZ");
		this.jumped = false;
	}
	

	/**
	 * @param boolean, true to prevent jumping, false otherwise
	 */
	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}


	/* (non-Javadoc)
	 * @see model.Actor#act()
	 */
	@Override
	public int act() {
		int move = move();
		if(move == 0){
			Actor actor = tile.getLeft().getOccupant();
			if (actor instanceof Zombie) {
				return 0;
			}
			attack(actor);
			return 2;	
		}
		else{
			return move;
		}
	}

	/* (non-Javadoc)
	 * @see model.Zombie#attack(model.Actor)
	 */
	@Override
	protected void attack(Actor actor) {
		actor.takeDamage(DF * super.level);
	}

	protected int move(){
		int i = 0;
		while(i<2 && super.move() > 0){
			i++;	
		}
		if(i<2){
			return jump();
		}
		else{
			return 1;
		}

	}

	private int jump(){
		if(!this.jumped){
			Tile nextTile = tile.getLeft();				
			if(nextTile != null){
				if(nextTile.isOccupied()){
					Tile nextLeft = nextTile.getLeft();
					if(nextLeft != null){
						if(nextLeft.isOccupied()){
							return 0;
						}
						else{
							super.tile.setOccupant(null);
							this.setTile(nextLeft);
							super.tile.setOccupant(this);
							return 1;
						}
					}
					else{
						return 0;
					}
				}
				return 1;
			}
			return -1;
		}
		return 0;
	}


}
