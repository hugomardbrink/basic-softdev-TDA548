package exercises;

import java.util.Arrays;

import static java.lang.System.out;

/*
 * Methods with array/matrix params and/or return value. Implement methods.
 *
 * See:
 * - Matrices
 */
public class Ex3MatrixMethods {

    public static void main(String[] args) {
        new Ex3MatrixMethods().program();
    }

    void program() {
        int[][] m = {           // Hard coded test data
                {-1, 0, -5, 3},
                {6, 7, -2, 0},
                {9, -2, -6, 8},
                {0, 0, 5, -6}
        };

        // TODO uncomment one at a time and implement

        // Return array with all negatives in m
        int[] negs = getNegatives(m);
        out.println(negs.length == 6);
        out.println(Arrays.toString(negs).equals("[-1, -5, -2, -2, -6, -6]")); // Possibly other ordering!

        // Mark all negatives with a 1, others as 0
        // (create matrix on the fly)
        int[][] marked = markNegatives(new int[][]{
                {1, -2, 3,},
                {-4, 5, -6,},
                {7, -8, 9,},
        });
        /* marked should be (don't uncomment)
        { {0, 1, 0},
          {1, 0, 1},
          {0, 1, 0} }
        */
        out.println(Arrays.toString(marked[0]).equals("[0, 1, 0]"));
        out.println(Arrays.toString(marked[1]).equals("[1, 0, 1]"));
        out.println(Arrays.toString(marked[2]).equals("[0, 1, 0]"));

        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // Create matrix from array
        int[][] matrix = toMatrix(arr);
        /* matrix should be (don't uncomment)
        { {1, 2, 3},
          {4, 5, 6},
          {7, 8, 9} }
        */
        out.println(Arrays.toString(matrix[0]).equals("[1, 2, 3]"));
        out.println(Arrays.toString(matrix[1]).equals("[4, 5, 6]"));
        out.println(Arrays.toString(matrix[2]).equals("[7, 8, 9]"));

        // Sum of all directly surrounding elements to some element in matrix
        // (not counting the element itself)
        // NOTE: Should be possible to expand method to include more distant neighbours
        out.println(sumNeighbours(matrix, 0, 0) == 11);
        out.println(sumNeighbours(matrix, 1, 1) == 40);
        out.println(sumNeighbours(matrix, 1, 0) == 23);
    }

    // -------- Write methods below this -----------------------

    int[] getNegatives(int[][] matrix) {
        int arrayIndex = 0;
        int[] matches = new int[matrix.length + matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                if (matrix[i][k] < 0) {
                    matches[arrayIndex] = matrix[i][k];
                    arrayIndex++;
                }
            }
        }
        int[] matchScaled = new int[arrayIndex];
        for (int i = 0; i < arrayIndex; i++) {
            matchScaled[i] = matches[i];
        }
        return matchScaled;
    }

    int[][] markNegatives(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                if (matrix[i][k] < 0) {
                    matrix[i][k] = 1;
                } else {
                    matrix[i][k] = 0;
                }
            }
        }
        return matrix;
    }

    int[][] toMatrix(int[] arr) {
        int arrIndex = 0;

        int[][] matrix = new int[(int) (Math.sqrt(arr.length))][(int) (Math.sqrt(arr.length))];
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[0].length; k++) {
                matrix[i][k] = arr[arrIndex];
                arrIndex++;
            }
        }
        return matrix;
    }

    int sumNeighbours(int[][] matrix, int row, int col) {
        int sum = 0,
            rowStart = row - 2,
            colStart = col - 2,
            rowStop = row + 2,
            colStop = col + 2;

        if (rowStart < 0){
            rowStart = 0;
        } if (rowStop > matrix.length){
            rowStop = matrix.length;
        } if (colStart < 0){
            colStart = 0;
        } if (colStop > matrix[0].length){
            colStop = matrix[0].length;
        }

        for (int r = rowStart; r < rowStop; r++) {
            for (int c = colStart; c < colStop; c++) {
                if (matrix[r][c] != matrix[row][col]) {
                    sum += matrix[r][c];
                }
            }
        }
        return sum;
    }
}




