package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Observable;

/**
 * The Model class is responsible for the overall simulation of the game state
 * @author StuartMacdonald
 */
public class Model extends Observable/* implements Cloneable*/ {
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	private PlayerData currPlayer;
	private LevelData currLevel;
	private StateSaver undoManager;
	private LevelBuilder levelbuilder;

	/**
	 * 
	 * @param level determines the game level and difficulty of the game generated. only 1 level is implemented currently
	 * @throws CloneNotSupportedException 
	 */
	public Model(int level){
		currPlayer = new PlayerData(level);
		currLevel = new LevelData(level);
		levelbuilder = new LevelBuilder(level);
		undoManager = new StateSaver();
		try {
			undoManager.saveState(currLevel, currPlayer);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		this.setChanged();
	}
	

	/**
	 * simulates the game system, updates the model. Every moving part moves.
	 * @throws CloneNotSupportedException 
	 */
	public void update() throws CloneNotSupportedException{	
		Random generator = new Random();	
		
		for(Actor a: currLevel.getActorList()){	
			if(a.isAlive()){
				if(a.act(currLevel) == 5){			//sunflowers will have act(){return 5} unless anyone can think of a better way to do this?
					currPlayer.solarPower+= 5;
				}
			}
		}
		if(generator.nextInt(100) > 50){
			addZombie();
		}
		undoManager.saveState(currLevel, currPlayer);	
		this.setChanged();
		notifyObservers();
	}

	
	/**
	 * Moves a zombie from the waiting area onto the map. If there is no place to put a zombie, the zombie is returned.
	 * @return True if a zombie was added, false otherwise
	 */
	private boolean addZombie(){				
		if(currLevel.getWaitingZombiesList().size() > 0){
			Random generator = new Random();
			int y;
			int endOfList = currLevel.getWaitingZombiesList().size() - 1;
			Actor newZombie = currLevel.getWaitingZombiesList().get(endOfList);
			currLevel.getWaitingZombiesList().remove(newZombie);
			y = generator.nextInt(MAX_ROWS);
			currLevel.addActor(newZombie, MAX_COLS, y);
		}
		return false;						 	//all rows are blocked
	}

	
	/**
	 * Attempts to purchase a plant. Decreases solar reserves by the cost of the plant if successful.
	 * @param type the kind of plant to be purchased
	 * @return The newly purchased plant, or null if it is unaffordable
	 */
	private Plant purchasePlant(String type){
		Plant plant = currPlayer.getPlant(type);
		if(plant != null){
			currPlayer.solarPower -= plant.getCost();
			return plant;
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
	public boolean placePlant(int x, int y, String type){
		if(currLevel.inBounds(x, y)){
			if(!currLevel.actorAt(x, y)){
				Actor newPlant = purchasePlant(type);			//this decreases your solarPower. we should split it into createPlant() and payForPlant() methods. 
				if (newPlant != null) {							//otherwise there will be times where we will want to refund the player if they screw up.
					return(currLevel.addActor(newPlant, x, y));
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
		for(int y = 0; y <= MAX_ROWS; y++){
			if(currLevel.zombieAt(0, y)){ 
				return -1; 								//game loss if there is a zombie in the first column
			}
		}
		if(currLevel.getWaitingZombiesList().isEmpty()){
			for(Actor a: currLevel.getActorList()){
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
	 */
	public void printGrid(){
		for (int y = 0; y < MAX_ROWS; y++){
			for(int x = 0; x < MAX_COLS; x++){
				if(currLevel.actorAt(x, y)){
					currLevel.getActorAt(x, y).getSprite();
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public ArrayList<Actor> getZombies(){
		
		return this.currLevel.getWaitingZombiesList();
	}

	public int getSolarPower() {
		return currPlayer.solarPower;
	}

	public int getSolarRate() {
		return currPlayer.solarRate;
	}
	
	/**
	 * Returns the choice
	 * @return String - the string representation of the choice
	 */
	public String getChoice()
	{
		return currPlayer.getChoice();
	}
	
	/**
	 *  Sets the choice 
	 * @param choosen -the choice the user has choosen
	 */
	public void setChoice(String choosen)
	{
		this.setChanged();
		currPlayer.setChoice(choosen);
		notifyObservers();
	}
	
	/*
	public Object clone() throws CloneNotSupportedException{
		Model clone = (Model) super.clone();
		clone.currPlayer = (PlayerData) currPlayer.clone();
		clone.currLevel = (LevelData) currLevel.clone();
		return clone;
	}
	*/

	public LevelData getCurrLevel() {
		return currLevel;
	}
	
	public boolean undo(){
		if(undoManager.canUndo()){
			this.currLevel = undoManager.undoLevel();
			this.currPlayer = undoManager.undoPlayer();
			this.setChanged();
			notifyObservers();
			return(true);
		}
		return false;
	}
	
	public boolean redo(){
		if(undoManager.canRedo()){
			this.currLevel = undoManager.redoLevel();
			this.currPlayer = undoManager.redoPlayer();
			this.setChanged();
			notifyObservers();
			return(true);
		}
		return false;
	}
	
	public boolean canUndo(){
		return(undoManager.canUndo());
	}
	
	public boolean canRedo(){
		return(undoManager.canRedo());
	}
	public void loadLevelBuilder(){
		this.currLevel = levelbuilder.getZombies();
		
	}


	/**
	 * @return the levelbuilder
	 */
	public LevelBuilder getLevelbuilder() {
		return levelbuilder;
	}
	
	
}