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

	public GameMenu() {

			menuBar= new JMenuBar();
			mainMenu = new JMenu("File");		
			//menuItems
			startGame = new JMenuItem("New");
			closeGame = new JMenuItem("Exit");
			mainMenu.add(startGame);
			mainMenu.add(closeGame);
			menuBar.add(mainMenu);
	}
	
	public void addAction(Controller c){
		startGame.addActionListener(c);
		closeGame.addActionListener(c);
	}
	
	public JMenuItem getStartGame(){
		return startGame;
	}
	
	public JMenuItem getCloseGame(){
		return closeGame;
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
}