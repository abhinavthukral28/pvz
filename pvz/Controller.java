package pvz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		pvz = new Model(level);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == gameInterface.getNewGame()){
			
		}
		else if (e.getSource() == gameInterface.getExitGame()){
			
		}
		else if (e.getSource() == gameInterface.getNewGame()){
			
		}
		for(int row = 0; row < gameInterface.getGridList().length; row++)
		{
			for(int col = 0; col<gameInterface.getGridList()[row].length; col++ )
			{
				if(e.getSource() == gameInterface.getGridList()[row][col] )
				{
					
				}
			}
		}

		
	}
	/*
	public void addModel(Model m){
		
		this.pvz = m;
		
	}*/
	
	public void addView(View v){
		
		this.gameInterface = v;
	}

}
