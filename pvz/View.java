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
	private static final String zombie="19460096_s.jpg";
	private static final String sunflowerpic="sunflower.jpg";
	private static final String peaShooter="peashooter.png";
	private static final String grass="grass3b.gif";
	private JButton zombies[];
	private JButton plants[];
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
		//Initializing the grid 
		b = new JButton[9][5];
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
		gridPanel.setLayout(new GridLayout(5,9));

		for(int y =0; y<5; y++){
			for (int x=0; x<9; x++){
				b[x][y] = new JButton();
				
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
		if(((Model)o).getOptionChoosen().equals("sunflower"))
		{
		}
		for(Tile tempTile: tempArrayList)
		{
			while(tempTile.getRight() != null)
			{
				if(tempTile.getOccupant() instanceof DefZombie)
				{
					b[x][y].setIcon(new ImageIcon(zombie));
					y++;
				}
				else if(tempTile.getOccupant() instanceof Shooter)
				{
					b[x][y].setIcon(new ImageIcon(peaShooter));
					y++;
				}
				else if(tempTile.getOccupant() instanceof SunFlower)
				{
					b[x][y].setIcon(new ImageIcon(sunflowerpic));
					y++;
				}
			}
			x++;
		}
		money.setText("Sun Power = " + ((Model)o).getSolarPower());
		if(((Model)o).getLevel() == 1)
		{
			plants[0].setIcon(new ImageIcon(sunflowerpic));
			plants[1].setIcon(new ImageIcon(peaShooter));
		}
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
	public static void main(String arg[]){
		
		View newview = new View();
	}
	

}
