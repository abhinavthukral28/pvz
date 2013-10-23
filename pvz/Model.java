package pvz;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 12;
	private ArrayList<Seeds> seedList;
	private ArrayList<Actor> actorList;
	private ArrayList<Zombie> waitingZombiesList;
	private ArrayList<Tile> gameGrid; 
	private int solarPower;	
	private int solarRate;
	
	private Model(){
		seedList = new ArrayList<Seeds>();
		actorList = new ArrayList<Actor>();
		waitingZombiesList = new ArrayList<Zombie>();
		gameGrid = new ArrayList<Tile>();
		for(int y = 0; y < MAX_ROWS; y++){				//for each row
			Tile newTile = new Tile();					
			gameGrid.add(newTile);						//add a new tile at the start of the row
			for(int x = 0; x < MAX_COLS; x++){			//for each column in the row
				Tile tempTile = gameGrid.get(y);
				while(tempTile.getNext() != null){		//navigate to the end of the row
					tempTile = tempTile.getNext();
				}
				tempTile.setNext(new Tile());			//link on a new tile
				tempTile.getNext().setLast(tempTile);	//and link it back
			}
		}
		solarPower = 0;
		solarRate = 5;
	}
	
	private Model(int level){
		new Model();
		if(level == 1){			//load the zombies, etc... for level 1
			seedList.add(Seeds(new Sunflower(), 20, 4));		//add seedpackets for the two Plant types
			seedList.add(Seeds(new Shooter(), 40, 5));
			for(int x = 0; x < 10; x++){
				waitingZombiesList.add(new Zombie()); //add some basic zombies
			}
		}
	}
	
	public void update(){			
		solarRate = 5;
		for(Actor a: actorList){	
			if(a.act() == 5){			//sunflowers will have act(){return 5} unless anyone can think of a better way to do this?
				solarPower+= 5;
			}
		}
		for(Seed s: seedList){
			s.update();
		}
		for(Actor a: actorList){	
			if(!a.isAlive()){
				actorList.remove(a);
			}
		}
		solarPower += solarRate;
		addZombie(); //but not every turn, have a fixed or random chance
		//notify observers
	}
	
	//the current components of the level are accessible
	//their public accessible attributes should be related to rendering
	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	public ArrayList<Seeds> getSeedList() {
		return seedList;
	}

	public ArrayList<Zombie> getWaitingZombiesList() {
		return waitingZombiesList;
	}

	public int getSolarPower() {
		return solarPower;
	}

	public int getSolarRate() {
		return solarRate;
	}

	
	private boolean addZombie(){				
		Zombie newZombie = waitingZombiesList.get(waitingZombiesList.size()); 				//pick the last one in line
		waitingZombiesList.remove(newZombie.indexOf());
		int x, y, tries = 0;
		//y = random row
		//x = far right column
		Tile destination = getTile(x,y));
		while(tries < 5){			//if the spot is occupied, choose another
			if(destination.getOccupant() == null){
				newZombie.setPosition(destination);
				destination.setOccupant(newZombie);
			}
			y = (y + 1) % MAX_COLS;
			tries++;
		}
		waitingZombiesList.add(newZombie);		//zombie goes back in line
		return false;						 	//all rows are blocked
	}

	public Tile getTile(int x, int y){
		Tile baseTile = gameGrid[y];
		for(int n = 0; n < x; n++){
			baseTile = baseTile.getNext();
		}
		return baseTile;
	}
	
	public boolean purchasePlant(int type){
		if(seedList[type].purchasePlant(solarPower)){
			solarPower -= seedList[type].getCost();
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean placePlant(int x, int y, int type){
		Plant newPlant;
		Tile destination = getTile(x,y);
		if(destination.getOccupant() == null){								//if the spot for the plant is empty
			if(this.purchasePlant(type)){									//and if the plant can be successfully purchased
				//newPlant = this.getSeedList().get(type).getPlantType();	//broken, will just make another reference to the plant
				newPlant.setPos(destination);
				destination.setOccupant(newPlant);
				return(addActor(newPlant));
			}
		}
		return false;		
	}
	
	public int winState(){
		for(int y = 0; y < MAX_ROWS; y++;){
			if(actorAt(0, y) != null){
				if(actorAt(0, y)){ 			//is a zombie
					return -1; 				//game loss if there is a zombie in the first column
				}
			}
		}
		if(waitingZombiesList.isEmpty()){
			for(Actor a: actorList){
				if(a){						//is a zombie
					return 0;
				}
			}
			return 1;						//game win if there are no zombies on the field, and no zombies waiting
		}
		return 0;
	}
}
