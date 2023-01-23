import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int aiDifficulty = 0;

        System.out.println("Enter number of players (1-6): ");
        System.out.print("> ");
        int numPlayers = scanner.nextInt();

        System.out.println("Enter number of AI players (1-3): ");
        System.out.print("> ");
        int numAI = scanner.nextInt();

        if (numAI > 0) {
            System.out.println("Enter AI complexity (1-5, higher complexities may require more processing time):");
            System.out.print("> ");
            aiDifficulty = scanner.nextInt();
        }

        System.out.println("Enter board size (1-9): ");
        System.out.print("> ");
        int boardSize = scanner.nextInt();

        TicTacToe ticTacToe = new TicTacToe(numPlayers, numAI, boardSize, aiDifficulty);
        ticTacToe.runGame();
    }
}