package pvz;
import java.util.*;

public class Seeds {
	private int level;
	private Map<String, Integer> map;
		
	public Seeds(int level){
		this.level = level;
		map = new HashMap<String,Integer>();
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
	

	public Actor getPlant(String string, int suns) {//not a good idea! want to return a new plant of the given type
		Actor actor = null;
		if(string.equals("sunflower")){
			actor = new SunFlower(null,level);
		}
		
		else if (string.equals("sunflower")){
			actor = new SunFlower(null,level);
		}
		else{
			return null;
		}
		if(actor.getCost() > suns){
			return null;
		}
		else{
			return actor;
		}
	}

	
	private void MapPopulate(){
		map.put("sunflower", 3);
		map.put("shooter", 5);
		
	}
	/*
	public int getCooldown() {
		return cooldown;
	}
	*/
}
