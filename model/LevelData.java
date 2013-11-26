package model;

import java.util.ArrayList;

/**
 * Contains the data associated with the current level.
 * The Actors, the oncoming zombies, and the grid.
 * @author StuartMacdonald
 *
 */

public class LevelData {
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	
	public ArrayList<Actor> actorList;
	public ArrayList<Actor> waitingZombiesList;
	public ArrayList<Tile> gameGrid;
	public int level;

	public LevelData(int level) {
		this.level = level;
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
				tempTile.setRight(new Tile());			//link on a new tile
				tempTile.getRight().setLeft(tempTile);	//and link it back
			}
		}
		
		for(int x = 0; x < (5 + level); x++){
			waitingZombiesList.add(new DefZombie(level)); 			//add some basic zombies
		}
		for(int x = 0; x < (level); x++){
			waitingZombiesList.add(new PoleZombie(level)); 			//add some pole-vault zombies
		}
		for(int x = 0; x < (level+2); x++){
			waitingZombiesList.add(new ExplosiveZombie(level)); 	//add some exploding zombies
		}
	}
}