package pvz;

import java.util.LinkedList;


public class Tile{

	//private LinkedList tile;
	
	private String tileSprite;
	private Actor currActor;
	private Tile next;
	private Tile previous;
	
	/**
	 * 
	 * @author AlhetiMamoon
	 * @param previous is the previous tile 
	 * @param next	is the next tile 
	 * @param actor is the actor assigned to the tile 
	 */
	
	public Tile(Tile previous, Tile next, Actor actor){
		
		//make a new tile
		tile = new Tile(previous, next, actor);
		
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
	public Tile getNext(){
		
		return next;
	}
	
	public Tile getPrevious{
		return previous;
	}
	public setNext(Tile next){
		this.next = next;
	}
}
