package model;

import java.util.ArrayList;

/**
 * Contains the data associated with the current level.
 * The Actors, the oncoming zombies, and the grid.
 * @author StuartMacdonald
 *
 */

public class LevelData implements Cloneable{
	public static final int MAX_Y = 6;
	public static final int MAX_X = 12;
	
	private ArrayList<Actor> actorList;
	private ArrayList<Actor> waitingZombiesList;
	//grid may be redundant. All the positional information in it is also stored in ActorList.
	private int level;

	public LevelData(int level) {
		this.level = level;
		actorList = new ArrayList<Actor>();
		waitingZombiesList = new ArrayList<Actor>();
		
		for(int x = 0; x < (5 + level); x++){
			getWaitingZombiesList().add(new DefZombie(level)); 			//add some basic zombies
		}
		for(int x = 0; x < (level); x++){
			getWaitingZombiesList().add(new PoleZombie(level)); 			//add some pole-vault zombies
		}
		for(int x = 0; x < (level+2); x++){
			getWaitingZombiesList().add(new ExplosiveZombie(level)); 	//add some exploding zombies
		}
	}
	/**
	 * @return the maxY
	 */
	public static int getMaxY() {
		return MAX_Y;
	}

	/**
	 * @return the maxX
	 */
	public int getMaxX() {
		return MAX_X;
	}
	
	public ArrayList<Actor> getActorList() {
		return actorList;
	}
	
	/**
	 * Gets all actors that are currently alive. We don't remove dead actors, just sort of ignore them.
	 * @return all actors that are in play and alive
	 */
	public ArrayList<Actor> getLivingActors(){
		ArrayList<Actor> liveOnes = new ArrayList<Actor>();
		for(Actor a: actorList){
			if(a.isAlive()){
				liveOnes.add(a);
			}
		}
		return liveOnes;
	}

	public ArrayList<Actor> getWaitingZombiesList() {
		return waitingZombiesList;
	}

	/**
	 * @param waitingZombiesList the waitingZombiesList to set
	 */
	public void setWaitingZombiesList(ArrayList<Actor> waitingZombiesList) {
		this.waitingZombiesList = waitingZombiesList;
	}
/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return true if there is an actor at the given coordinates
	 */
	public boolean actorAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x, y) && a.isAlive()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return true if there is a zombie at the coordinate
	 */
			
	public boolean zombieAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x, y)){
				if(!a.isFriendly() && a.isAlive()){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Adds a new Actor to the level at a certain position. Position is stored in the actor, and in the grid.
	 * Actor is also enrolled in the actorList
	 * @param newActor
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean addActor(Actor newActor, int x, int y){
		return(newActor.setXY(x, y) && actorList.add(newActor));
	}
	
	/**
	 * Searches the grid for a plant actor at a position.
	 * Because this information is also stored in actors, a search of the actorList would also work
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean plantAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x,y)){
				if(a.isFriendly() && a.isAlive()){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return a list of all the actors at the coordinates
	 */
	public ArrayList<Actor> getActorsAt(int x, int y){
		ArrayList<Actor> returnList = new ArrayList<Actor>();
		for(Actor a: actorList){
			if(a.isAt(x,y) && a.isAlive()){
				returnList.add(a);
			}
		}
		return returnList;
	}
	
	/**
	 * 		
	 * @param x coordinate
	 * @param y coordinate
	 * @return the first actor found at the given coordinates
	 */
	public Actor getActorAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x,y)){
				return(a);
			}
		}
		return null;
	}
	

	/**
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @return true if the coordinates are within the bounds of the level
	 */
	public boolean inBounds(int x, int y){
		return(x >= 0 && x < MAX_X && y >= 0 && y < MAX_Y);
	}
	
	public Object clone() throws CloneNotSupportedException{
		LevelData clone = (LevelData)super.clone();
		clone.waitingZombiesList = new ArrayList<Actor>();
		clone.actorList = new ArrayList<Actor>();
		for(Actor a: waitingZombiesList){
			clone.waitingZombiesList.add((Actor) a.clone());
		}
		for(Actor a: actorList){
			clone.actorList.add((Actor) a.clone());
		}
		clone.level = this.level;
		return clone;
	}

	public int getLevel() {
		return level;
	}
}