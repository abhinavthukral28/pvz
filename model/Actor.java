package model;

/**
 * Abstract Class Actor implements many common functions for plants and Zombies.
 * It acts as a super class to all plants and zombies
 * @author Abhinav Thukral
 * @version 1.0
 * 
 */
public abstract class Actor {

	// The tile that the actor occupies
	protected Tile tile;
	// Status of the Actor if it is dead or alive
	protected boolean status;
	// Current Health of the Actor
	protected int currHealth;
	// Maximum health of the Actor
	protected int maxHealth;
	// The string/graphical representation of the Actor
	protected String sprite;
	// The level of the actor based on the level of the game
	protected int level;		
	// True if the Actor is allied with the player
	protected boolean friendly;
	
	/**
	 * Constructor for class Actor, usually only used by sub classes
	 * @param maxHealth
	 * @param level
	 * @param sprite
	 */
	public Actor(int maxHealth,
			int level, String sprite, boolean friendly) {
		super();
		this.status = true;
		this.currHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.sprite = sprite;
		this.level = level;
		this.friendly = friendly;
	}
	/**
	 * 
	 * just a place holder method for sub classes.
	 */
	 abstract int act();
	 
	
	
	/**
	 * @return current Health
	 */
	public int getCurrHealth() {
		return currHealth;
	}
	/**
	 * @return Maximum Health
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * Reduces the currHealth of the Actor by integer amount specified in damage
	 * @param damage
	 * @return currentHealth
	 */
	public int takeDamage(int damage) {
		currHealth = currHealth - damage;
		if (currHealth <= 0) {
			die();
		}
		return currHealth;
	}
	
	/**
	 * Die method for the actor, changes the status of the Actor to false
	 */
	private void die() {
		status = false;
		currHealth = 0;
		this.tile.setOccupant(null);
		setTile(null);
	}
	
	/**
	 * Method for changing the tile that contains the Actor
	 * @param tile
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}
	/**
	 * 
	 * @return tile of the Actor
	 */
	public Tile getTile() {
		return tile;
	}	
	/**
	 * @return status that is true if its alive, otherwise false if its dead
	 */
	public boolean isAlive() {
		return status;
	}
	
	/**
	 * @return Sprite representation of the actor
	 */
	public String toString(){
		return sprite;
	}
	/**
	 * @return Friendly allegience of actor. True for plants, false for zombies
	 */
	public boolean isFriendly(){
		return friendly;
	}
	
}
