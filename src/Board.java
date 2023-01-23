public class Board {

    private final WinCondition[] configs;
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

        configs = new WinCondition[]{
                new WinCondition(1, 1, 3),
                new WinCondition(1, 0, 3),
                new WinCondition(0, 1, 3),
                new WinCondition(-1, 1, 3),
                new WinCondition(new Point(1, 0), new Point(1, 1), new Point(0, 1))};
    }


    public WinCondition[] getConfigs() {
        return configs;
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
        return tiles[yIdx][xIdx].occupySpace(player);
    }

    public void undoMove(int x, int y) {
        tiles[y][x].emptySpace();
    }

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
