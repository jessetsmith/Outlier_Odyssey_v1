
/** Author: Jesse Smith
Assignment by Outlier.org */

import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class GemRun {
    public static boolean hidden = true;
    public static boolean play = true;
    public static boolean isValid = true; 
    public static int playerPosition;
    public static int gemPosition;
    public static String message;  
    public static final int MAZE_SIZE = 20; // uneditable board size measurement
    
    public static void main(String[] args) {
        // Scanner incantation
        Scanner input = new Scanner(System.in);
        
        while(play){
            HandleStart();
             
            System.out.print("WELCOME TO GEM RUN"+"\n"+"\n");
          
            // Ask user for player and gem positions
            // 'gem' is an int -1-19; if -1, randomize position
            // 'player' is an int -1-19; if -1, randomize position
            System.out.print("Enter 0-19 for gem position or -1 to randomize: ");
            int gem = input.nextInt();
            System.out.print("Enter 0-19 for player position or -1 to randomize: ");
            int player = input.nextInt();
            input.nextLine(); // Scanner incantation
            String maze = "";
                      
            //Establish user and gem starting positions
            playerPosition = player == -1 ? (int)(Math.random() * (MAZE_SIZE - 1)) : player;
            gemPosition = gem == -1 ? (int)(Math.random() * (MAZE_SIZE - 1)) : gem;
          //Make sure player position and gem position are different
          if(playerPosition == gemPosition){
              while(playerPosition == gemPosition){
                   playerPosition = (int)((MAZE_SIZE - 1) * Math.random());
                      //System.out.print(playerPosition+"\n");
              }
          }
    	    //Create string for printing maze
    	     for (int i = 0; i < MAZE_SIZE; i++ ) {
    	    	 if(i == playerPosition) {
    	    		 maze += "P";
    	    	 }else {
    	    		 maze += "_";
    	    	 }
    	     }
          //System.out.print(maze.length()+"\n");
          
          //Set up user prompt for movement
          //System.out.print(gemPosition + "\n" + playerPosition + "\n");
          System.out.print("\n");
    	    System.out.print("Find the gem hidden within the maze..."+"\n"+"\n");
          System.out.print(maze +"\n"+"\n");
          //Use while loop to create gameplay cycle without repetitive code
    	    while(hidden){
                                      
              System.out.print("Enter a direction (left or right): "+"\n"+"\n");
              //Read user input
              String direction = input.next().toLowerCase();
              input.nextLine(); // Scanner incantation
            
              //System.out.print(playerPosition+"\n");
              //System.out.print(direction+"\n");
            
              //handle change in player position in handler method for reusability
              maze = HandleMovement(direction);
              HandlePosition();
              isValid = true;
          }
            
            System.out.print("YOU FOUND THE GEM!"+"\n"+"\n");
          
            HandleWin();
            boolean endGame = true;
          
            while(endGame){
                System.out.print("Play Again? Y or N:  ");
                String playAgain = input.next().toLowerCase();
                input.nextLine(); // Scanner incantation
                
                //System.out.print(playAgain+"\n"+"\n");
                
                if(playAgain.equals("n")){
                    endGame = false;
                    play = false;
                    // Scanner incantation finish
                    input.close();
                }else if (playAgain.equals("y")){
                    endGame = false;
                    hidden = true;
                }else{
                  System.out.print("Please input a valid response."+"\n"+"\n");
                }
            }
        } 
    }
  
  //Handle user inputed movement
  public static String HandleMovement(String direction){
    String maze = "";
    //Check user inputed direction
      if(direction.equals("left")&& playerPosition != 0){
        //if user inputs left, and is not on the final index, move the player
          playerPosition--;
        }else if(direction.equals("right") && playerPosition != MAZE_SIZE - 1){
        //if user inputs right, and is not on the final index, move the player
          playerPosition++;
        }else if(!(direction.equals("left") || direction.equals("right"))){
        //If input isn't valid, prompt new input
          System.out.print("\n"+"\n"+"*!PLEASE INPUT A VALID DIRECTION!*"+"\n"+"\n");
          isValid = false; 
        }
    //If player lands on the space with the gem, handle the win
    if(playerPosition == gemPosition){
        HandleGem();
    }
    //System.out.print(playerPosition+"\n");
    
    //Create string for printing maze
	     for (int i = 0; i < MAZE_SIZE; i++ ) {
	    	 if(i == playerPosition) {
           if(!hidden){
             maze += "*";
           }else{
             maze += "P";
           }
	    	 }else {
	    		 maze += "_";
	    	 }
	     }
    System.out.print("\n"+ maze +"\n"+"\n");
    return maze;
  }
  
  //Handle different position scenarios 
   public static void HandlePosition(){
     //If player is on either end of the index, tell them to move the other way
    if((playerPosition == 0 || playerPosition == MAZE_SIZE - 1) 
       && playerPosition != gemPosition){
        message = "*!YOU'VE REACHED THE EDGE OF THE MAP! TRY THE OTHER DIRECTION!*";
        System.out.print(message +"\n"+"\n");
    } else if(isValid && (playerPosition >= gemPosition + 7 || playerPosition <= gemPosition - 7)){
      //If player is seven spaces or more away from the gem, give them some help
        message = "Very cold!";
        System.out.print(message +"\n"+"\n");
    } else if(isValid && (playerPosition == gemPosition + 1 || playerPosition == gemPosition - 1)){
      //If player is close to gem, let them know
        message = "Very warm!";
        System.out.print(message +"\n"+"\n");
    }

  } 
  
  public static void HandleGem(){
      hidden = false;
  }
  
  public static void HandleStart(){
    System.out.print(
    "_________________________________________"+"\n" +
    " _____              _____         " +"\n" +
    "|   __|___ _____   | __  |_ _ ___ "+"\n" +
    "|  |  | -_|     |  |    -| | |   |"+"\n" +
    "|_____|___|_|_|_|  |__|__|___|_|_|"+"\n" +
    "_________________________________________"+"\n"+"\n");
  }
  
  public static void HandleWin(){
    System.out.print(
    "____________________________________________"+"\n" +
    " _____                  _____             " +"\n" +
    "|   __|___ _____ ___   |     |_ _ ___ ___ "+"\n" +
    "|  |  | .'|     | -_|  |  |  | | | -_|  _|"+"\n" +
    "|_____|__,|_|_|_|___|  |_____||_||___|_|"+"\n"+
    "____________________________________________"+"\n"+"\n");
  }
}



   