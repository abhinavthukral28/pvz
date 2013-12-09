package model;

import java.util.ArrayList;

/**
 * 
 * @author AlhetiMamoon
 * this class is responsible for the level building process
 *
 */

public class LevelBuilder {
	
	private ArrayList<Zombie> zombies;
	
	public LevelBuilder(){
		
		zombies = new ArrayList<Zombie>();
		
	}
	
	public void addZombie(Zombie z){
		
		zombies.add(z);
		
	}
	public void removeZombie(Zombie z){
		
		zombies.remove(z);
	}
	
	public ArrayList<Zombie> getZombies(){
		
		return this.zombies;
	}
	

}
