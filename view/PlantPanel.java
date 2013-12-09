package view;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;
import controller.Controller;

/**
 * The PlantPanel contains all the buttons used by the player.
 * Buttons for placing plants, as well as the skip turn and undo/redo buttons.
 * @author StuartMacdonald
 * @author AlhetiMamoon
 * @author Fady Ibrahim
 *
 */
public class PlantPanel {
	private static final String sunflowerpic="images/sunflower.jpg";
	private static final String peaShooter="images/peashooter.jpg";
	private static final String grass="images/grass3b.gif";
	private static final String walnut = "images/walnut.jpg";
	private static final String potato = "images/potatoMine.jpg";
	private static final String snowShooter = "images/peashooterFreeze.jpg";							
	private JPanel sunFlowerPanel;
	private JButton[] plants;
	private JButton skipTurn;

	public PlantPanel() {
		plants = (new JButton[6]);
		sunFlowerPanel = (new JPanel());
		getSunFlowerPanel().setLayout(new FlowLayout());
		for (int i=0; i<5; i++){
			
			plants[i] = new JButton();
			plants[i].setIcon(new ImageIcon(grass));
			getSunFlowerPanel().add(plants[i]);
		}
		skipTurn = new JButton("Skip Turn");
	//	skipTurn.setEnabled(true);
		getSunFlowerPanel().add(skipTurn);
	}
	
	public void addAction(Controller c){
	    skipTurn.addActionListener(c);
		for (int k=0; k<5; k++){
			getPlants()[k].addActionListener(c);
		}
	}
	
	public JButton getSkipTurn() {
		return skipTurn;
	}

	public JPanel getSunFlowerPanel() {
		return sunFlowerPanel;
	}
	
	public void update(Observable o){
		if(((Model)o).getCurrLevel().getLevel() == 1){			
			getPlants()[0].setIcon(new ImageIcon(sunflowerpic));
			getPlants()[1].setIcon(new ImageIcon(peaShooter));
			getPlants()[2].setIcon(new ImageIcon(snowShooter));
			getPlants()[3].setIcon(new ImageIcon(walnut));
			getPlants()[4].setIcon(new ImageIcon(potato)); 
		}
		skipTurn.setEnabled(true);
	}

	public JButton[] getPlants() {
		return plants;
	}

	
}