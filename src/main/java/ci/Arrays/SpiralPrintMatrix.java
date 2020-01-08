package ci.Arrays;

/**
 * Created by Gopala Akshintala on 11/2/16.
 */
public class SpiralPrintMatrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}};
        var rows = matrix.length;
        var cols = matrix[0].length;
        var start = 0;

        while (rows > start * 2 && cols > start * 2) {
            printSpiral(matrix, rows, cols, start);
            ++start;
        }
    }

    private static void printSpiral(int[][] matrix, int rows, int cols, int start) {
        var endX = cols - 1 - start;
        var endY = rows - 1 - start;

        // left to right
        for (var i = start; i <= endX; i++) {
            System.out.print(matrix[start][i] + " ");
        }

        // top to bottom, minimum 2 rows required
        if (start < endY) {
            for (var i = start + 1; i <= endY; i++) {
                System.out.print(matrix[i][endX] + " ");
            }
        }

        // right to left, minimum 2 rows and 2 cols required, two rows because if only one row, it would have been
        // covered in the first loop from left to right
        if (start < endX && start < endY) {
            for (var i = endX - 1; i >= start; i--) {
                System.out.print(matrix[endY][i] + " ");
            }
        }

        // bottom to top, atleast 3 rows and 2 cols, as if only 2 rows, it would hv already been covered.
        if (start < endX && start < endY - 1) {
            for (var i = endY - 1; i >= start + 1; i--) {
                System.out.print(matrix[i][start] + " ");
            }
        }
    }

}
