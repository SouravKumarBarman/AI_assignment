import java.util.Scanner;

public class ticTacToe {
    

    static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(board[i][0] + "|" +
                    board[i][1] + "|" +
                    board[i][2]);
            if (i < 2)
                System.out.println("-----");
        }
    }

    static boolean isMovesLeft() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }

    static int evaluate() {

        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1]
                    && board[row][1] == board[row][2]) {

                if (board[row][0] == 'O')
                    return 10;
                if (board[row][0] == 'X')
                    return -10;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col]
                    && board[1][col] == board[2][col]) {

                if (board[0][col] == 'O')
                    return 10;
                if (board[0][col] == 'X')
                    return -10;
            }
        }

        if (board[0][0] == board[1][1]
                && board[1][1] == board[2][2]) {

            if (board[0][0] == 'O')
                return 10;
            if (board[0][0] == 'X')
                return -10;
        }

        if (board[0][2] == board[1][1]
                && board[1][1] == board[2][0]) {

            if (board[0][2] == 'O')
                return 10;
            if (board[0][2] == 'X')
                return -10;
        }

        return 0;
    }

    static int minimax(boolean isMax) {

        int score = evaluate();

        if (score == 10 || score == -10)
            return score;

        if (!isMovesLeft())
            return 0;

        if (isMax) {

            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == ' ') {

                        board[i][j] = 'O';

                        best = Math.max(best,
                                minimax(false));

                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {

            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == ' ') {

                        board[i][j] = 'X';

                        best = Math.min(best,
                                minimax(true));

                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    static void bestMove() {

        int bestVal = -1000;
        int row = -1, col = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board[i][j] == ' ') {

                    board[i][j] = 'O';

                    int moveVal = minimax(false);

                    board[i][j] = ' ';

                    if (moveVal > bestVal) {
                        row = i;
                        col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        board[row][col] = 'O';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            printBoard();

            System.out.print("Enter row and col (0-2): ");
            int r = sc.nextInt();
            int c = sc.nextInt();

            board[r][c] = 'X';

            if (evaluate() == -10) {
                printBoard();
                System.out.println("You Win!");
                break;
            }

            if (!isMovesLeft()) {
                printBoard();
                System.out.println("Draw!");
                break;
            }

            bestMove();

            if (evaluate() == 10) {
                printBoard();
                System.out.println("Computer Wins!");
                break;
            }
        }
    }
}