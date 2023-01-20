public class AI {
    public static int reccursions = 0;

    public static void placeBestMove(Board board, Player[] players, int player){
        Point bestMove = null;
        int best = -100;

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (board.recordMove(x,y,players[player])) {
                    int score = minimax(board, players, (player + 1) % players.length);

                    if (score >= best) {
                        if (score == 10) {
                            return;
                        }
                        best = score;
                        bestMove = new Point(x,y);
                    }
                    board.undoMove(x,y);
                }
            }
        }
        board.recordMove(bestMove.x(), bestMove.y(), players[player]);
    }

    private static int minimax(Board board, Player[] players, int turn) {
        reccursions++;
        int winner = WinCondition.checkAll(board);

        if (board.isFull() || winner != -1) {
            return winner == turn ? -10 :
                    winner == Math.floorMod(turn - 1, players.length) ? 10 :
                    winner == -1 ? 5 : 0;
        }

        int best = -100;

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (!board.recordMove(x,y,players[turn])) {
                    continue;
                }
                best = Math.max(best, minimax(board, players, (turn + 1) % players.length));
                board.undoMove(x,y);
                if (best >= 10) {
                    return -best;
                }
            }
        }
        return -best;
    }



}
