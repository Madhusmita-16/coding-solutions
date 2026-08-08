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

                // Store elements of the k x k submatrix
                int[] values = new int[k * k];
                int idx = 0;

                for (int i = r; i < r + k; i++) {
                    for (int j = c; j < c + k; j++) {
                        values[idx++] = grid[i][j];
                    }
                }

                // Sort so the minimum difference is between
                // two adjacent distinct values.
                Arrays.sort(values);

                int minDiff = Integer.MAX_VALUE;

                for (int i = 1; i < values.length; i++) {
                    if (values[i] != values[i - 1]) {
                        minDiff = Math.min(
                            minDiff,
                            values[i] - values[i - 1]
                        );
                    }
                }

                // If all values are equal, answer is 0.
                if (minDiff == Integer.MAX_VALUE) {
                    minDiff = 0;
                }

                ans[r][c] = minDiff;
            }
        }

        return ans;
    }
}