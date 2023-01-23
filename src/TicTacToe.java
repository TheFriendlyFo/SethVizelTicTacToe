import java.util.Scanner;

public class TicTacToe {
    private final Player[] players;
    private final Board board;
    private final int aiDifficulty;

    /**
     * Initializes a game of tictactoe, creating its board and players.
     * @param numPlayers the number of non-AI players
     * @param numAI the number of AI players
     * @param boardSize the side length of the board
     * @param aiDifficulty the amount of processing allowed to the AI, translating
     *                     roughly to its difficulty.
     */
    public TicTacToe(int numPlayers, int numAI, int boardSize, int aiDifficulty) {
        players = new Player[numPlayers + numAI];
        this.aiDifficulty = aiDifficulty;

        for (int i = 0; i < numPlayers; i++) {
            players[i] = new Player(i, false);
        }
        for (int i = numPlayers; i < players.length; i++) {
            players[i] = new Player(i, true);
        }

        board = new Board(boardSize);
    }

    /**
     * A loop will run the game until the takeTurn returns true, meaning game has ended.
     * Every iteration, each player takes a turn placing a mark on the board.
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
     * Print a message saying whose turn it is
     * Draw the board for player reference.
     * If the player is not an AI:
     * Allow the appropriate player to enter the space number they want to occupy and record the move.
     * Otherwise, let the AI process its move and continue.
     * If the player has won the game, print a message and return true.
     * If the board is full, print a message and return true.
     * Otherwise, the game is not yet over and return false.
     *
     * @param player the player taking the turn.
     * @return true if the GAME is over, false if the TURN is over but the game is not over
     */
    public boolean takeTurn(Player player) {
        if (!player.isAI()) {
            Scanner scanner = new Scanner(System.in);
            board.drawBoard();
            boolean selectedValidSpace = false;

            // repeat until player selects a valid space, which occurs when recordMove returns true;
            // this occurs when the player has selected a numbered "blank" space.
            while (!selectedValidSpace) {
                System.out.println("\nPlayer " + player + "'s turn! Choose a space: ");
                System.out.print("> ");

                int chosenSpace = scanner.nextInt() - 1;
                selectedValidSpace = board.recordMove(chosenSpace % board.getSize(), chosenSpace / board.getSize(), player);
            }
            // If the player is an AI, process the next move using AI.placeBestMove.
        } else {
            System.out.println("\nAI " + player + "is processing...");
            AI.placeBestMove(board, players, player.ID(), aiDifficulty);
        }
        // Draws the board for the next player, or the end of the game
        board.drawBoard();

        // Determines the state of the board
        int winner = WinCondition.globalCheck(board);

        if (winner != -1) {
            System.out.println("\n" + players[winner] + "won!");
            return true;
        }

        if (board.isFull()) {
            System.out.println("The game is a tie!");
            return true;
        }

        return false;
    }
}