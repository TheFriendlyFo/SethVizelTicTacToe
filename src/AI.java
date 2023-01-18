import java.util.Scanner;

public class AI {

    public static void placeBestMove(Board board, Player[] players, int player){
        Point bestMove = null;
        int best = 100;

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (board.recordMove(x,y,players[player])) {
                    int score = minimax(board, players, (player + 1) % players.length, 0);
                    if (betterScore(score, best, player) == score) {
                        if (score - player == 0 || score == -1) {
                            return;
                        }
                        best = betterScore(best, score, player);
                        bestMove = new Point(x,y);
                    }
                    board.undoMove(x,y);
                }
            }
        }
        board.recordMove(bestMove.x(), bestMove.y(), players[player]);
    }

    private static int minimax(Board board, Player[] players, int turn, int depth) {
        int winner = WinCondition.checkAll(board);

        if (board.isFull() || winner != -1) {
            return winner;
        }

        int best = 100;

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (!board.recordMove(x,y,players[turn])) {
                    continue;
                }
                int score = minimax(board, players, (turn + 1) % players.length, depth + 1);
                board.undoMove(x,y);
                if (score - turn == 0 || score == -1) {
                    return score;
                }
                best = betterScore(best, score, turn);
            }
        }
        return best;
    }

    private static int betterScore(int score1, int score2, int playerNum) {
        return Math.abs(playerNum - score1) < Math.abs(playerNum - score2) ? score1 : score2;
    }
}
