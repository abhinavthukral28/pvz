package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Actor;
import model.DefZombie;
import model.LevelData;
import model.Model;
import model.Shooter;
import model.SunFlower;


import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import controller.Controller;

/**
 * 
 * @author AlhetiMamoon
 * @author Fady Ibrahim
 * @author Stuart Macdonald
 * 
 * Class View is responsible receiving updates of the interface from the Model and projecting it to the user 
 */


public class View extends JFrame implements Observer {
	//constants
	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 12;
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	
	private  JPanel mainPanel;
	private  JPanel statusPanel;
	private ZombiePanel zombiesPanel;
	private PlantPanel sunFlowerPanel;

	private JLabel money;
	
	
	private GameMenu mainMenu;
	private GamePanel gridPanel;

	private JFrame frame;
	public View(){
		money =  new JLabel("Sun Power = 0");
		
		//Initializing the panels
		mainPanel = new JPanel();		
		sunFlowerPanel = new PlantPanel();
		zombiesPanel = new ZombiePanel();
		gridPanel = new GamePanel();		
		statusPanel = new JPanel();
		statusPanel.add(money);
		
		mainPanel.setLayout(new BorderLayout(40,5));
		statusPanel.setLayout(new FlowLayout());
		
		//adding panels to the main pane
		mainPanel.add(gridPanel.getGridPanel(), BorderLayout.CENTER);
		mainPanel.add(sunFlowerPanel.getSunFlowerPanel(), BorderLayout.SOUTH);
		mainPanel.add(zombiesPanel.getZombiePanel(), BorderLayout.EAST);
		mainPanel.add(statusPanel, BorderLayout.NORTH);

		mainMenu = new GameMenu();
		
		frame = new JFrame("PVZ");
		frame.setJMenuBar(mainMenu.menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		
	}
	/**
	 * This method updates the View with any changes that occurred in the Model.
	 * It will update the game grid and the money.
	 * @param o, arg -o is the object that this class instance is observing. 
	 * -arg is any arguments passed in
	 */
	@Override
	public void update(Observable o, Object arg) {		
		//update the sun money
		money.setText("Sun Power = " + ((Model)o).getSolarPower());
		//initialize the first level view
		sunFlowerPanel.update(o);
		gridPanel.update(o);
	}
	
	/**
	 * Adds the actionlistener controller c to the GUI components.
	 * @param c -c is the controller object that is assigned to listen to this object instances GUI components.
	 */
	public void addAction(Controller c){
		//adds actionlistener to the menu items
		mainMenu.startGame.addActionListener(c);
	    mainMenu.closeGame.addActionListener(c);
	   
	    //adds actionlistener to the game grid
		gridPanel.addAction(c);		
		zombiesPanel.addAction(c);
		sunFlowerPanel.addAction(c);
	}
	
	/**
	 * Return skipTurn button.
	 * @return JButton - returns a button
	 */
	public JButton getSkipTurn()
	{
		return sunFlowerPanel.getSkipTurn();
	}
	/**
	 * Return startGame JMenuItem.
	 * @return JMenuItem -returns a JMenuItem
	 */
	public JMenuItem getNewGame()
	{
		return mainMenu.startGame;
	}
	/**
	 * Return closeGame JMenuItem.
	 * @return JMenuItem -returns a JMenuItem
	 */
	public JMenuItem getExitGame()
	{
		return mainMenu.closeGame;
	}
	/**
	 * Returns the game grid.
	 * @return JButton[][] -Returns a JButton 
	 */
	public JButton[][] getGridList()
	{
		return gridPanel.getB();
	}
	/**
	 *  Returns the plants available to choose from.
	 * @return plants -returns plant list
	 */
	public JButton[] getPlantsList()
	{
		
		return sunFlowerPanel.getPlants();
	}

}

