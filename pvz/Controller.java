package pvz;

import java.util.Scanner;

public class Controller {
	private static String plantChosen;
	private static int row;
	private static int col;
	private static Scanner scanInput;
	private static Scanner readInput;
	private static Model gameModel;
	
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
	
	
	public static String setStringPlant(){
		System.out.println("What plant would you like to buy? \n 1: Sunflower\n 2: Shooter\n Anything else cancels.");
		readInput=new Scanner(System.in);
		scanInput=new Scanner(readInput.nextLine());
		if(scanInput.hasNext()){
			try{
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
			catch(Exception e){
				System.out.println("Invalid command: No integer value entered.");
				return null;
			}
		}
		return null;
	}

	public static void main(String arg[]){
		gameModel = new Model(1);
		int gameState = 0;
		String newPlant = "";
		Tile tempTile = null;
		gameModel.update();
		while(gameState == 0){
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
		}
		if(gameState == 1){
			System.out.println("You won. You killed all the zombies.");
		}
		else if(gameState == -1){
			System.out.println("You lost. The zombies ate your brains.");
		}
		
	}

}

