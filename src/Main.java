import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of players (1-6): ");
        System.out.print("> ");
        int numPlayers = scanner.nextInt();

        System.out.println("Enter number of AI players (1-3): ");
        System.out.print("> ");
        int numAI = scanner.nextInt();

        System.out.println("Enter the AI difficulty (1-5, higher difficulties may require more processing time):");
        System.out.print("> ");
        int aiDifficulty = scanner.nextInt();

        System.out.println("Enter board size (1-9): ");
        System.out.print("> ");
        int boardSize = scanner.nextInt();

        TicTacToe ticTacToe = new TicTacToe(numPlayers, numAI, boardSize, aiDifficulty);
        ticTacToe.runGame();
    }
}