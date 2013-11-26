package model;

import java.util.ArrayList;
import java.util.Stack;

public class StateSaver {
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	private static Stack<ArrayList<Tile>> history;
	private static Stack<ArrayList<Tile>> future;
	
	public void saveState(ArrayList<Tile> present){
		ArrayList<Tile> tempList = new ArrayList<Tile>(present);
		//tempList = present;
		printGrid(tempList);
		 history.push(tempList);
		 //future.clear();
	}
	
	public ArrayList<Tile> undo(){
		ArrayList<Tile> templist = history.pop();
		printGrid(templist);
		future.push(templist);
		return  templist;
	}
	
	public ArrayList<Tile> redo(){
		ArrayList<Tile> templist = future.pop();
		printGrid(templist);
		history.push(templist);
		return templist;
	}
	
	public StateSaver(){
		history = new Stack<ArrayList<Tile>>();
		future = new Stack<ArrayList<Tile>>();
	}
	
	public void printGrid(ArrayList<Tile> gameGrid){
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
}
