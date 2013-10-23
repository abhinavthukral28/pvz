package pvz;

public class Seeds {
	private Actor plantType;
	private int cost;	
	//private int cooldown;		//not needed for milestone 1
	//private int delay;
		
	public Seeds(Actor type, int cost/*, int delay*/){
		plantType = type;
		this.cost = cost;		
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
	
	public boolean purchasePlant(int suns){
		if(/*!isEnabled() || */suns < cost){		//can't purchase plant if it is on cooldown, or if you don't have enough sunlight
			return false;
		}
		else{
			//cooldown = delay;
			return true;
		}
	}

	public Actor getPlantType() {				//not a good idea! want to return a new plant of the given type
		return plantType;
	}

	public int getCost() {
		return cost;
	}
	/*
	public int getCooldown() {
		return cooldown;
	}
	*/
}
