import java.util.ArrayList;

/**
 * Methods for use to test Maze.java.
 * These methods will print output to catch major
 * errors or gaps in the assignment.
 * 
 * Successful tests to not guarantee full credit.
 */
public class Tests {
    public static void main(String[] args) {
    }

    /** 
     * Method to test the init() method in Maze.java
     */
    public static void testInit() {
        // Declare 2D 5x5 board array
        String[][] board = new String[5][5];
        int[] player = { 0, 0 }; // Initialize player position

        Maze.init(board); // Initialize board

        ArrayList<String> animals = new ArrayList<String>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == null) {
                    System.out.println("Error: not all values have been initialized!");
                } else {
                    animals.add(board[i][j]);
                }
            }
        }

        if (animals.remove("Wolf") == false) {
            System.out.println("No wolves found.");
        } else {
            System.out.println("At least one wolf found!");
        }
        if (animals.remove("Boar") == false) {
            System.out.println("No boars found.");
        } else {
            System.out.println("At least one boar found!");
        }
        if (animals.remove("Elk") == false) {
            System.out.println("No elks found.");
        } else {
            System.out.println("At least one elk found!");
        }
        if (animals.remove("Hare") == false) {
            System.out.println("No hares found.");
        } else {
            System.out.println("At least one hare found!");
        }

        System.out.println("Board printout:");
        Maze.drawBoard(board, player);
    }

    /**
     * Method to test the validMove() method in Maza.java
     */
    public static void testValidMove() {
        // Declare 2D 5x5 board array
        String[][] board = new String[5][5];
        int[] player = { 0, 0 }; // Initialize player position
        int[] playerTwo = {4, 4}; // Second player position

        // Initialize nextCell to default to invalid move
        int[] nextCell = { -1, -1 };

        Maze.init(board); // Initialize board

        // Test board move up
        Maze.validMove('u', board, player, nextCell);
        if (nextCell[0] == -1 && nextCell[1] == -1) {
            System.out.println("Catches player trying to move too far up!");
        } else {
            System.out.println("Up error: nextCell should be {-1, -1} if moving up at top of board");
        }
        refresh(nextCell);
        Maze.validMove('u', board, playerTwo, nextCell);
        if (nextCell[0] == 3 && nextCell[1] == 4) {
            System.out.println("Updates nextCell correctly for valid move up!");
        } else {
            System.out.println("Up error: nextCell is not updated correctly for valid move.");
        }

        refresh(nextCell);
        System.out.println();

        // Test board move left
        Maze.validMove('l', board, player, nextCell);
        if (nextCell[0] == -1 && nextCell[1] == -1) {
            System.out.println("Catches player trying to move too far left!");
        } else {
            System.out.println("Left error: nextCell should be {-1, -1} if moving left at left of board");
        }
        refresh(nextCell);
        Maze.validMove('l', board, playerTwo, nextCell);
        if (nextCell[0] == 4 && nextCell[1] == 3) {
            System.out.println("Updates nextCell correctly for valid move left!");
        } else {
            System.out.println("Left error: nextCell is not updated correctly for valid move.");
        }
        
        refresh(nextCell);
        System.out.println();

        // Test board move down
        Maze.validMove('d', board, playerTwo, nextCell);
        if (nextCell[0] == -1 && nextCell[1] == -1) {
            System.out.println("Catches player trying to move too far down!");
        } else {
            System.out.println("Down error: nextCell should be {-1, -1} if moving down at bottom of board");
        }
        refresh(nextCell);
        Maze.validMove('d', board, player, nextCell);
        if (nextCell[0] == 1 && nextCell[1] == 0) {
            System.out.println("Updates nextCell correctly for valid move down!");
        } else {
            System.out.println("Down error: nextCell is not updated correctly for valid move.");
        }
        
        refresh(nextCell);
        System.out.println();

        // Test board move right
        Maze.validMove('r', board, playerTwo, nextCell);
        if (nextCell[0] == -1 && nextCell[1] == -1) {
            System.out.println("Catches player trying to move too far right!");
        } else {
            System.out.println("Right error: nextCell should be {-1, -1} if moving right at right of board");
        }
        refresh(nextCell);
        Maze.validMove('r', board, player, nextCell);
        if (nextCell[0] == 0 && nextCell[1] == 1) {
            System.out.println("Updates nextCell correctly for valid move right!");
        } else {
            System.out.println("Right error: nextCell is not updated correctly for valid move.");
        }
        
        
    }

    public static void refresh(int[] nextCell) {
        nextCell[0] = -1;
        nextCell[1] = -1;
    }
}