import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int rows = m - k + 1;
        int cols = n - k + 1;

        int[][] ans = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // Store all elements of the k x k submatrix
                int[] values = new int[k * k];
                int index = 0;

                for (int i = r; i < r + k; i++) {
                    for (int j = c; j < c + k; j++) {
                        values[index++] = grid[i][j];
                    }
                }

                // Sort the elements
                Arrays.sort(values);

                // Find minimum difference between distinct elements
                int minDiff = Integer.MAX_VALUE;

                for (int i = 1; i < values.length; i++) {
                    if (values[i] != values[i - 1]) {
                        minDiff = Math.min(
                            minDiff,
                            values[i] - values[i - 1]
                        );
                    }
                }

                // If all elements are equal
                if (minDiff == Integer.MAX_VALUE) {
                    minDiff = 0;
                }

                ans[r][c] = minDiff;
            }
        }

        return ans;
    }
}