import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Board board = new Board();

        Player player1 = new Player('X');
        Player player2 = new Player('O');

        Player currentPlayer = player1;

        boolean gameRunning = true;

        System.out.println("=== TIC TAC TOE ===");

        while (gameRunning) {
            board.displayBoard();

            System.out.print("Player " + currentPlayer.getSymbol() + " enter position (1-9): ");
            int choice = sc.nextInt();

            if (choice < 1 || choice > 9) {
                System.out.println("Invalid input! Try again.");
                continue;
            }

            boolean validMove = board.markCell(choice, currentPlayer.getSymbol());

            if (!validMove) {
                System.out.println("Cell already occupied! Try again.");
                continue;
            }

            // Check win
            if (board.checkWin(currentPlayer.getSymbol())) {
                board.displayBoard();
                System.out.println("🎉 Player " + currentPlayer.getSymbol() + " Wins!");
                break;
            }

            // Check draw
            if (board.isFull()) {
                board.displayBoard();
                System.out.println("It's a Draw!");
                break;
            }

            // Switch player
            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }

        sc.close();
    }
}