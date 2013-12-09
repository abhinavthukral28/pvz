package view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;

public class ZombiePanel {
	private static final String zombie="images/damagedExplosiveZombie.png";
	private JPanel zombiePanel;
	private JButton[] zombies;

	public ZombiePanel() {
		zombies = new JButton[5];
		zombiePanel = (new JPanel());
		
		for (int i=0; i<5; i++){	
			zombies[i] = new JButton();
			zombies[i].setIcon(new ImageIcon(zombie));
			getZombiePanel().add(zombies[i]);
		}
		getZombiePanel().setLayout(new GridLayout(0,1));
	}

	public JPanel getZombiePanel() {
		return zombiePanel;
	}
	
	//adds actionlistener to zombie list
	public void addAction(Controller c){
		for (int i=0; i<5; i++){
			zombies[i].addActionListener(c);
		}
	}
	
	public void update(){ //TODO
	}
	
}