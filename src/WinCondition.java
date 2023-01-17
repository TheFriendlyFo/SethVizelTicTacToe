public class WinCondition {
    private final Point[] points;
    private int xMax, yMax;

    public WinCondition(int xInc, int yInc, int len) {
        points = new Point[len - 1];
        xMax = 0;
        yMax = 0;

        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(xInc * (i + 1), yInc * (i + 1));
            xMax = Math.max(xMax, points[i].x());
            yMax = Math.max(yMax, points[i].y());
        }
    }

    public static String checkAll(Board board){
        WinCondition[] winningConfigs = new WinCondition[]{
                new WinCondition(1, 1, board.getSize()),
                new WinCondition(1, 0, board.getSize()),
                new WinCondition(0, 1, board.getSize()),
                new WinCondition(new Point(1,0), new Point(1,1), new Point(0,1))
        };

        for (WinCondition config : winningConfigs) {
            String winner = config.getWinner(board);

            if (winner != null) {
                return winner;
            }
        }
        return "";
    }

    public WinCondition(Point... points) {
        this.points = points;
        xMax = 0;
        yMax = 0;

        for (Point point : points) {
            xMax = Math.max(xMax, point.x());
            yMax = Math.max(yMax, point.y());
        }
    }

    public String getWinner(Board board) {
        for (int y = 0; y < board.getSize() - yMax; y++) {
            for (int x = 0; x < board.getSize() - xMax; x++) {
                if (check(x, y, board)) {
                    return board.getSpace(x,y);
                }
            }
        }
        return null;
    }

    private boolean check(int x, int y, Board board) {
        String check = board.getSpace(x, y);
        for (Point point : points) {
            if (!board.getSpace(x + point.x(), y + point.y()).equals(check)) {
                return false;
            }
        }
        return true;
    }
}