package pvz;

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
	
	public Tile(){
		next = null;
		previous = null;
		currActor = null;
		tileSprite = ".";
	}
	
	public Tile(Tile previous, Tile next, Actor actor){
		//make a new tile
		this.next = next;
		this.previous = previous;
		this.currActor = actor;
		tileSprite = ".";
		
	}
	
	//return true if empty, false if occupied
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
		this.tileSprite = currActor.getSprite(); 
		
	}
	public String toString(){
		if(isOccupied()){
			return currActor.getSprite();
		}
		else{
			return tileSprite;
		}
		
	}
	
	public Tile getNext(){
		
		return next;
	}
	
	public Tile getPrevious(){
		return previous;
	}
	
	public void setNext(Tile next){
		this.next = next;
	}

	public void setPrevious(Tile previous) {
		this.previous = previous;
	}
}
