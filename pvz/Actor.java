package plantszombies;

/**
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class Actor {
	protected int x;									// x position
	protected int y;									// y position
	protected boolean status;
	protected int currHealth;
	protected int maxHealth;
	protected String sprite;
	protected int level;								// game level
	
	public Actor(int x, int y, int maxHealth,
			int level, String sprite) {
		super();
		this.x = x;
		this.y = y;
		this.status = true;
		this.currHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.sprite = sprite;
		this.level = 1;
	}
	
	public int takeDamage(int damage) {
		currHealth = currHealth - damage;
		if (currHealth <= 0) {
			die();
		}
		return currHealth;
	}
	
	private void die() {
		status = false;
		currHealth = 0;
	}
	/**
	 * setPoaition is yet to be fully implemented depending on the implementation of the grid (to be discussed in meeting)
	 * @param x
	 * @param y
	 */
	
	public void setPostion(int x, int y){
		this.x = this.x + x;
		this.y = this.y + y;
	}
	
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isAlive() {
		return status;
	}
	
	
}