package pvz;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	private ArrayList<Seeds> seedList;
	private ArrayList<Plant> plantList;
	private ArrayList<Zombie> zedList;
	//inactive zombies?
	private (2d array)<Tile> gameGrid;
	private int solarPower;
	private int solarRate;
	
	private Model(){
		plantList = new ArrayList<Plant>();
		zedList = new ArrayList<Zombie>>();
		solarPower = 0;
		solarRate = 5;
		//gameGrid
	}
	
	void update(){			
		solarRate = 5;
		for(Plant p: plantList){	
			p.update();
			if(p.getType() == SUNFLOWER){
				solarRate += 5;
			}
		}
		for(Zombie z: zedList){
			z.update();
		}
		for(Seed s: seeedList){
			s.update();
		}
		for(Plant p: plantList){	
			if(!p.isAlive()){
				plantList.remove(p);
			}
		}
		for(Zombie z: zedList){
			if(!z.isAlive()){
				zedList.remove(z);
			}
		}
		solarPower += solarRate;
		//feed in inactive zombies
		//check winstate
		//notify observers
	}
	
	//the current components of the level are accessible
	//their public accessible attributes are related to rendering
	public ArrayList<Plant> getPlantList() {
		return plantList;
	}
	public ArrayList<Zombie> getZedList() {
		return zedList;
	}
	public TYPE getGamegrid(){
		return gameGrid;
	}

}
