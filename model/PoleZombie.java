package model;

/**
 * PoleZobie creates a Zombie that can move 2 steps at a time or jump a plant only once
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
	// boolean frozen to see if the zombie has been frozen
	private boolean isFrozen;
	// Default Sprite for the Zombie
	private static final String DEFSPRITE = "images/PoleZombie.jpg";
	// Cracked Sprite for the Zombie
	private static final String CRACKEDSPRITE = "images/damagedPoleZombie.jpg";

	/**
	 * @param level
	 */
	public PoleZombie(int level) {
		super((HF * level), level, "PZ", DEFSPRITE, CRACKEDSPRITE);
		this.jumped = false;
		this.isFrozen = false;
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
	public int act(LevelData grid) {
		int move = move(grid);
		if(move == 0){
			if(grid.inBounds(x-1,y)){ 
				Actor leftActor = grid.getActorAt(x - 1, y);
				if(leftActor != null){
					if (leftActor instanceof Zombie) {
						return 0;
					}
					attack(leftActor);
					return 2;
				}
				return 0;
			}
			else return -1;
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

	protected int move(LevelData grid){
		if(this.isFrozen){
			return super.move(grid);
		}
		else{
			int i = 0;
			while(i<2 && super.move(grid) > 0){
				i++;	
			}
			if(i<2){
				return jump(grid);
			}
			else{
				return 1;
			}

		}


	}
	/**
	 * Makes the zobie jump a plant
	 * @return
	 */

	private int jump(LevelData grid){
		if(!this.jumped){
			if(grid.inBounds(x - 2, y)){
				if(grid.plantAt(x-2, y)){
					return 0;
				}
				else{
					grid.getActorsAt(this.x, this.y).remove(this);
					this.x = this.x - 2;
					grid.getActorsAt(this.x, this.y).add(this);
					this.jumped = true;
					return 1;
				}
			}
			else{
				return -1;
			}
		}
		else{
			return 0;
		}
	}


	/**
	 * @param isFrozen the isFrozen to set
	 */
	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

}
