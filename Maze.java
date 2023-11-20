
/** Author: Jesse Smith
Assignment by Outlier.org */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Maze {
    //Initialize Map for Collection of Hazards
    public static Map<Integer,String> Hazards = new HashMap<>();
    private static void setHazards(){
      Hazards.put(0,"Wolf");
      Hazards.put(1,"Boar");
      Hazards.put(2,"Elk");
      Hazards.put(3,"Hare");
      Hazards.put(4,"Hare");
    }
    //Initialize Map for collection of the health effects of a hazard
    public static Map<String,Integer> HealthEffects = new HashMap<>();
    private static void setEffects(){
        HealthEffects.put("Wolf",(-20));
        HealthEffects.put("Boar",(-10));
        HealthEffects.put("Elk",(-5));
        HealthEffects.put("Hare",10);
        HealthEffects.put("GEM",100);
      }

    public static void main(String[] args) {
      //call methods for adding values to maps
        setHazards();
        setEffects();
        // Uncomment to test the init() method
         //Tests.testInit();

        // Uncomment to test the validMove() method
         //Tests.testValidMove();

         runGame(); // Run the game!
    }

    /**
     * Initializes a 2D maze game board.
     * 
     * Places the player in the upper left corner;
     * randomly places one gem in the maze;
     * and randomly assigns remaining squares to:
     * "Wolf", "Boar", "Elk", or "Hare".
     * 
     * @param board an empty 2D String array
     */
    public static void init(String[][] board) {
      //Places hazards in randomized positions
        for(int i = 0; i < board.length; ++i){
          for(int j = 0; j < board[0].length; ++j){
            //Generates a random number between 0 and 4
            int r = (int)((4) * Math.random());
            //Gets a hazard from the collection that corresponds to the random number
            String h = Hazards.get(r);
            //Assigns the randomized hazard value to the space on the board
            board[i][j] = h;
          }
          System.out.println();
        }
        board[0][0] = "     "; // reserve player start
        // Place gem in randomized position
        int randomI, randomJ;
        do {
            randomI = (int) ((board.length) * Math.random());
            randomJ = (int) ((board[0].length) * Math.random());

            board[randomI][randomJ] = "GEM";
        } while ((randomI == 0 && randomJ == 0) && (randomI == 4 && randomJ == 4));
    }

    /**
     * Prints a 2D maze game board.
     * 
     * @param board  An initialized 2D String array
     * @param player The current player position
     */
    public static void drawBoard(String[][] board, int[] player) {

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                System.out.print("|");
                if (i == player[0] && j == player[1]) {
                    System.out.print("  P  ");
                } else if (board[i][j].equals("X")) {
                    System.out.print("  X  ");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println("|");
        }
    }

    /**
     * Assesses the validity of a player's requested
     * move and updates nextCell with values for the next move.
     * 
     * @param move     a direction: 'u', 'd', 'l', or 'r'
     * @param board    an initialized 2D String game board array
     * @param player   the current player position
     * @param nextCell values for the player's next move;
     *                 {-1, -1} if move is invalid
     */
    public static void validMove(char move, String[][] board, int[] player, int[] nextCell) {
      if(move == 'u' || move == 'd' || move == 'l' || move == 'r'){
          switch(move){
            case 'u':
               if(player[0] == 0){
                  nextCell[0] = -1;
                  nextCell[1] = -1;
                }else{
                  nextCell[0] = player[0] - 1;
                  nextCell[1] = player[1];
                }
              break;
            case 'd':
              if(player[0] == 4){
                  nextCell[0] = -1;
                  nextCell[1] = -1;
                }else{
                  nextCell[0] = player[0] + 1;
                  nextCell[1] = player[1];
                }
              break;
            case 'l':
              if(player[1] == 0){
                  nextCell[0] = -1;
                  nextCell[1] = -1;
                }else{
                  nextCell[0] = player[0];
                  nextCell[1] = player[1] - 1;
                }
              break;
            case 'r':
              if(player[1] == 4){
                  nextCell[0] = -1;
                  nextCell[1] = -1;
                }else{
                  nextCell[0] = player[0];
                  nextCell[1] = player[1] + 1;
                }
              break;
          }
      } else{
          nextCell[0] = -1;
          nextCell[1] = -1;
      }
    }

    /**
     * Moves a player through the game board and
     * updates board array and health amount accordingly.
     * 
     * @param nextCell directions for player movement
     * @param board    an initialized 2D String game board array
     * @param player   the current player position
     * @return the change to the player's health
     */
    public static int makeMove(int[] nextCell, String[][] board, int[] player) {
        int effect = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (i == player[0] && j == player[1]) {
                    board[i][j] = "X";
                } else if(board[nextCell[0]][nextCell[1]].equals("X")){
                  //if board value is "X", no health effect takes place, 
                  //and we can just update player location
                    board[player[0]][player[1]] = "X";
                    player[0] = nextCell[0];
                    player[1] = nextCell[1];  
                }else if (i == nextCell[0] && j == nextCell[1]) {
                  //Gets the hazard value from the board
                    String hazard = board[i][j];
                  //Gets the health effect of the given hazard value
                    effect = HealthEffects.get(hazard);
                  //Change the player position values
                    player[0] = nextCell[0];
                    player[1] = nextCell[1];  
                } 
            }
        }
        return effect;
    }

    /** 
     * Runs the game from beginning to end using
     * all methods in the class.
     * 
     * This has been completed for you!
     */
    public static void runGame() {
        HandleStart();
        // Start Scanner incantation
        Scanner input = new Scanner(System.in);

        // Declare 2D 5x5 board array
        String[][] board = new String[5][5];

        int[] player = { 0, 0 }; // Initialize player position
        int health = 50; // Initialize health

        // Initialize nextCell to default to invalid move
        int[] nextCell = { -1, -1 };

        init(board); // Initialize board

        // Run game while player has health & has not reached finishing square
        while ((health != 0) && (!(player[0] == 4 && player[1] == 4))) {
            char move;
         
            // Print current game state
            drawBoard(board, player);
            System.out.println("\nHealth: " + health + "\n");

            // Ask for player input & move
            do {
                // Ask for & collect input
                // char move is 'u', 'd', 'l', or 'r'
                System.out.println("Enter a direction you want to move!");
                System.out.print("Up, down, left, or right? ");
                move = input.nextLine().toLowerCase().charAt(0);

                // Assess move validity
                validMove(move, board, player, nextCell);
                if (nextCell[0] == -1)
                    System.out.println("Invalid move! Try again.");
                System.out.println();

            } while (nextCell[0] == -1); // while move is invalid

            System.out.print("Your chosen cell has a: ");
            System.out.println(board[nextCell[0]][nextCell[1]]);
            System.out.print("Would you like to move there? Yes or no: ");
            char confirm = input.nextLine().toLowerCase().charAt(0);
            System.out.println();
            
            if (confirm == 'y') { // If player wants to move
                int effect = makeMove(nextCell, board, player);
                if (effect == 100) {
                    health *= 100;
                } else {
                    health += effect;
                }
                if (health < 0)
                    health = 0;
              
            }
            // Reset nextCell for next move
            nextCell[0] = -1;
            nextCell[1] = -1;

        }

        // Exit game
        if ((player[0] == 4 && player[1] == 4)) {
            drawBoard(board, player);
            System.out.println();
            System.out.println("You got to the end! You win!");
            HandleEnd();
        } else {
            System.out.println("Sorry, you have no more health. Game over!");
            HandleEnd();
        }

        input.close();
    }

    public static void HandleStart(){
    System.out.print(
    "_________________________"+"\n" +
    " _____ _____ _____ _____ "+"\n" +
    "|     |  _  |__   |   __|"+"\n" +
    "| | | |     |   __|   __|"+"\n" +
    "|_|_|_|__|__|_____|_____|"+"\n" +
    "_________________________");
  }

    public static void HandleEnd(){
    System.out.print(
    "____________________________________________"+"\n" +
    " _____                  _____             " +"\n" +
    "|   __|___ _____ ___   |     |_ _ ___ ___ "+"\n" +
    "|  |  | .'|     | -_|  |  |  | | | -_|  _|"+"\n" +
    "|_____|__,|_|_|_|___|  |_____||_||___|_|"+"\n"+
    "____________________________________________"+"\n"+"\n");
  }

}
