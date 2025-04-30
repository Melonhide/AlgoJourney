package interviewPrepare;

import java.util.List;

public class designTicTacToeVariant_348 {
    // Given a nxn Tic-Tac-Toe board and a player's move (row, col),
    // implement a function isWin that returns true if this move causes the player to win.
    // A player wins if they have all 3 of their marks in a row, column, or diagonal.
    //
    // Constraints:
    // - Board size is fixed at nxn.
    // - Player is either 1 or 2.
    // - Only one move is made and only one call to isWin() will be made.
    //
    // Example:
    // Input: board = [[1,2,0],[1,1,2],[0,0,1]], player = 1, move = (2,2)
    // Output: true

    public static boolean isWin(int[][] board, int player, int row, int col) {
        int n = board.length;
        int rowcnt = 0;
        int colcnt = 0;
        int diagnoal = 0;
        int antidiagnoal = 0;
        for (int i = 0; i < n; i++) {
            if (board[i][col] == player && i != row) {
                colcnt++;
            }
            if (board[row][i] == player && i != col) {
                rowcnt++;
            }
            if (row == col) {
                if (board[i][i] == player && (i != row && i != col)) {
                    diagnoal++;
                }
            }
            if (row + col == n - 1) {
                if (board[i][n - 1 - i] == player && (i != row)) {
                    antidiagnoal++;
                }
            }
        }
        return rowcnt == n - 1 || colcnt == n - 1 || diagnoal == n - 1 || antidiagnoal == n - 1;
    }

    public static void main(String[] args) {
        List<TestCase> testCases = List.of(
                new TestCase(new int[][]{
                        {1, 1, 0},
                        {0, 2, 0},
                        {2, 0, 0}
                }, 1, 0, 2, true, "Horizontal win"),

                new TestCase(new int[][]{
                        {1, 2, 0},
                        {0, 2, 1},
                        {1, 0, 0}
                }, 2, 2, 1, true, "Vertical win"),

                new TestCase(new int[][]{
                        {2, 1, 0},
                        {1, 2, 1},
                        {0, 1, 0}
                }, 2, 2, 2, true, "Main diagonal win"),

                new TestCase(new int[][]{
                        {0, 0, 1},
                        {0, 1, 2},
                        {0, 2, 0}
                }, 1, 2, 0, true, "Anti-diagonal win"),

                new TestCase(new int[][]{
                        {1, 0, 2},
                        {0, 1, 0},
                        {0, 2, 1}
                }, 2, 1, 0, false, "No win"),

                new TestCase(new int[][]{
                        {1, 1, 1, 0},
                        {0, 2, 0, 0},
                        {2, 0, 0, 2},
                        {0, 2, 1, 0}
                }, 1, 0, 3, true, "4x4 Horizontal win"),

                new TestCase(new int[][]{
                        {1, 2, 1, 2},
                        {2, 1, 2, 1},
                        {1, 2, 0, 2},
                        {2, 1, 2, 1}
                }, 2, 3, 2, false, "4x4 No win")
        );

        for (TestCase tc : testCases) {
            boolean result = isWin(tc.board, tc.player, tc.row, tc.col);
            System.out.printf("Test: %-25s Expected: %-5s Got: %-5s %s\n",
                    tc.description,
                    tc.expected,
                    result,
                    result == tc.expected ? "✅" : "❌");
        }
    }

    // 辅助类封装测试用例
    static class TestCase {
        int[][] board;
        int player;
        int row, col;
        boolean expected;
        String description;

        TestCase(int[][] board, int player, int row, int col, boolean expected, String description) {
            this.board = board;
            this.player = player;
            this.row = row;
            this.col = col;
            this.expected = expected;
            this.description = description;

        }
    }
}