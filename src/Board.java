public class Board {
    private final Tile[][] tiles;
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
    public int getSpace(int xIdx, int yIdx) {
        return tiles[yIdx][xIdx].getOwnerId();
    }

    public void drawBoard() {
        System.out.println("\n}-------------------------------------------------------------------{\n");
        System.out.printf("}%s{\n", "-".repeat(2 + size * 5));

        for (Tile[] row : tiles) {
            System.out.print("}-");
            for (Tile tile : row) {
                System.out.printf("{ %-2s}", tile);
            }

            System.out.println("-{ ");
            System.out.printf("}%s{\n", "-".repeat(2 + size * 5));
        }
    }


    public boolean recordMove(int xIdx, int yIdx, Player player) {
        // if user chooses a space between 1 and 9, try to occupy it, which updates
        // the symbol and returns true if the space is currently a numbered "blank" space
        if (checkIdx(xIdx, yIdx)) {
            return tiles[yIdx][xIdx].occupySpace(player);
        }
        return false;
    }

    public void undoMove(int x, int y) {
        tiles[y][x].emptySpace();
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

    public boolean isFilled(int x, int y) {
        return !tiles[y][x].isBlank();
    }
}
