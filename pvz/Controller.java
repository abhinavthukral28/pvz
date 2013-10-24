package pvz;

import java.util.Scanner;

public class Controller {
	private static String plantChosen;
	private static int row;
	private static int col;
	private static Scanner scanInput;
	private static Scanner readInput;
	private static Model gameModel;
	
<<<<<<< HEAD
	public Tile setTile(String plantName)
	{
		int rowInput;
		int colInput;
		Tile tempTile;
		gameModel=new Model(1);
		readInput=new Scanner(System.in);
		scanInput=new Scanner(readInput.nextLine());
		System.out.println("Where do you want to place the plant. Example: 0 1,4 5 (Max rows up to 6, max column up to 12).");
		if(scanInput.hasNext())
		{
			try
			{
				rowInput=Integer.parseInt(scanInput.next());
			if(scanInput.hasNext())
			{
				colInput=Integer.parseInt(scanInput.next());
				tempTile=gameModel.getTile(rowInput, colInput);
				gameModel.placePlant(tempTile, plantName);
				
			}
			else
			{
				System.out.println("Invaild command: No value for column was entered.");
			}
=======
	public static Tile getTile(){
		System.out.println("Where do you want to place the plant. Example: 0 1,4 5 (Max rows up to 6, max column up to 12).");
		//gameModel=new Model(1);
		readInput=new Scanner(System.in);
		scanInput=new Scanner(readInput.nextLine());
		
		if(scanInput.hasNext()){
			try{
				row=Integer.parseInt(scanInput.next());
				if(scanInput.hasNext()){
					col=Integer.parseInt(scanInput.next());
					return gameModel.getTile(col, row);
				}
				else{
					System.out.println("Invalid command: No value for column was entered.");
				}
>>>>>>> stuart's-branch
			}
			catch(Exception e){
				System.out.println("Invalid value was entered.");
			}
		
		}
		else{
			System.out.println("Invaild command: No value for row was entered. ");
		}
		return null;
	}
	
<<<<<<< HEAD
	public boolean setStringPlant()
	{
		  System.out.println("What plant would you like to buy? (Type 1 or 2) 1:Sunflower 2:Shooter");
		     readInput=new Scanner(System.in);
			 scanInput=new Scanner(readInput.nextLine());
			if(scanInput.hasNext())
			{
				try
				{
=======
	
	public static String setStringPlant(){
		System.out.println("What plant would you like to buy? \n 1: Sunflower\n 2: Shooter\n Anything else cancels.");
		readInput=new Scanner(System.in);
		scanInput=new Scanner(readInput.nextLine());
		if(scanInput.hasNext()){
			try{
>>>>>>> stuart's-branch
				int userInput=Integer.parseInt(scanInput.next());

				if(userInput<0 && userInput>2){
					System.out.println("Item does not exist.");
					return null;
				}
				else{
					switch (userInput){
					case 1:
						plantChosen="sunflower";
						break;
					case 2:
						plantChosen="shooter";
						break;
					default:
						plantChosen="";
						break;
				    }
					return plantChosen;
				}
			}
<<<<<<< HEAD
			return false;
	      }

	public static void main(String arg[])
	{
=======
			catch(Exception e){
				System.out.println("Invalid command: No integer value entered.");
				return null;
			}
		}
		return null;
	}

	public static void main(String arg[]){
		gameModel = new Model(1);
>>>>>>> stuart's-branch
		int gameState = 0;
		String newPlant = "";
		Tile tempTile = null;
		gameModel.update();
		while(gameState == 0){
<<<<<<< HEAD


		/*//get player input
			//possibly place a plant
			update();
			gameState = winState();
=======
			newPlant = setStringPlant();
			if(newPlant != ""){
				tempTile = getTile();
				if(tempTile != null){
					gameModel.placePlant(tempTile, newPlant);
				}
			}
			//gameModel.printGrid();
			gameModel.update();
			gameState = gameModel.state();
>>>>>>> stuart's-branch
		}
		if(gameState == 1){
			System.out.println("You won. You killed all the zombies.");
		}
		else if(gameState == -1){
			System.out.println("You lost. The zombies ate your brains.");
		}
		
	}

}

