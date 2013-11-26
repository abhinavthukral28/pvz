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

import model.DefZombie;
import model.Model;
import model.Shooter;
import model.SunFlower;
import model.Tile;

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
	
	//Game panels 
	private static JPanel gridPanel;
	private static JPanel mainPanel;
	private static JPanel sunFlowerPanel;
	private static JPanel zombiePanel;
	private static JPanel statusPanel;
	private static final String zombie="images/damagedExplosiveZombie.png";
	private static final String sunflowerpic="images/sunflower.jpg";
	private static final String peaShooter="images/peashooter.jpg";
	private static final String grass="images/grass3b.gif";
	private static final String walnut = "images/walnut.jpg";
	private static final String potato = "images/potatoMine.jpg";
	private static final String snowShooter = "images/peashooterFreeze.jpg";							//TODO
	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 12;
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	private JButton zombies[];
	private JButton plants[];
	private JButton skipTurn;
	private JMenu mainMenu;
	private JMenuBar menuBar;
	private JMenuItem startGame;
	private JMenuItem closeGame;
	private JLabel money;
	
	
	//grid buttons
	private static JButton b[][];
	
	private JFrame frame;
	public View(){
		
		zombies = new JButton[5];
		plants = new JButton[5];
		//house.setIcon(arg0);
		skipTurn = new JButton("Skip Turn");
		skipTurn.setEnabled(false);
		//Initializing the grid 
		b = new JButton[MAX_ROWS][MAX_COLS];
		money =  new JLabel("Sun Power = 0");
		
		
		//Initializing the panels
		mainPanel = new JPanel();
		gridPanel = new JPanel();
		
		
		sunFlowerPanel = new JPanel();
		zombiePanel = new JPanel();
		statusPanel = new JPanel();
		statusPanel.add(money);
		//setting the layout of the game grid
		mainPanel.setLayout(new BorderLayout(40,5));
		gridPanel.setLayout(new GridLayout(MAX_ROWS,MAX_COLS));

		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y] = new JButton();
				b[x][y].setEnabled(false);
				gridPanel.add(b[x][y]);
			}
		}
		for (int i=0; i<5; i++){
			
			zombies[i] = new JButton();
			zombies[i].setIcon(new ImageIcon(zombie));
			zombiePanel.add(zombies[i]);
		}
		zombiePanel.setLayout(new GridLayout(0,1));
		sunFlowerPanel.setLayout(new FlowLayout());
		for (int i=0; i<5; i++){
			
			plants[i] = new JButton();
			plants[i].setIcon(new ImageIcon(grass));
			sunFlowerPanel.add(plants[i]);
		}
		sunFlowerPanel.add(skipTurn);
		statusPanel.setLayout(new FlowLayout());
		
		//adding panels to the main pane
		mainPanel.add(gridPanel, BorderLayout.CENTER);
		mainPanel.add(sunFlowerPanel, BorderLayout.SOUTH);
		mainPanel.add(zombiePanel, BorderLayout.EAST);
		mainPanel.add(statusPanel, BorderLayout.NORTH);

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
				
		frame = new JFrame("PVZ");
		frame.setJMenuBar(menuBar);
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
		
		ArrayList<Tile> tempArrayList = ((Model)o).getGameGrid();
		int x=0;
		int y=0;
		Tile tCol;
		//check to see if the user chose a non-valid or skip button
		if (((Model)o).getChoice()==null){
			this.setGridButtons(false);
		}
		//check to see if the user has chosen a plant
		else if(((Model)o).getChoice() != null){
			this.setGridButtons(true);
		}
		//go through the game grid to update the position of zombies and the static position of plants
		for(Tile tRow: tempArrayList){
			tCol=tRow;
			y=0;
			do{
				if(y>MAX_COLS || x> MAX_ROWS){
					break;
				}
				if(tCol.getOccupant() != null){										
					b[x][y].setIcon(new ImageIcon(tCol.getOccupant().getSprite()));
				}
				y++;
				tCol=tCol.getRight();
			}while(tCol.getRight() != null);
			x++;
		}
		//update the sun money
		money.setText("Sun Power = " + ((Model)o).getSolarPower());
		//initialize the first level view
		if(((Model)o).getLevel() == 1){
			plants[0].setIcon(new ImageIcon(sunflowerpic));
			plants[1].setIcon(new ImageIcon(peaShooter));
			plants[2].setIcon(new ImageIcon(snowShooter));
			plants[3].setIcon(new ImageIcon(walnut));
			plants[4].setIcon(new ImageIcon(potato)); 
			skipTurn.setEnabled(true);
		}
	}
	/**
	 * Adds the actionlistener controller c to the GUI components.
	 * @param c -c is the controller object that is assigned to listen to this object instances GUI components.
	 */
	public void addAction(Controller c){
		//adds actionlistener to the menu items
		startGame.addActionListener(c);
	    closeGame.addActionListener(c);
	    //adds actionlistener to the skipTurn button
	    skipTurn.addActionListener(c);
	    //adds actionlistener to the game grid
		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y].addActionListener(c);
			}
		}
		//adds actionlistener to zombie list
		for (int i=0; i<5; i++){
			zombies[i].addActionListener(c);
		}
		//adds actionlistener to plants list
		for (int k=0; k<5; k++){
			plants[k].addActionListener(c);
		}
	}
	/**
	 * Return skipTurn button.
	 * @return JButton - returns a button
	 */
	public JButton getSkipTurn()
	{
		return skipTurn;
	}
	/**
	 * Return startGame JMenuItem.
	 * @return JMenuItem -returns a JMenuItem
	 */
	public JMenuItem getNewGame()
	{
		return startGame;
	}
	/**
	 * Return closeGame JMenuItem.
	 * @return JMenuItem -returns a JMenuItem
	 */
	public JMenuItem getExitGame()
	{
		return closeGame;
	}
	/**
	 * Returns the game grid.
	 * @return JButton[][] -Returns a JButton 
	 */
	public JButton[][] getGridList()
	{
		return b;
	}
	/**
	 *  Returns the plants available to choose from.
	 * @return plants -returns plant list
	 */
	public JButton[] getPlantsList()
	{
		
		return plants;
	}
	/**
	 * Sets the buttons enabled according to the given parameter.
	 * Also assigns the image to a null value, this will clear 
	 * the Game grid of the previous positions.
	 * @param bool -accepts a bool
	 */
	public void setGridButtons(boolean bool)
	{
		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y].setEnabled(bool);
				//set the button to nullto avoid 
				//the zombie drawn in areas where it left 
				//becuase of it's movement 
				b[x][y].setIcon(null);
			}
		}
	}
}

