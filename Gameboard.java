public class GameBoard {

    private Cell[][] board;
    private int rows;
    private int cols;
    private int exitCell = -1; // ID of final board cell

    /**
     * Default constructor
     * Creates a 5x5 board and initializes cells
     */

    public GameBoard() {
        this(5, 5);
    }

    /**
     * Parameterized constructor
     * Creates a board of specified size and initializes cells
     * 
     * @param rows  number of rows in board
     * @param cols  number of columns in board
     */

    public GameBoard(int rows, int cols) {

        /* TODO: complete constructor */
            // Initialize 'rows' and 'cols'
            // Initialize 'board' to a new 2D Cell array with 'rows' rows and 'cols' columns
            // Run initBoard(), initCell0(), and initGemCell()

    }

    /**
     * Initializes every board element to a new Cell object
     * and increments the exitCell attribute
     */
    private void initBoard() {

        /* TODO: Initialize the board */
            // Initialize every element to a new Cell object with the no-argument constructor
            // For every new element, increment exitCell

    }

    /**
     * Sets cell 0 as player start
     */
    private void initCell0() {
        board[0][0].setCleared(true);
        board[0][0].setVisited(true);

    }

    /**
     * Places the gem in a random spot on the board
     * that is not the first or last cells
     */
    private void initGemCell() {
        int randI;
        int randJ;

        do {

            randI = (int) ((board.length) * Math.random());
            randJ = (int) ((board[0].length) * Math.random());

            CellItem c = new CellItem("GEM", 5000);
            board[randI][randJ].setCellItem(c);

        } while ((randI == 0 && randJ == 0) || ((randI == (board.length - 1) && randJ == (board[0].length - 1))));

    }

    /**
     * Returns the value of the last cell
     * 
     * @return an int representing the last cell value counting from 0
     */
    public int getExitCell() {
        return this.exitCell;
    }

    /**
     * Returns the 2D board array
     * 
     * @return the 2D array of cells
     */
    public Cell[][] getBoard() {
        return this.board;
    }

    /**
     * Returns the coordinates of a cell position
     * 
     * @return int coordinates in a 1D array
     */
    public int[] getCoord(int position) {

        int[] coord = { -1, -1 };
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (position == this.board[i][j].getID()) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }

        return coord;

    }

    /**
     * Displays the current game board
     * 
     * @param player current Player object
     */
    public void displayBoard(Player player) {

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j].getID() == player.getPosition()) {
                    System.out.print("|");
                    System.out.print(" P ");
                } else {
                    System.out.print("|");
                    System.out.print(board[i][j]);
                }

            }
            System.out.println("|");

        }

    }

}
