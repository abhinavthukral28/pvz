package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import controller.Controller;

public class LevelBuilderView extends JFrame implements Observer{
	private static final int WINDOW_WIDTH = 1024;
	private static final int WINDOW_HEIGHT = 768;
	
	
	private  JPanel mainPanel;
	private  JPanel statusPanel;

	private ZombieSelectPanel buildPanel;
	private BuildGamePanel workingView;

	private JFrame frame;
	
	private boolean builderMode;			//maybe pointless?
	
	public LevelBuilderView(){
	
		//Initializing the panels
		mainPanel = new JPanel();		
		statusPanel = new JPanel();
		buildPanel = new ZombieSelectPanel();
		workingView = new BuildGamePanel();
		
		mainPanel.setLayout(new BorderLayout(40,5));
		statusPanel.setLayout(new FlowLayout());
		
		//adding panels to the main pane
		mainPanel.add(statusPanel, BorderLayout.NORTH);
		frame = new JFrame("PVZ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
		frame.setContentPane(mainPanel);
		frame.setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		workingView.update(o, arg);
		
	}
	
	public void addAction(Controller c){
		buildPanel.addAction(c);
	}
	
	public JButton[] getZombies() {
		return buildPanel.getZombies();
	}
	
	public JButton getUndo(){
		return buildPanel.getUndoButton();
	}
}
