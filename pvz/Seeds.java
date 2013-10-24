package pvz;
/**
 * Seeds class used to add new plants to the grid
 * Note: Sections related to the delay have been commented and will be implemented in the next mile stone as per the requirement
 *
 */
public class Seeds {
	private int level;
	//private Map<String, Integer> map;
		
	/**
	 * initializes the seeds
	 * @param level
	 */
	public Seeds(int level){
		this.level = level;
		//map = new HashMap<String,Integer>();
		//this.delay = delay;
	
	}
	
	/*
	public void update(){
		if(cooldown > 0){
			cooldown--;
		}
	}
		
	public boolean isEnabled(){
		if(cooldown > 0){
			return false;
		}
		return true;
	}*/
	
	/**
	 * 
	 * @param string
	 * @param suns
	 * @return requested Plant if it exists else returns null
	 */
	public Actor getPlant(String string, int suns) {
		Actor actor = null;
		if(string.equals("sunflower")){
			actor = new SunFlower(level);
		}
		
		else if (string.equals("shooter")){
			actor = new Shooter(level);
		}
		else{
			return null;
		}
		// Check if there are enough suns for the plant
		if(actor.getCost() > suns){
			return null;
		}
		else{
			return actor;
		}
	}

	/*
	 * for cooldown
	 * to be implemented later
	 *
	private void MapPopulate(){
		map.put("sunflower", 3);
		map.put("shooter", 5);
		
	}*/
	/*
	public int getCooldown() {
		return cooldown;
	}
	*/
}
