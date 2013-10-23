package pvz;

/**
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public class Actor {
	protected Tile tile;
	protected boolean status;
	protected int currHealth;
	protected int maxHealth;
	protected String sprite;
	protected int level;								// game level
	
	public Actor(Tile tile, int maxHealth,
			int level, String sprite) {
		super();
		this.tile = tile;
		this.status = true;
		this.currHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.sprite = sprite;
		this.level = level;
	}
	public void act(){
		return;
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
	
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public boolean isAlive() {
		return status;
	}
	
	
	
}