package ci.BackTracking.RobotMove;

/**
 * Created by gakshintala on 4/7/16.
 */
public class RobotMove {

    public static void main(String[] args) {
        int m = 3, n = 3;
        var threshold = 3;
        var visited = new boolean[m][n];
        System.out.println(traversableGridCount(0, 0, threshold, visited));
    }

    private static int traversableGridCount(int i, int j, int threshold, boolean[][] visited) {
        var count = 0;
        if (isValidMove(i, j, threshold, visited)) {
            visited[i][j] = true;
            count = 1 + traversableGridCount(i + 1, j, threshold, visited) +
                    traversableGridCount(i - 1, j, threshold, visited) +
                    traversableGridCount(i, j + 1, threshold, visited) +
                    traversableGridCount(i + 1, j, threshold, visited);
        }
        return count;
    }

    private static boolean isValidMove(int i, int j, int threshold, boolean[][] visited) {
        return !visited[i][j] && isIndexInbound(i, j) && (sumOfDigits(i) + sumOfDigits(j) <= threshold);
    }

    private static boolean isIndexInbound(int i, int j) {
        return i >= 0 && i < 3 && j >= 0 && j < 3;
    }

    private static int sumOfDigits(int n) {
        var sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }

}
