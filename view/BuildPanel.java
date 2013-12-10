package view;

import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;
import controller.Controller;

public class BuildPanel {
	private static final String DEFSPRITE = "images/damagedExplosiveZombie.png";
	private static final String EXPSPRITE = "images/HealthyExplosiveZombie.jpg";
	private static final String POLSPRITE = "images/PoleZombie.jpg";
	private JPanel graveyardPanel;
	private JButton[] zombies;
	private JButton undoButton;

	public BuildPanel() {
		zombies = (new JButton[3]);
		graveyardPanel = (new JPanel());
		getGraveyardPanel().setLayout(new FlowLayout());
		for (int i=0; i<3; i++){
			zombies[i] = new JButton();
			getGraveyardPanel().add(zombies[i]);
		}
		undoButton = new JButton("Undo");
		getGraveyardPanel().add(undoButton);
	}
	
	public void addAction(Controller c){
	    undoButton.addActionListener(c);
		for (int k=0; k<3; k++){
			zombies[k].addActionListener(c);
		}
	}

	public JPanel getGraveyardPanel() {
		return graveyardPanel;
	}
	
	public void update(Observable o){
		if(((Model)o).getCurrLevel().getLevel() == 1){			
			zombies[0].setIcon(new ImageIcon(DEFSPRITE));
			zombies[1].setIcon(new ImageIcon(EXPSPRITE));
			zombies[2].setIcon(new ImageIcon(POLSPRITE));
		}
	}

	public JButton[] getZombies() {
		return zombies;
	}

	public JButton getUndoButton() {
		return undoButton;
	}
}
