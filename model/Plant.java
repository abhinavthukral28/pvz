
package model;
/**
 * The Plant class 
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public abstract class Plant extends Actor{
	
	protected int cost;

public Plant(int maxHealth, int level, String string, int cost, String sprite) {
	super(maxHealth, level, string, true, sprite);
	this.cost = cost;
}

	/**
	 * @return the cost of the plant
	 */
	public int getCost(){
		return cost;
	}

}