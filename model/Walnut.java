package model;


/**
 * Walnut class creates a walnut which is a defensive plant such that several attacks by a zombie are required to break it
 * @author Abhinav Thukral
 */
public class Walnut extends Plant {

	private static final int HF = 100;
	private static final int COST = 20;
	// Default Sprite for the Plant
	private static final String DEFSPRITE = "images/walnut.jpg";
	private static final String CRACKEDSPRITE = "images/damedwalnut2";
	

	public Walnut(int level) {
		super(HF * level, level, "o", COST, DEFSPRITE);
	}

	
	/* (non-Javadoc)
	 * @see pvz.Actor#act()
	 */
	@Override
	public int act() {
		return 0;
		
	}
	
	public String getSprite(){
		if(super.isCracked()){
			return CRACKEDSPRITE;
		}
		else{
			return super.getSprite();
		}
	}
}
