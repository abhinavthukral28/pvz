package pvz;

public class Tile{

	private String tileSprite;
	private Actor currActor;
	private Tile rightTile;
	private Tile leftTile;
	
	/**
	 * 
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
	
	public Tile getRight(){
		
		return rightTile;
	}
	
	public Tile getLeft(){
		return leftTile;
	}
	
	public void setRight(Tile next){
		this.rightTile = next;
	}

	public void setLeft(Tile previous) {
		this.leftTile = previous;
	}
}
