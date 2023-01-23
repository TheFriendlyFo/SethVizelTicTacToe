public class AI {
    public static int recursions = 0;

    public static void placeBestMove(Board board, Player[] players, int playerID, int aiDifficulty) {
        int bestX = 0, bestY = 0;
        int best = -1000;

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (board.recordMove(x, y, players[playerID])) {
                    int score = minimax(board, players, (playerID + 1) % players.length, (int) (100 * Math.pow(10, aiDifficulty)));
                    recursions = 0;
                    if (score >= best) {
                        best = score;
                        bestX = x;
                        bestY = y;
                    }
                    board.undoMove(x, y);
                }
            }
        }
        board.recordMove(bestX, bestY, players[playerID]);
    }

    private static int minimax(Board board, Player[] players, int turn, int aiDifficulty) {
        recursions++;
        if (board.isFull() || WinCondition.globalCheck(board) != -1 || recursions >= aiDifficulty) {
            return -WinCondition.globalEvaluation(board, turn);
        }

        int best = -1000;

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (!board.recordMove(x, y, players[turn])) {
                    continue;
                }
                best = Math.max(best, minimax(board, players, (turn + 1) % players.length, aiDifficulty));
                board.undoMove(x, y);
            }
        }
        return -best;
    }


}
