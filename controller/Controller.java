package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.View;
import model.Model;

/**
 * 
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
			pvz.notifyObservers();
		}
		else if (e.getSource() == gameInterface.getExitGame()){
			gameInterface.dispose();
			System.exit(0);
		}
		if(e.getSource() == gameInterface.getSkipTurn())
		{
			pvz.setChoice(null);
			pvz.update();
		}
		if(pvz!=null){
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
						if(pvz.placePlant(pvz.getTile(col, row), pvz.getChoice())){
							pvz.update();
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
