package model;

import java.util.ArrayList;
import java.util.Stack;

public class StateSaver {
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	private static Stack<Model> history;
	private static Stack<Model> future;
	
	public void saveState(Model present) throws CloneNotSupportedException{
		history.push((Model) present.clone());
		future.clear();
	}
	
	public Model undo(){
		Model tempModel = null;
		if(!history.isEmpty()){
			tempModel = history.pop();
			//printGrid(templist);
			future.push(tempModel);
		}
		return tempModel;
	}
	
	public Model redo(){
		Model tempModel = null;
		if(!future.isEmpty()){
			tempModel = future.pop();
			//printGrid(templist);
			history.push(tempModel);
		}
		return tempModel;
	}
	
	public StateSaver(){
		history = new Stack<Model>();
		future = new Stack<Model>();
	}
	
	
}
