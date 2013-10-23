package pvz;


/**
 * @author Abhinav Thukral
 * 
 *
 */
public class SunFlower extends Actor {
	
	private int turn;

	public SunFlower(Tile tile, int maxHealth, int level) {
		super(tile, maxHealth, level, "F");
		turn = 3;
	}
	
	public int act() {
		if((this.turn % 3) == 0){
			turn++;
			return 5;
		}
		turn++;
		return 0;
	}
	
}
