package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.Controller;

/**
 * GameMenu is the menu bar containing such functions as making a new game, exiting,
 * opening the level builder, saving and loading games.
 * @author StuartMacdonald
 * @author AlhetiMamoon
 * @author Fady Ibrahim
 */
public class GameMenu {
	private JMenu mainMenu;
	private JMenuBar menuBar;
	private JMenuItem startGame;
	private JMenuItem closeGame;

	private JMenuItem newLevel;
	

    private JMenuItem saveGame;
    private JMenuItem loadGame;
	
    public GameMenu() {

			menuBar= new JMenuBar();
			mainMenu = new JMenu("File");		
			//menuItems
			startGame = new JMenuItem("New");
			closeGame = new JMenuItem("Exit");
			newLevel = new JMenuItem("New Level");
			
			mainMenu.add(newLevel);

			saveGame = new JMenuItem("Save");
			loadGame = new JMenuItem("Load");
			mainMenu.add(startGame);
			mainMenu.add(closeGame);
			mainMenu.add(saveGame);
			mainMenu.add(loadGame);

			menuBar.add(mainMenu);
	}
	
	public void addAction(Controller c){
		startGame.addActionListener(c);
		closeGame.addActionListener(c);
		saveGame.addActionListener(c);
		loadGame.addActionListener(c);
		newLevel.addActionListener(c);
	}
	public JMenuItem getLoadGame(){
		return loadGame;
	}
	public JMenuItem getSaveGame(){
		return saveGame;

		

	}
	public JMenuItem getStartGame(){
		return startGame;
	}
	
	public JMenuItem getCloseGame(){
		return closeGame;
	}
	public JMenuItem getNewLevel(){
		return newLevel;
	}
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
}