package model;

import java.util.ArrayList;

/**
 * Contains the data associated with the current level.
 * The Actors, the oncoming zombies, and the grid.
 * @author StuartMacdonald
 *
 */

public class LevelData implements Cloneable{
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	
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

	public ArrayList<Actor> getActorList() {
		return actorList;
	}

	public ArrayList<Actor> getWaitingZombiesList() {
		return waitingZombiesList;
	}

	public boolean actorAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x, y)){
				return true;
			}
		}
		return false;
	}
	
	public boolean zombieAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x, y)){
				if(!a.isFriendly()){
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
				if(a.isFriendly()){
					return true;
				}
			}
		}
		return false;
	}
	
	public ArrayList<Actor> getActorsAt(int x, int y){
		ArrayList<Actor> returnList = new ArrayList<Actor>();
		for(Actor a: actorList){
			if(a.isAt(x,y)){
				returnList.add(a);
			}
		}
		return returnList;
	}
	
	public Actor getActorAt(int x, int y){
		for(Actor a: actorList){
			if(a.isAt(x,y)){
				return(a);
			}
		}
		return null;
	}
	
	//TODO call this a lot
	public boolean inBounds(int x, int y){
		return(x >= 0 && x < MAX_COLS && y >= 0 && y < MAX_ROWS);
	}
	
	public Object clone() throws CloneNotSupportedException{
		LevelData clone = (LevelData)super.clone();
		clone.waitingZombiesList = (ArrayList<Actor>) this.waitingZombiesList.clone();
		clone.actorList = (ArrayList<Actor>) this.actorList.clone();
		clone.level = this.level;
		return clone;
	}
}