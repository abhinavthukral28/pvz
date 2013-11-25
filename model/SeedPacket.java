package model;


/**
 * SeedPacket class used to add new plants to the grid
 * Note: Sections related to the delay have been commented and will be implemented in the next mile stone as per the requirement
 *	@author Stuart
 *	@author Abhinav
 */
public class SeedPacket {
	private int level;
	//private Map<String, Integer> map;
		
	/**
	 * initializes the seeds
	 * @param level
	 */
	public SeedPacket(int level){
		this.level = level;
	}
	
	/**
	 * 
	 * @param string
	 * @param suns
	 * @return requested Plant if it exists else returns null
	 */
	public Plant getPlant(String string, int suns) {
		Plant plant = null;
		if(string.equals("sunflower")){
			plant = new SunFlower(level);
		}
		
		else if (string.equals("shooter")){
			plant = new Shooter(level);
		}
		else{
			return null;
		}
		// Check if there are enough suns for the plant
		if(plant.getCost() > suns){
			return null;
		}
		else{
			return plant;
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
