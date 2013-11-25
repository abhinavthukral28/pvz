
package pvz;
/**
 * The Plant class 
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public abstract class Plant extends Actor{
	
	protected int cost;

public Plant(int maxHealth, int level, String sprite, int cost) {
	super(maxHealth, level, sprite, true);
	this.cost = cost;
}

	/**
	 * @return the cost of the plant
	 */
	public int getCost(){
		return cost;
	}

}