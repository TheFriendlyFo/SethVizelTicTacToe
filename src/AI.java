public class AI {

    private String symbol = "jhnj";

    public AI (Player player) {
        this.symbol = player.getSymbol();
    }

    public Point findBestMove(Board board) {
        Point bestMove = null;



        return bestMove;
    }

    public int evaluate(Board board) {
        return switch (WinCondition.checkAll(board)){
            case "" -> 0;
            case null -> 10;
            default -> -10;
        };

    }
}
