import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of players (1-6): ");
        System.out.print("> ");
        int numPlayers = scanner.nextInt();

        System.out.println("Enter board size (1-9): ");
        System.out.print("> ");
        int boardSize = scanner.nextInt();

        TicTacToe ticTacToe = new TicTacToe(numPlayers, boardSize);
        ticTacToe.runGame();
    }
}