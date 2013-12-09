package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMenu {
	public JMenu mainMenu;
	public JMenuBar menuBar;
	public JMenuItem startGame;
	public JMenuItem closeGame;
	//TODO move more stuff from view to here?
	public GameMenu() {
		//adding the menu to the frame
			//Creating a menu bar 
			menuBar= new JMenuBar();
			//Creating menus
					
			mainMenu = new JMenu("File");
					
			//Items
			startGame = new JMenuItem("New");
			closeGame = new JMenuItem("Exit");
			//add the menuItems to the mainMenu
			mainMenu.add(startGame);
			mainMenu.add(closeGame);
			//add the main menu to the menubar
			menuBar.add(mainMenu);
	}
}