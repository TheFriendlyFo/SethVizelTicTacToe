import java.util.Scanner;

public class AI {

    public static void placeBestMove(Board board, Player[] players, int player){
        Point currentMove = null;
        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (board.recordMove(x,y,players[player])) {
                    currentMove = new Point(x,y);
                    int winner = minimax(board, players, (player + 1) % players.length);
                    if (winner == player || winner == -1) {
                        return;
                    } else {
                        board.undoMove(x,y);
                    }
                }
            }
        }
        board.recordMove(currentMove.x(), currentMove.y(), players[player]);
    }

    public static int minimax(Board board, Player[] players, int turn) {
        int winner = WinCondition.checkAll(board);

        if (board.isFull() || winner != -1) {
            return winner;
        }

        for (int y = 0; y < board.getSize(); y++) {
            for (int x = 0; x < board.getSize(); x++) {
                if (!board.recordMove(x,y,players[turn])) {
                    continue;
                }
                /*board.drawBoard();
                (new Scanner(System.in)).nextLine();*/
                winner = minimax(board, players, (turn + 1) % players.length);
                board.undoMove(x,y);
                if (winner == turn || winner == -1) {
                    return turn;
                }
            }
        }
        return winner;
    }
}
