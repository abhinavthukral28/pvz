package pvz;

import java.util.Scanner;

public class Controller {
	private String plantChoosen;
	private int row,col;
	private Scanner scanInput;
	private Scanner readInput;
	private Model gameModel;
	
	public Tile setTile(String plantName)
	{
		System.out.println("Where do you want to place the plant. Example: 0 1,4 5 (Max rows up to 6, max column up to 12).");
		gameModel=new Model(1);
		readInput=new Scanner(System.in);
		scanInput=new Scanner(readInput.nextLine());

		if(scanInput.hasNext())
		{
			try
			{
			int rowInput=Integer.parseInt(scanInput.next());
			if(scanInput.hasNext())
			{
				int colInput=Integer.parseInt(scanInput.next());
				
			}
			else
			{
				System.out.println("Invaild command: No value for column was entered.");
			}
			}
			catch(Exception e)
			{
				System.out.println("Invaild value was entered.");
			}
		
		}
		else
		{
			System.out.println("Invaild command: No value for row was entered. ");
		}
		return null;
	}
	
	
	public Tile getTile(int row, int col)
	{
		return null;
	}
	
	public boolean setStringPlant()
	{
		  System.out.println("What plant would you like to buy? (Type 1 or 2) 1:Sunflower 2:Shooter");
		     readInput=new Scanner(System.in);
			 scanInput=new Scanner(readInput.nextLine());
			if(scanInput.hasNext())
			{
				try
				{
				int userInput=Integer.parseInt(scanInput.next());

				if(userInput<0 && userInput>2)
				{
					System.out.println("Item does not exist.");
					return false;
					
				}
				else{
					switch (userInput)
					{
					case 1:
						plantChoosen="sunflower";
						return true;
					case 2:
						plantChoosen="shooter";
						return true;
					default:
						plantChoosen="None";
						return true;
				    }
				 }
				 }
				catch(Exception e)
				{
					System.out.println("Invalid command: No integer value entered.");
					return false;
				}
				
			}
			return false;
	      }



	public static void main(String arg[])
	{
		int gameState = 0;
		//Model gameModel=new Model(1);
		while(gameState == 0){

		
		/*//get player input
			//possibly place a plant
			update();
			gameState = winState();
		}
		if(gameState == 1){
			//You won! Congraturation
		}
		else if(gameState == -1){
			//You lost. Mission Failed
		}
		return 0;
		
	}
	*/

        }
    }


	
}

