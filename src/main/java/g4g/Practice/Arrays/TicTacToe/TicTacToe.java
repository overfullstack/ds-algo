package g4g.Practice.Arrays.TicTacToe;

import java.util.Scanner;

/**
 * Created by gakshintala on 6/14/16.
 */
public class TicTacToe {
    // This matrix is used to find indexes to check all
    // possible wining triplets in board[0..8]
    private static int WIN[][] = {{0, 1, 2}, // Check first row.
            {3, 4, 5}, // Check second Row
            {6, 7, 8}, // Check third Row
            {0, 3, 6}, // Check first column
            {1, 4, 7}, // Check second Column
            {2, 5, 8}, // Check third Column
            {0, 4, 8}, // Check first Diagonal
            {2, 4, 6}}; // Check second Diagonal

    public static void main(String[] args) {
        var scn = new Scanner(System.in);
        var tests = scn.nextInt();
        while (tests-- > 0) {
            var board = new char[9];
            fillArray(board, scn);
            System.out.println(isBoardValid(board) ? "Valid" : "Invalid");
        }
    }

    private static void fillArray(char[] arr, Scanner scn) {
        for (var i = 0; i < arr.length; i++) {
            arr[i] = scn.next().charAt(0);
        }
    }

    private static boolean isBoardValid(char[] board) {
        var xCount = findCount(board, 'X');
        var oCount = findCount(board, 'O');
        // Valid only if count is equal or x is +1 ahead of o
        if (xCount == oCount || xCount == oCount + 1) {
            if (isWinning(board, 'O')) {
                // false if both are winning
                if (isWinning(board, 'X')) {
                    return false;
                }
                // If O is winning, count should be equal
                return (xCount == oCount);
            }
            // If X is winning, it should be 1 ahead
            if (isWinning(board, 'X') && (xCount != oCount + 1)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private static boolean isWinning(char[] board, char c) {
        // Checking winning combination, by row by row in WIN array
        for (var i = 0; i < 8; i++) {
            if (board[WIN[i][0]] == c
                    && board[WIN[i][1]] == c
                    && board[WIN[i][2]] == c) {
                return true;
            }
        }
        return false;
    }

    private static int findCount(char[] board, char x) {
        var count = 0;
        for (var c : board) {
            if (c == x) {
                count++;
            }
        }
        return count;
    }
}
