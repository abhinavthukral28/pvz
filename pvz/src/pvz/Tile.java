package pvz;

import java.util.LinkedList;


public class Tile{

	//private LinkedList tile;
	private int x;
	private int y;
	private String tileSprite;
	private Actor currActor;
	private Tile tile;
	
	/**
	 * 
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param actor the actor to be assigned to the tile
	 */
	
	public Tile(int x, int y, Actor actor){
		
		//check for the range of X and Y
		if (x > 9 || x < 1 || y < 1 || y > 4){
			System.out.println("Enter valid x, y values");
			return;
		}
		
		tile = new Tile(x, y, actor);
		//make a new tile
	}
	
	//return true if empty, false if occupied
	public boolean isOccupied(){
		
		//check 
		return tile.currActor == null;
	}
	/**
	 * 
	 * @return the Actor on this tile
	 */
	public Actor getOccupant(){
		
		return currActor;
	}
	
	/**
	 * @param actor is Actor to be assigned to the tile
	 */
	public void setOccupant(Actor actor){
		
		currActor = actor;
		this.tileSprite = currActor.getSprite(); 
		
	}
	public String toString(){
		
		
		
	}
}
