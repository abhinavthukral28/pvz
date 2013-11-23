
package pvz;
/**
 * The Plant class 
 * @author Abhinav Thukral
 * @version 1.0
 *
 */
public abstract class Plant extends Actor{

public Plant(int maxHealth, int level, String sprite, boolean friendly) {
	super(maxHealth, level, sprite, friendly);
}
	/** 
	 * act() method for plants
	 * @return 
	 */
	public abstract int act(); 


	/**
	 * @return the cost of the plant
	 */
	public abstract int getCost(); 

}