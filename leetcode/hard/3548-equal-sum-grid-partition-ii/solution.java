import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {

        for (int rotation = 0; rotation < 4; rotation++) {

            if (check(grid)) {
                return true;
            }

            grid = rotate(grid);
        }

        return false;
    }

    // Check horizontal partitions where at most one cell
    // can be removed from the upper part.
    private boolean check(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A horizontal split requires at least 2 rows.
        if (m < 2) {
            return false;
        }

        long total = 0;

        for (int[] row : grid) {
            for (int value : row) {
                total += value;
            }
        }

        long upperSum = 0;

        // Values already encountered in the upper part.
        HashSet<Integer> seen = new HashSet<>();

        // 0 represents "delete nothing".
        seen.add(0);

        for (int i = 0; i < m - 1; i++) {

            // Add current row to upper part.
            for (int j = 0; j < n; j++) {
                seen.add(grid[i][j]);
                upperSum += grid[i][j];
            }

            long need = 2L * upperSum - total;

            /*
             * If we remove x from upper part:
             *
             * upperSum - x = total - upperSum
             *
             * Therefore:
             *
             * x = 2 * upperSum - total
             */

            if (seen.contains((int) need)) {

                // For normal multi-column grids, any cell in
                // the upper part can be removed while keeping
                // the remaining part connected, except when
                // the upper part consists of a single row.
                if (i > 0 || n > 1) {
                    return true;
                }

                // For the first row, only the two boundary
                // cells can be removed while preserving connectivity.
                if (grid[0][0] == need ||
                    grid[0][n - 1] == need ||
                    need == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    // Rotate matrix 90 degrees clockwise.
    private int[][] rotate(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rotated = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m - 1 - i] = grid[i][j];
            }
        }

        return rotated;
    }
}