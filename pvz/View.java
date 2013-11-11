package pvz;

import java.awt.BorderLayout;
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

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 
 * @author AlhetiMamoon
 * @author Fady Ibrahim
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
	private static final String zombie="images/19460096_s.jpg";
	private static final String sunflowerpic="images/sunflower.jpg";
	private static final String peaShooter="images/peashooter.png";
	private static final String grass="images/grass3b.gif";
	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 12;
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
			sunFlowerPanel.add(skipTurn);
		}
		
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
		frame.setSize(300,400);
		
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		ArrayList<Tile> tempArrayList = ((Model)o).getGameGrid();
		int x=0;
		int y=0;
		Tile tCol;
		if (((Model)o).getChoice()==null)
		{
			this.setGridButtons(false);
		}
		else if(((Model)o).getChoice().equals("sunflower") || ((Model)o).getChoice().equals("shooter"))
		{
			this.setGridButtons(true);
		}
		for(Tile tRow: tempArrayList)
		{
			tCol=tRow;
			y=0;
			do{
				if(y>MAX_COLS || x> MAX_ROWS)
				{
					break;
				}
				if(tCol.getOccupant() instanceof DefZombie)
				{
					b[x][y].setIcon(new ImageIcon(zombie));
				}
				else if(tCol.getOccupant() instanceof Shooter)
				{
					b[x][y].setIcon(new ImageIcon(peaShooter));
				}
				else if(tCol.getOccupant() instanceof SunFlower)
				{
					b[x][y].setIcon(new ImageIcon(sunflowerpic));
				}
				y++;
				tCol=tCol.getRight();
			}while(tCol.getRight() != null);
			
			x++;
		}
		money.setText("Sun Power = " + ((Model)o).getSolarPower());
		if(((Model)o).getLevel() == 1)
		{
			plants[0].setIcon(new ImageIcon(sunflowerpic));
			plants[1].setIcon(new ImageIcon(peaShooter));
			skipTurn.setEnabled(true);
		}
	}

	public void addAction(Controller c)
	{

		startGame.addActionListener(c);;
	    closeGame.addActionListener(c);;
	    skipTurn.addActionListener(c);
		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y].addActionListener(c);
			}
		}
		for (int i=0; i<5; i++){
			
			zombies[i].addActionListener(c);
		}

		for (int k=0; k<5; k++){
			
			plants[k].addActionListener(c);
		}
	}
	public JButton getSkipTurn()
	{
		return skipTurn;
	}
	public JMenuItem getNewGame()
	{
		return startGame;
	}
	public JMenuItem getExitGame()
	{
		return closeGame;
	}
	public JButton[][] getGridList()
	{
		return b;
	}
	public JButton[] getPlantsList()
	{
		return plants;
	}
	public void setGridButtons(boolean bool)
	{
		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y].setEnabled(bool);
				b[x][y].setIcon(null);
			}
		}
	}
}

