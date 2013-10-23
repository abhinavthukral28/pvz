package pvz;

import java.util.Scanner;

public class Controller {

    public static void main(String arg[]){
        int gameState = 0;
        //Model gameModel=new Model(1);
        while(gameState == 0){
            System.out.println("What plant would you like to buy? (Type 1 or 2) 1:Sunflower 2:Shooter");
            Scanner readInput=new Scanner(System.in);
            Scanner scanInput=new Scanner(readInput.nextLine());
            if(scanInput.hasNext())
            {
                try
                {
                    int userInput=Integer.parseInt(scanInput.next());

                    if(userInput>2)
                    {
                        System.out.println("Item does not exist.");

                    }
                    else{

                        System.out.println("Where do you want to place the plant. Eg 0 1: max rows 6, max length 12.");

                        readInput=new Scanner(System.in);
                        scanInput=new Scanner(readInput.nextLine());
                        switch (userInput)
                        {
                            case 1:
                                if(scanInput.hasNext())
                                {
                                    int rowInput=Integer.parseInt(scanInput.next());
                                    if(scanInput.hasNext())
                                    {
                                        int colInput=Integer.parseInt(scanInput.next());
                                        //Check if there is a zombie within the tile
                                        //if there is a zombie print to the user that there is a zombie in that location
                                        //place the plant at location (rowinput, colInput) in the tiles

                                    }
                                    else
                                    {
                                        System.out.println("Invaild command: No value for column was entered.");
                                    }
                                }
                                else
                                {
                                    System.out.println("Invaild command: No value for row was entered. ");
                                }
                                break;
                            case 2:
                                if(scanInput.hasNext())
                                {
                                    int rowInput=Integer.parseInt(scanInput.next());
                                    if(scanInput.hasNext())
                                    {
                                        int colInput=Integer.parseInt(scanInput.next());
                                    }
                                    else
                                    {
                                        System.out.println("InvaildCommand: No value for colum was entered");
                                    }
                                }
                                else{
                                    System.out.println("Invaild Command: No value for row was entered");
                                }
                                break;
                        }}}//¸
                        catch(Exception e)
                        {
                            System.out.println("Invaild command: No integer value entered.");
                        }
            }
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

