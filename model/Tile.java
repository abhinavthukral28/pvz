package model;

public class Tile{

	private String tileSprite;
	private Actor currActor;
	private Tile rightTile;
	private Tile leftTile;
	
	/**
	 * The basic element of the game grid which can contain a zombie or a plant
	 * @author AlhetiMamoon
	 * @param leftTile is the previous tile 
	 * @param rightTile	is the next tile 
	 * @param actor is the actor assigned to the tile 
	 */
	
	public Tile(){
		rightTile = null;
		leftTile = null;
		currActor = null;
		tileSprite = ".";
	}
	
	public Tile(Tile previous, Tile next, Actor actor){
		//make a new tile
		this.rightTile = next;
		this.leftTile = previous;
		this.currActor = actor;
		tileSprite = ".";
	}
	
	/**
	 * @return True if the tile has an Occupant, false otherwise
	 */
	public boolean isOccupied(){
		return (currActor != null);
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
	}
	
	public String toString(){
		if(isOccupied()){
			return currActor.toString();
		}
		else{
			return tileSprite;
		}
		
	}
	
	/**
	 * @return rightTile
	 */
	public Tile getRight(){
		
		return rightTile;
	}
	
	/**
	 * @return leftTile
	 */
	public Tile getLeft(){
		return leftTile;
	}
	
	/**
	 * @param rightTile 
	 */
	public void setRight(Tile next){
		this.rightTile = next;
	}

	/**
	 * @param previousTile
	 */
	public void setLeft(Tile previous) {
		this.leftTile = previous;
	}
}
