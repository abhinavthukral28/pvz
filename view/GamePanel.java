package view;

import java.awt.GridLayout;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Actor;
import model.LevelData;
import model.Model;
import controller.Controller;

/**
 * The GamePanel class contains the button array that the Actors are displayed upon.
 * @author StuartMacdonald
 * @author AlhetiMamoon
 * @author Fady Ibrahim
 */
public class GamePanel {
	private static final int MAX_ROWS = 6;
	private static final int MAX_COLS = 12;
	private JPanel gridPanel;
	private JButton[][] b;

	public GamePanel() {
		b = new JButton[MAX_ROWS][MAX_COLS];
		gridPanel = (new JPanel());
		getGridPanel().setLayout(new GridLayout(MAX_ROWS,MAX_COLS));
		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y] = new JButton();
				b[x][y].setEnabled(false);
				getGridPanel().add(b[x][y]);
			}
		}
	}
	
	public void addAction(Controller c){
		for(int x =0; x<MAX_ROWS; x++){
			for (int y=0; y<MAX_COLS; y++){
				b[x][y].addActionListener(c);
			}
		}
	}
	
	void update(Observable o){
		LevelData gameState = ((Model)o).getCurrLevel();
		int x=0;
		int y=0;
		
		//check to see if the user chose a non-valid or skip button
		if (((Model)o).getChoice()==null){
			this.setGridButtons(false);
		}
		//check to see if the user has chosen a plant
		else if(((Model)o).getChoice() != null){
			this.setGridButtons(true);
		}
		//draw each actor in play
		for(Actor a: gameState.getLivingActors()){
			x = a.getX();
			y = a.getY();	
			if(gameState.inBounds(x, y)){
				b[y][x].setIcon(new ImageIcon(a.getSprite()));
			}
		}
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

	public JPanel getGridPanel() {
		return gridPanel;
	}
	
	public JButton[][] getB(){
		return b;
	}
}