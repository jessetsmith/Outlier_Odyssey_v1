import java.util.Scanner;

public class Game {

    GameBoard board;
    Player player;

    /**
     * Default constructor
     * Initializes 'board' and 'player' to defaults
     */
    public Game() {
        board = new GameBoard();
        player = new Player();
    }

    /**
     * Constructor that sets board to particular size
     * and the player to default
     * 
     * @param row an int representing the rows for the board
     * @param col an int representing the cols for the board
     */
    public Game(int row, int col) {
        board = new GameBoard(row, col);
        player = new Player();
    }

    /**
     * Runs the game from beginning to end
     * 
     * @param input Scanner object to handle user input
     */

    public void runGame(Scanner input) {

        while ((player.getHealth() != 0) && (player.getPosition() != board.getExitCell())) {
            
            Cell nextCell;
            char mov;

            // Print current game state
            board.displayBoard(player);
            System.out.println("\nHealth: " + player.getHealth() + "\n");

            do {
                // Ask for & collect input
                // char move is 'u', 'd', 'l', or 'r'
                System.out.println("Enter a direction you want to move!");
                System.out.print("Up, down, left, or right? ");
                mov = input.nextLine().toLowerCase().charAt(0);
                
                // Assess move validity
                nextCell = validMove(mov);
                if (nextCell == null)
                    System.out.println("Invalid move! Try again");
                System.out.println();

            } while (nextCell == null); // while move is invalid

            makeMove(nextCell, input);

        }

        if (player.getPosition() == board.getExitCell()) { // reached exit
            board.displayBoard(player);
            System.out.println("You got to the end! You win!");
        } else {
            board.displayBoard(player);
            System.out.println("Sorry, you have no more health. Game over!");
        }

    }

    /**
     * Assesses the validity of a player's requested
     * move and returns values for the next move.
     *  
     * @param move  a direction: 'u', 'd', 'l', or 'r'
     * @return   the Cell to move to or null for an invalid move
     */

    private Cell validMove(char move) {

        int[] position = board.getCoord(player.getPosition());
        // Save player position values for easy access
        int y = position[0];
        int x = position[1];
        Cell[][] board = this.board.getBoard();

        if (move == 'u') {
            if (y == 0) { // Cannot move up
                return null;
            } else
                return this.board.getBoard()[y - 1][x];

        } else if (move == 'd') {
            if (y >= board.length - 1) { // Cannot move down
                return null;
            } else
                return this.board.getBoard()[y + 1][x];

        } else if (move == 'l') {
            if (x == 0) { // Cannot move left
                return null;
            } else
                return this.board.getBoard()[y][x - 1];

        } else if (move == 'r') {
            if (x >= board[0].length - 1) { // Cannot move right
                return null;
            } else
                return this.board.getBoard()[y][x + 1];

        }

        return null;
    }

    /**
     * Moves a player through the game board and
     * updates board array and health amount accordingly.
     * 
     * @param nextCell Cell object to move to
     * @param input    Scanner object to handle user input
     */
    private void makeMove(Cell nextCell, Scanner input) {

        nextCell.setCleared(true);

        if (nextCell.getVisited()) {
            System.out.println("This cell has been cleared and has no cost.");
        } else {
            System.out.print("Your chosen cell has a ");
            System.out.print(nextCell.getCellItem().getName());
            System.out.println(" with a cost of " + nextCell.getCellCost());
        }

        System.out.print("Would you like to move there? Yes or no: ");
        char confirm = input.nextLine().toLowerCase().charAt(0);

        if (confirm == 'y') { // If player wants to move
            this.player.setPosition(nextCell.getID());
            player.setHealth(player.getHealth() + nextCell.getCellCost());
            nextCell.setVisited(true);
            if (player.getHealth() < 0) {
                player.setHealth(0);
            }

        }

    }
}
