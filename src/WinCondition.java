public class WinCondition {
    private final Point[] points;
    private int xMin, xMax, yMin, yMax;

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

    public WinCondition(Point... points) {
        this.points = points;

        for (Point point : points) {
            xMin = Math.min(xMin, point.x());
            xMax = Math.max(xMax, point.x());
            yMin = Math.min(yMin, point.y());
            yMax = Math.max(yMax, point.y());
        }
    }

    public static int checkAll(Board board){
        WinCondition[] winningConfigs = new WinCondition[]{
                new WinCondition(1, 1, board.getSize()),
                new WinCondition(1, 0, board.getSize()),
                new WinCondition(0, 1, board.getSize()),
                new WinCondition(-1, 1, board.getSize()),
                new WinCondition(new Point(1,0), new Point(1,1), new Point(0,1))
        };

        for (WinCondition config : winningConfigs) {
            int winner = config.getWinner(board);

            if (winner != -1) {
                return winner;
            }
        }
        return -1;
    }


    public int getWinner(Board board) {
        for (int y = -yMax; y < board.getSize() - yMax; y++) {
            for (int x = -xMax; x < board.getSize() - xMax; x++) {
                if (check(x, y, board)) {
                    return board.getSpace(x,y);
                }
            }
        }
        return -1;
    }

    private boolean check(int x, int y, Board board) {
        int check = board.getSpace(x, y);
        for (Point point : points) {
            if (board.getSpace(x + point.x(), y + point.y()) != check) {
                return false;
            }
        }
        return true;
    }
}