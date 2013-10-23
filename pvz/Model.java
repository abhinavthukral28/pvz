package pvz;

import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;

public class Model extends Observable {
	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 12;
	private ArrayList<Seeds> seedList;
	private ArrayList<Actor> actorList;
	private ArrayList<Actor> waitingZombiesList;
	private ArrayList<Tile> gameGrid; 
	private int solarPower;	
	private int solarRate;
	

	private Model(int level){
		seedList = new ArrayList<Seeds>();
		actorList = new ArrayList<Actor>();
		waitingZombiesList = new ArrayList<Actor>();
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
				tempTile.getNext().setPrevious(tempTile);	//and link it back
			}
		}
		
		solarPower = 0;
		solarRate = 5;
		if(level == 1){												//load the zombies, etc... for level 1
			//seedList.add(Seeds(new SunFlower(null, 1), 20));		//add seedpackets for the two Plant types
			//seedList.add(Seeds(new Shooter(null, 1), 40));
			for(int x = 0; x < 10; x++){
				waitingZombiesList.add(new DefZombie(1)); 			//add some basic zombies
			}
		}
	}
	
	public static void main(String arg[]){
		int gameState = 0;
		Model game = new Model(1);
		while(gameState == 0){
			//get player input
			//possibly place a plant
			game.update();
			game.printGrid();
			//gameState = game.winState();
		}/*
		if(gameState == 1){
			//You won! Congraturation
		}
		else if(gameState == -1){
			//You lost. Mission Failed
		}*/
	}
	
	public void update(){	
		Random generator = new Random();
		
		solarRate = 5;
		for(Actor a: actorList){	
			if(a.act() == 5){			//sunflowers will have act(){return 5} unless anyone can think of a better way to do this?
				solarPower+= 5;
			}
		}
		/*for(Seeds s: seedList){
			s.update();
		}*/
		for(Actor a: actorList){	
			if(!a.isAlive()){
				actorList.remove(a);
			}
		}
		solarPower += solarRate;
		if(generator.nextInt(100) > 50){
			addZombie(); 
		}
		//notify observers
	}
	
	//the current components of the level are accessible
	//their public accessible attributes should be related to rendering
	
	private boolean addZombie(){				
		int endOfList = waitingZombiesList.size() - 1;
		Actor newZombie = waitingZombiesList.get(endOfList);
		waitingZombiesList.remove(newZombie);
		Random generator = new Random();
		int y;
		int tries = 0;
		y = generator.nextInt(MAX_ROWS);
		Tile destination = getTile(MAX_COLS ,y);
		while(tries < 5){			//if the spot is occupied, choose another
			if(destination.getOccupant() == null){
				newZombie.setTile(destination);
				destination.setOccupant(newZombie);
				return true;
			}
			y = (y + 1) % MAX_COLS;
			tries++;
		}
		waitingZombiesList.add(newZombie);		//zombie goes back in line
		return false;						 	//all rows are blocked
	}

	public Tile getTile(int x, int y){
		Tile baseTile = gameGrid.get(y);
		for(int n = 0; n < x; n++){
			baseTile = baseTile.getNext();
		}
		return baseTile;
	}
	
	/*
	public boolean purchasePlant(int type){
		if(seedList.get(type).purchasePlant(solarPower)){
			solarPower -= seedList.get(type).getCost();
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean placePlant(int x, int y, Actor purchasePlant){
		Tile destination = getTile(x,y);
		if(destination.getOccupant() == null){								//if the spot for the plant is empty
			if(this.purchasePlant(type)){									//and if the plant can be successfully purchased
				newPlant.setTile(destination);
				destination.setOccupant(newPlant);
				return true;
			}
		}
		return false;		
	}
	*/
	public int winState(){
		for(int y = 0; y < MAX_ROWS; y++){
			if(!getTile(0,y).isOccupied()){
				if(!getTile(0,y).getOccupant().isFriendly()){ 
					return -1; 								//game loss if there is a zombie in the first column
				}
			}
		}
		if(waitingZombiesList.isEmpty()){
			for(Actor a: actorList){
				if(!a.isFriendly()){								
					return 0;
				}
			}
			return 1;										//game win if there are no zombies on the field, and no zombies waiting
		}
		return 0;
	}
	
	private void printGrid(){
		for (int y = 0; y < MAX_ROWS; y++){
			Tile tempTile = gameGrid.get(y);
			for(int x = 0; x < MAX_COLS; x++){
				if(tempTile != null){
					System.out.print(tempTile.toString());
				}
				tempTile = tempTile.getNext();
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	public ArrayList<Seeds> getSeedList() {
		return seedList;
	}

	public ArrayList<Actor> getWaitingZombiesList() {
		return waitingZombiesList;
	}

	public int getSolarPower() {
		return solarPower;
	}

	public int getSolarRate() {
		return solarRate;
	}

}
