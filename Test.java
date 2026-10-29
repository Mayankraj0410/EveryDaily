import java.util.Scanner;

public class Test {
    private static final char EMPTY = ' ';

    public static void main(String[] args) {
        char[] board = {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY};
        Scanner scanner = new Scanner(System.in);
        char player = 'X';

        System.out.println("Tic-Tac-Toe");
        System.out.println("Choose a position from 1 to 9:");
        printBoard(board);

        while (true) {
            System.out.print("Player " + player + ", enter your position: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number from 1 to 9.");
                scanner.next();
                continue;
            }

            int position = scanner.nextInt();
            if (position < 1 || position > 9 || board[position - 1] != EMPTY) {
                System.out.println("That position is not available. Try again.");
                continue;
            }

            board[position - 1] = player;
            printBoard(board);

            if (hasWon(board, player)) {
                System.out.println("Player " + player + " wins!");
                break;
            }

            if (isDraw(board)) {
                System.out.println("It's a draw!");
                break;
            }

            player = player == 'X' ? 'O' : 'X';
        }

        scanner.close();
    }

    private static void printBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            char value = board[i] == EMPTY ? (char) ('1' + i) : board[i];
            System.out.print(" " + value + " ");

            if (i % 3 != 2) {
                System.out.print("|");
            } else if (i != 8) {
                System.out.println("\n---+---+---");
            }
        }
        System.out.println("\n");
    }

    private static boolean hasWon(char[] board, char player) {
        int[][] winningLines = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int[] line : winningLines) {
            if (board[line[0]] == player
                    && board[line[1]] == player
                    && board[line[2]] == player) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDraw(char[] board) {
        for (char cell : board) {
            if (cell == EMPTY) {
                return false;
            }
        }
        return true;
    }
}
