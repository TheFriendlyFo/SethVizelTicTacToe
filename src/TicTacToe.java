import java.util.Scanner;

public class TicTacToe {
    private final Player[] players;
    private final Board board;

    /**
     * Creates a player with the symbol X and a player with the symbol O
     * and initializes the players instance variable with the two players.
     * <p>
     * Creates a new Board and assigns it to board, then draws game board.
     */
    public TicTacToe(int numPlayers, int boardSize) {
        players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i);
        }
        //players[2] = new Player(2);
        board = new Board(boardSize);

        // draws the board as part of setup
        board.drawBoard();
    }

    /**
     * A loop will run the game until the board is full.
     * Inside the loop, each player will take a turn, starting with p1/
     * If either player's turn ends the game (the takeTurn() method returns true), break out of the loop.
     */
    public void runGame() {
        boolean gameOver = false;

        // repeat until either the board is full (i.e. all spaces have been selected),
        // or the game is over because someone won, which is true if takeTurn returns true
        while (!board.isFull() && !gameOver) {
            // each time through the while loop, iterate over the players array (2 players);
            // we add a break just in case the first player in array wins the game,
            // to prevent the next player from getting another turn
            for (Player player : players) {
                if (takeTurn(player)) {
                    gameOver = true;
                    break;  // breaks out of the for-loop -- NOT the while loop
                }
            }
        }
        System.out.println("Thanks for playing!");
    }

    /**
     * Allows the current player to take a turn.
     * Print a message saying whose turn it is: X or O
     * Draw the board for player reference.
     * Allow the appropriate player to enter the space number they want to occupy and record the move.
     * If the player has won the game, print a message and return true.
     * If the board is full, print a message and return true.
     * Otherwise, the game is not yet over and return false.
     *
     * @param player the player taking the turn.
     * @return true if the GAME is over, false if the TURN is over but the game is not over
     */
    public boolean takeTurn(Player player) {
        Scanner scanner = new Scanner(System.in);
        boolean selectedValidSpace = false;

        // repeat until player selects a valid space, which occurs when recordMove returns true;
        // this occurs when the player has selected a numbered "blank" space.
        while (!selectedValidSpace) {
            System.out.println("\nPlayer " + player.getSymbol() + "'s turn! Choose a space: ");
            System.out.print("> ");

            int chosenSpace = scanner.nextInt() - 1;
            selectedValidSpace = board.recordMove(chosenSpace % board.getSize(), chosenSpace / board.getSize(), player);
        }

        // redraw the board, which will include the newly placed X or O as updated via recordMove
        board.drawBoard();

        // check to see if the board reveals a winning condition for either X or O
        String winner = board.checkWinner();

        if (winner != null) {
            System.out.println("\n" + winner + "won!");
            return true;
        }

        if (board.isFull()) {
            System.out.println("The game is a tie!");
            return true;
        }

        return false;
    }
}