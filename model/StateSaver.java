


package model;

import java.util.Stack;
/**
 * The StateSaver class is responsible for tracking 
 * the previous and future sates of the game, for 
 * undo and redo capabilities
 * @author StuartMacdonald
 */
public class StateSaver {
	public static final int MAX_ROWS = 6;
	public static final int MAX_COLS = 12;
	private static Stack<LevelData> pastLevel;
	private static Stack<LevelData> futureLevel;
	private static Stack<PlayerData> pastPlayer;
	private static Stack<PlayerData> futurePlayer;
	
	
	public void saveState(LevelData presentLevel, PlayerData presentPlayer) throws CloneNotSupportedException{
		pastLevel.push((LevelData) presentLevel.clone());
		pastPlayer.push((PlayerData) presentPlayer.clone());
		futureLevel.clear();		//future timelines... are cut off
		futurePlayer.clear();
		//System.out.println("State Saved.\n");
	}
	
	public LevelData undoLevel(){
		LevelData tempLevel = null;
		if(!pastLevel.isEmpty()){
			tempLevel = pastLevel.pop();
			//printGrid(templist);
			futureLevel.push(tempLevel);
		}
		//System.out.println("Undid\n");
		return tempLevel;
	}
	
	public PlayerData undoPlayer(){
		PlayerData tempPlayer = null;
		if(!pastPlayer.isEmpty()){
			tempPlayer = pastPlayer.pop();
			//printGrid(templist);
			futurePlayer.push(tempPlayer);
		}
		return tempPlayer;
		
	}
	
	public PlayerData redoPlayer(){
		PlayerData tempPlayer = null;
		if(!futurePlayer.isEmpty()){
			tempPlayer = futurePlayer.pop();
			//printGrid(templist);
			pastPlayer.push(tempPlayer);
		}
		return tempPlayer;
	}
	
	public LevelData redoLevel(){
		LevelData tempLevel = null;
		if(!futureLevel.isEmpty()){
			tempLevel = futureLevel.pop();
			//printGrid(templist);
			pastLevel.push(tempLevel);
		}
		return tempLevel;
	}
	
	public StateSaver(){
		pastPlayer = new Stack<PlayerData>();
		futurePlayer = new Stack<PlayerData>();
		pastLevel = new Stack<LevelData>();
		futureLevel = new Stack<LevelData>();
	}
	
	boolean canUndo(){
		return(!pastPlayer.isEmpty() && !pastLevel.isEmpty());
	}
	
	boolean canRedo(){
		return(!futurePlayer.isEmpty() && !futureLevel.isEmpty());
	}
	
}
