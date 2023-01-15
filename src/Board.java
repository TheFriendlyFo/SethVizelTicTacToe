public class Board {
    private final Tile[][] tiles;
    private final WinCondition[] winningConfigs;

    private final int size;

    /**
     * Constructs 9 new Space objects and adds them to the spaces array.
     * Also sets up the 8 winning conditions for tic-tac-toe.
     */
    public Board(int size) {
        this.size = size;
        tiles = new Tile[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                tiles[y][x] = new Tile(y * size + x + 1);
            }
        }

        winningConfigs = new WinCondition[]{
                new WinCondition(1, 1, size),
                new WinCondition(1, 0, size),
                new WinCondition(0, 1, size),
                new WinCondition(new Point(1,0), new Point(1,1), new Point(0,1))
        };
    }

    public boolean checkIdx(int xIdx, int yIdx) {
        return 0 <= xIdx && xIdx < size && 0 <= yIdx && yIdx < size;
    }

    public int getSize() {
        return size;
    }

    // getter method; note that there is
    // a parameter, which allows caller to specify
    // which specific Space (of the 9) to return
    public String getSpace(int xIdx, int yIdx) {
        if (checkIdx(xIdx, yIdx)) {
            return tiles[yIdx][xIdx].getSymbol();
        }
        return null;
    }

    /**
     * Draws the tic-tac-toe board so that the user can see what is happening.
     * The empty board should look like this:
     * <p>
     * 1|2|3
     * -----
     * 4|5|6
     * -----
     * 7|8|9
     * <p>
     * As the players choose space numbers, those spaces will be filled in with Xs and Os.
     * <p>
     * O|2|3
     * -----
     * 4|X|6
     * -----
     * 7|8|9
     * <p>
     * This method goes through each space on the board and checks for BLANKS.
     * If the space is BLANK, it prints the appropriate number.
     * Otherwise, it prints the appropriate symbol.
     */
    public void drawBoard() {
        System.out.println("\n}-------------------------------------------------------------------{\n");
        System.out.printf("}%s{\n", "-".repeat(2 + size * 5));

        for (Tile[] row : tiles) {
            System.out.print("}-");
            for (Tile tile : row) {
                System.out.printf("{ %-2s}", tile.getSymbol());
            }

            System.out.println("-{ ");
            System.out.printf("}%s{\n", "-".repeat(2 + size * 5));
        }
    }


    public boolean recordMove(int xIdx, int yIdx, Player player) {
        // if user chooses a space between 1 and 9, try to occupy it, which updates
        // the symbol and returns true if the space is currently a numbered "blank" space
        if (checkIdx(xIdx, yIdx)) {
            return tiles[yIdx][xIdx].occupySpace(player.getSymbol());
        }
        return false;
    }

    /**
     * Uses the checkConfiguration() method to determine whether there is a winner.
     * By checking each of the various WinConditions into checkConfiguration,
     * this method will know whether the game has been won, and if so, by who (X or O).
     *
     * @return if there IS a winning condition on the board, appropriates returns either "X" or "O",
     * whichever the winning symbol is; if there is NO winning condition and no
     * current winner, this method returns BLANK!
     */
    public String checkWinner() {
        for (WinCondition config : winningConfigs) {
            // if a player HAS achieved a particular win configuration on the board...
            String winner = config.getWinner(this);

            if (winner != null) {
                // if the config is a winning condition, then all three Spaces
                // making up that configuration have the same symbol (i.e. 3 X's in a row
                // or 3 O's in a row), so arbitrarily choose the first int value
                // in the array (index 0) to get the winning symbol

                return winner;
            }
        }
        return null;
    }


    /**
     * Determines whether the board is full (has no BLANK spaces).
     *
     * @return True if there are no BLANKS in any spaces.
     */
    public boolean isFull() {
        for (Tile[] row : tiles) {
            for (Tile column : row) {
                if (column.isBlank()) {
                    return false;
                }
            }
        }
        return true;
    }
}
