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

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               tiles[i][j] = new Tile();
            }
        }

        winningConfigs = new WinCondition[8];
        winningConfigs[0] = new WinCondition(0, 1, 2);
        winningConfigs[1] = new WinCondition(3, 4, 5);
        winningConfigs[2] = new WinCondition(6, 7, 8);
        winningConfigs[3] = new WinCondition(0, 3, 6);
        winningConfigs[4] = new WinCondition(1, 4, 7);
        winningConfigs[5] = new WinCondition(2, 5, 8);
        winningConfigs[6] = new WinCondition(0, 4, 8);
        winningConfigs[7] = new WinCondition(2, 4, 6);
    }

    public  boolean checkIdx(int xIdx, int yIdx) {
        return 0 <= xIdx && xIdx < tiles[0].length
                && 0 <= yIdx && yIdx < tiles.length;
    }

    // getter method; note that there is
    // a parameter, which allows caller to specify
    // which specific Space (of the 9) to return
    public Tile getSpace(int xIdx, int yIdx) {
        if (checkIdx(xIdx, yIdx)) {
            return tiles[yIdx][xIdx];
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
        System.out.println();

        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                System.out.print(" | " + tile.getSymbol());
            }

        }
    }


    /**
     * Updates a space based on a player's move by changing it from a blank space to either
     * a an X or O (whichever symbol is associated with player)
     * The method uses an integer (spaceIdx) to determine which space is going to be updated.
     * Method returns TRUE if the space was successfully "occupied", which occurs via the occupySpace
     * method on the space object; if the space was NOT successfully occupied (either because the selected
     * space was outside the 1-9 range, OR the space was ALREADY occupied by the opposite player), then
     * return false to indicate the space was NOT occupied.
     *
     * @param spaceNum the index of the space to be occupied by player.
     * @param player   the Player taking the turn and attempting to "occupy" the space.
     * @return true if the move was successful and the space occupied; return false otherwise.
     */
    public boolean recordMove(int xIdx, int yIdx, Player player) {
        // if user chooses a space between 1 and 9, try to occupy it, which updates
        // the symbol and returns true if the space is currently a numbered "blank" space
        if (checkIdx(xIdx, yIdx)) {
            return tiles[yIdx][xIdx].occupySpace(player.getSymbol());
        }
        return false;
    }

    /**
     * Uses the checkConfiguration() method to determine whether or not there is a winner.
     * By checking each of the various WinConditions into checkConfiguration,
     * this method will know whether or not the game has been won, and if so, by who (X or O).
     *
     * @return if there IS a winning condition on the board, appropriates returns either "X" or "O",
     * whichever the winning symbol is; if there is NO winning condition and no
     * current winner, this method returns BLANK!
     */
    public String checkWinner() {
        for (WinCondition config : winningConfigs) {
            // if a player HAS achieved a particular win configuration on the board...
            if (checkConfiguration(config)) {
                // if the config is a winning condition, then all three Spaces
                // making up that configuration have the same symbol (i.e. 3 X's in a row
                // or 3 O's in a row), so arbitrarily choose the first int value
                // in the array (index 0) to get the winning symbol
                int spaceWithWinningSymbol = config.getWinningSpaces()[0];

                return tiles[spaceWithWinningSymbol / 3][spaceWithWinningSymbol % 3].getSymbol();
            }
        }
        return Tile.BLANK;
    }

    /**
     * Determines whether or not all 3 received spaces have the same symbol.
     *
     * @param comboToCheck The WinCondition containing the three indices of a win condition
     * @return true if the spaces all have the same symbol, i.e. it's a "WIN"
     */
    public boolean checkConfiguration(WinCondition comboToCheck) {
        int[] winningSpaces = comboToCheck.getWinningSpaces();
        int s1 = winningSpaces[0];
        int s2 = winningSpaces[1];
        int s3 = winningSpaces[2];

        return tiles[s1].getSymbol() == tiles[s2].getSymbol() && tiles[s1].getSymbol() == tiles[s3].getSymbol();
    }

    /**
     * Determines whether or not the board is full (has no BLANK spaces).
     *
     * @return True if there are no BLANKs in any spaces.
     */
    public boolean isFull() {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].getSymbol() == Tile.BLANK) {
                return false;
            }
        }
        return true;
    }
}
