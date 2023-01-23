public class WinCondition {
    private final Point[] points;
    private int xMin, xMax, yMin, yMax;

    /**
     * Creates a winning condition traveling in a linear direction for len spaces.
     * For example, new WinCondition(1,0,4) would travel forward in x increments of 1, and y increments of 0
     * for a length of 4 tiles. This would equate to a horizontal line. These 4 tiles will be used to evaluate
     * the board's state.
     * @param xInc The amount x is to be incremented
     * @param yInc The amount y is to be incremented
     * @param len the number of increments
     */
    public WinCondition(int xInc, int yInc, int len) {
        points = new Point[len - 1];

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(xInc * (i + 1), yInc * (i + 1));
            xMin = Math.min(xMin, points[i].x());
            xMax = Math.max(xMax, points[i].x());
            yMin = Math.min(yMin, points[i].y());
            yMax = Math.max(yMax, points[i].y());
        }
    }

    /**
     * Creates a winning condition composed of 'points' points. These points will be used to evaluate
     * the board's state.
     * @param points the points to be evaluated.
     */
    public WinCondition(Point... points) {
        this.points = points;

        for (Point point : points) {
            xMin = Math.min(xMin, point.x());
            xMax = Math.max(xMax, point.x());
            yMin = Math.min(yMin, point.y());
            yMax = Math.max(yMax, point.y());
        }
    }

    public static int globalCheck(Board board) {
        for (WinCondition config : board.getConfigs()) {
            int winner = config.checkAll(board);

            if (winner != -1) {
                return winner;
            }
        }
        return -1;
    }

    public static int globalEvaluation(Board board, int playerID) {
        int evaluation = 0;
        for (WinCondition config : board.getConfigs()) {
            evaluation += config.evaluateAll(board, playerID);
        }
        return evaluation;
    }

    public int checkAll(Board board) {
        for (int y = -yMin; y < board.getSize() - yMax; y++) {
            for (int x = -xMin; x < board.getSize() - xMax; x++) {
                if (board.getSpace(x, y) != -1 && checkOne(x, y, board)) {
                    return board.getSpace(x, y);
                }
            }
        }
        return -1;
    }

    private boolean checkOne(int x, int y, Board board) {
        int check = board.getSpace(x, y);
        for (Point point : points) {
            if (board.getSpace(x + point.x(), y + point.y()) != check) {
                return false;
            }
        }
        return true;
    }

    private int evaluateAll(Board board, int playerID) {
        int evaluation = 0;
        for (int y = -yMin; y < board.getSize() - yMax; y++) {
            for (int x = -xMin; x < board.getSize() - xMax; x++) {
                evaluation += evaluateOne(x, y, board, playerID);
            }
        }
        return evaluation;
    }

    private int evaluateOne(int x, int y, Board board, int playerID) {
        int owner = board.getSpace(x, y);
        int evaluation = owner == -1 ? 0 : 1;

        for (Point point : points) {
            int compare = board.getSpace(x + point.x(), y + point.y());
            if (owner == -1) {
                owner = compare;
            } else if (compare != -1) {
                if (owner != compare) {
                    return evaluation;
                }
                evaluation++;
            }
        }
        return evaluation == 0 ? 0 : (owner == playerID ? 1 : -1) * ((int) Math.pow(3, evaluation));
    }
}