package pvz;

import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;

public class Model extends Observable {
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	private String choice;
	private Seeds seeds;
	private ArrayList<Actor> actorList;
	private ArrayList<Actor> waitingZombiesList;
	private ArrayList<Tile> gameGrid; 
	private int solarPower;	
	private int solarRate;
	private int level;
	//private ArrayList<View> observerViews;
	

	
	/**
	 * 
	 * @param level determines the game level and difficulty of the game generated. only 1 level is implemented currently
	 */
	public Model(int level){
		this.level = level;
		seeds = new Seeds(level);
		actorList = new ArrayList<Actor>();
		waitingZombiesList = new ArrayList<Actor>();
		gameGrid = new ArrayList<Tile>();
		for(int y = 0; y < MAX_ROWS; y++){				//for each row
			Tile newTile = new Tile();					
			gameGrid.add(newTile);						//add a new tile at the start of the row
			for(int x = 0; x < MAX_COLS; x++){			//for each column in the row
				Tile tempTile = gameGrid.get(y);
				while(tempTile.getRight() != null){		//navigate to the end of the row
					tempTile = tempTile.getRight();
				}
				tempTile.getRight(new Tile());			//link on a new tile
				tempTile.getRight().setLeft(tempTile);	//and link it back
			}
		}
		
		solarPower = 10;
		solarRate = 5;
		if(level == 1){												//load the zombies, etc... for level 1
			for(int x = 0; x < 5; x++){
				waitingZombiesList.add(new DefZombie(1)); 			//add some basic zombies
			}
		}
		this.setChanged();
	}
	

	/**
	 * simulates the game system, updates the model. Every moving part moves.
	 */
	public void update(){	
		Random generator = new Random();
		
		solarRate = 5;
		for(Actor a: actorList){	
			if(a.isAlive()){
				if(a.act() == 5){			//sunflowers will have act(){return 5} unless anyone can think of a better way to do this?
					solarPower+= 5;
				}
			}
		}

		solarPower += solarRate;
		if(generator.nextInt(100) > 50){
			addZombie();
		}
		this.setChanged();
		//printGrid();
		//System.out.println("You have " + solarPower + " sun points to spend.");
		//notifyViews();
	}
	
	//the current components of the level are accessible
	//their public accessible attributes should be related to rendering
	
	/**
	 * Moves a zombie from the waiting area onto the map. If there is no place to put a zombie, the zombie is returned.
	 * @return True if a zombie was added, false otherwise
	 */
	private boolean addZombie(){				
		if(waitingZombiesList.size() > 0){
			int endOfList = waitingZombiesList.size() - 1;
			Actor newZombie = waitingZombiesList.get(endOfList);
			waitingZombiesList.remove(newZombie);
			Random generator = new Random();
			int y;
			int tries = 0;
			y = generator.nextInt(MAX_ROWS);
			Tile destination = getTile(MAX_COLS, y);
			while(tries < 5){			//if the spot is occupied, choose another
				if(destination != null){
					if(destination.getOccupant() == null){
						newZombie.setTile(destination);
						destination.setOccupant(newZombie);
						actorList.add(newZombie);
						//System.out.println("A zombie appeared at "+ MAX_COLS + " " + y);
						return true;
					}
				}
				y = (y + 1) % MAX_COLS;
				tries++;
			}
			waitingZombiesList.add(newZombie);		//zombie goes back in line
		}
		return false;						 	//all rows are blocked
	}

	/**
	 * Searches for and returns a requested tile.
	 * @param x coordinate of a Tile
	 * @param y coordinate of a Tile
	 * @return the Tile at the given location
	 */
	public Tile getTile(int x, int y){
		if(x >= 0 && x <= MAX_COLS && y >= 0 && y < MAX_ROWS){
			Tile baseTile = gameGrid.get(y);
			for(int n = 0; n < x; n++){
				baseTile = baseTile.getRight();
			}
			return baseTile;
		}
		return null;		//kind of rude
	}
	
	/**
	 * Attempts to purchase a plant. Decreases solar reserves by the cost of the plant if successful.
	 * @param type the kind of plant to be purchased
	 * @return The newly purchased plant, or null if it is unaffordable
	 */
	private Actor purchasePlant(String type){
		Actor actor = seeds.getPlant(type, solarPower);
		if(actor != null){
			solarPower -= actor.getCost();
			return actor;
		}
		else{
			return null;
		}
	}
	
	/**
	 * Puts a plant on the map, given a co-ordinate pair, and the name of a plant type.
	 * @param x destination co-ordinate for the plant
	 * @param y destination co-ordinate for the plant
	 * @param type the type of plant to be placed
	 * @return True if the plant was placed, false otherwise;
	 */
	public boolean placePlant(Tile destination, String type){
		if(destination != null){
			if(destination.getOccupant() == null){
				Actor newPlant = purchasePlant(type);			//this decreases your solarPower. we should split it into createPlant() and payForPlant() methods. 
				if (newPlant != null) {							//otherwise there will be times where we will want to refund the player if they screw up.
					actorList.add(newPlant);
					newPlant.setTile(destination);
					destination.setOccupant(newPlant);
					return true;
				}
			}
		}
		return false;		
	}
	
	/**
	 * Checks the game state for a win or a loss.
	 * Win if there are no zombies on the field, and no zombies waiting.
	 * Loss if there is a zombie in the first column.
	 * @return -1 if the player lost, 1 if they won, 0 otherwise
	 */
	public int state(){
		Tile tempTile = null;
		for(int y = 0; y <= MAX_ROWS; y++){
			tempTile = getTile(0,y);
			if(tempTile != null){
				if(tempTile.isOccupied()){
					if(!getTile(0,y).getOccupant().isFriendly()){ 
						return -1; 								//game loss if there is a zombie in the first column
					}
				}
			}
		}
		if(waitingZombiesList.isEmpty()){
			for(Actor a: actorList){
				if(!a.isFriendly() && a.isAlive()){								
					return 0;
				}
			}
			return 1;										//game win if there are no zombies on the field, and no zombies waiting
		}
		return 0;
	}
	
	/**
	 * Primitive display method. A view system will be responsible for all of this in later versions.
	 *//*
	public void printGrid(){
		for (int y = 0; y < MAX_ROWS; y++){
			Tile tempTile = gameGrid.get(y);
			while(tempTile != null){
				System.out.print(tempTile.toString());
				tempTile = tempTile.getRight();
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	*/
	public ArrayList<Actor> getZombies(){
		
		return this.waitingZombiesList;
	}


	public ArrayList<Tile> getGameGrid() {
		return gameGrid;
	}


	public int getSolarPower() {
		return solarPower;
	}


	public int getSolarRate() {
		return solarRate;
	}


	public int getLevel() {
		return level;
	}


	public ArrayList<Actor> getActorList() {
		return actorList;
	}


	public void setActorList(ArrayList<Actor> actorList) {
		this.actorList = actorList;
	}


	public ArrayList<Actor> getWaitingZombiesList() {
		return waitingZombiesList;
	}


	public void setWaitingZombiesList(ArrayList<Actor> waitingZombiesList) {
		this.waitingZombiesList = waitingZombiesList;
	}
	
	/*
	public boolean addView(View newView){
		return observerViews.add(newView);
	}
	
	public boolean removeView(View toRemove){
		return observerViews.remove(toRemove);
	}
	
	public void notifyViews(){
		for(View v: observerViews){
			v.update(this, null);
		}
	}
	*/
	public String getChoice()
	{
		return choice;
	}
	
	public void setChoice(String choosen)
	{
		this.setChanged();
		choice=choosen;
	}
}
