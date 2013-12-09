package view;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;
import controller.Controller;

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
		plants = (new JButton[5]);
		sunFlowerPanel = (new JPanel());
		getSunFlowerPanel().setLayout(new FlowLayout());
		for (int i=0; i<5; i++){
			
			getPlants()[i] = new JButton();
			getPlants()[i].setIcon(new ImageIcon(grass));
			getSunFlowerPanel().add(getPlants()[i]);
		}
		skipTurn = new JButton("Skip Turn");
		skipTurn.setEnabled(false);
		getSunFlowerPanel().add(skipTurn);
	}
	
	public void toggleSkipTurn(boolean toggle){
		if(toggle){
			skipTurn.setEnabled(true);
		}
		else{
			
		}
	}
	public void addAction(Controller c){
		 //adds actionlistener to the skipTurn button
	    skipTurn.addActionListener(c);
		//adds actionlistener to plants list
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
		if(((Model)o).getCurrLevel().getLevel() == 1){			//TODO this should go off of seedpackets
			getPlants()[0].setIcon(new ImageIcon(sunflowerpic));
			getPlants()[1].setIcon(new ImageIcon(peaShooter));
			getPlants()[2].setIcon(new ImageIcon(snowShooter));
			getPlants()[3].setIcon(new ImageIcon(walnut));
			getPlants()[4].setIcon(new ImageIcon(potato)); 
		}
		toggleSkipTurn(true);
	}

	public JButton[] getPlants() {
		return plants;
	}

}