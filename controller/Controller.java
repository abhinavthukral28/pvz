package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LevelBuilderView;
import view.View;
import model.LevelBuilder;
import model.Model;

/**
 * Class Controller is the design controller 
 * The Class communicates with View and Model classes
 * @author AlhetiMamoon
 * @author Fady Ibrahim
 *
 */


public class Controller implements ActionListener{
	
	//The controller hard wired to the Model and View through
	
	private View gameInterface;
	private Model pvz;
	private int level;
	private LevelBuilderView buildView;
	private LevelBuilder builder;
	
	public Controller(int level){
		this.level = level;
		gameInterface = new View();
		gameInterface.addAction(this);
	}

	/*
	 * This action performed method will handle all the events caused by the view using the MVC style.
	 * @param ActionEven e -The source of the interrupt
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == gameInterface.getNewGame()){
			pvz = new Model(level);
			pvz.addObserver(gameInterface);
			if(gameInterface.isBuilderMode()){
				gameInterface.switchToPlayMode();
			}
			pvz.notifyObservers();
		}
		else if (e.getSource() == gameInterface.getExitGame()){
			gameInterface.dispose();
			System.exit(0);
		}
		else if (e.getSource() == gameInterface.getNewLevel()){
			System.out.println("New Level Selected");
			if(!gameInterface.isBuilderMode()){
				gameInterface.switchToBuildMode();
				if(buildView == null){
					buildView = new LevelBuilderView();
					buildView.addAction(this);
				}
				if(builder == null){
					builder = new LevelBuilder(1);
					builder.addObserver(buildView);
				}
			}
		}
		if(pvz!=null){
			if(e.getSource() == gameInterface.getSkipTurn())
			{
				pvz.setChoice(null);
				try {
					pvz.update();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			}
			else if(e.getSource() == gameInterface.getUndo()){				//TODO test!
				pvz.undo();
			}
			else if(e.getSource() == gameInterface.getRedo()){
				pvz.redo();
			}
			else{
				if(builder != null && buildView != null){
					for(int zombInd=0; zombInd<buildView.getZombies().length; zombInd++){
						if(e.getSource() == buildView.getZombies()[zombInd] ){
							switch(zombInd){
								case 0:
									builder.addZombie("defzombie");
									break;
								case 1:
									builder.addZombie("explosivezombie");
									break;
								case 2:
									builder.addZombie("polezombie");
									break;
								default:
									break;
							}
						}
					}
				
					if(e.getSource() == buildView.getUndo()){
						builder.removeZombie();
					}
				}
				
				for(int plantInd=0; plantInd<gameInterface.getPlantsList().length;plantInd++){
					if(e.getSource() == gameInterface.getPlantsList()[plantInd] ){
						switch(plantInd){
							case 0:
								pvz.setChoice("sunflower");
								break;
							case 1:
								pvz.setChoice("shooter");
								break;
							case 2:
								pvz.setChoice("snowshooter");
								break;
							case 3:
								pvz.setChoice("walnut");
								break;
							case 4:
								pvz.setChoice("potato");
								break;
							default:
								pvz.setChoice(null);
								break;
						}
					}
				}
				
				for(int row = 0; row < gameInterface.getGridList().length; row++){
					for(int col = 0; col<gameInterface.getGridList()[row].length; col++ ){
						if(e.getSource() == gameInterface.getGridList()[row][col] )	{
							if(pvz.getChoice()!=null)	{
								if(pvz.placePlant(col, row, pvz.getChoice())){
									try {
										pvz.update();
									} catch (CloneNotSupportedException e1) {
										e1.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
        if(pvz.state() == 1){
            System.out.println("You won. You killed all the zombies.");
            //TODO advance level here
            System.exit(0);
        }
        else if(pvz.state() == -1){
            System.out.println("You lost. The zombies ate your brains.");
            System.exit(0);
        }
    
	}
		
	}
}
