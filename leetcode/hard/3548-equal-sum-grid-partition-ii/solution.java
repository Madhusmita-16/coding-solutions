import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {
        return checkHorizontal(grid) || checkVertical(grid);
    }

    // Check horizontal cuts.
    private boolean checkHorizontal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (m == 1) {
            return false;
        }

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        long upperSum = 0;

        for (int cut = 0; cut < m - 1; cut++) {

            for (int j = 0; j < n; j++) {
                upperSum += grid[cut][j];
            }

            long lowerSum = total - upperSum;

            // Already equal.
            if (upperSum == lowerSum) {
                return true;
            }

            if (upperSum > lowerSum) {
                long need = upperSum - lowerSum;

                // Delete from upper part.
                if (canDeleteHorizontalUpper(grid, cut, need)) {
                    return true;
                }

            } else {
                long need = lowerSum - upperSum;

                // Delete from lower part.
                if (canDeleteHorizontalLower(grid, cut, need)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Upper part = rows [0 ... cut]
    private boolean canDeleteHorizontalUpper(
            int[][] grid,
            int cut,
            long need) {

        int n = grid[0].length;
        int height = cut + 1;

        // Only one row.
        if (height == 1) {
            return grid[0][0] == need ||
                   grid[0][n - 1] == need;
        }

        // Only one column.
        if (n == 1) {
            return grid[0][0] == need ||
                   grid[cut][0] == need;
        }

        /*
         * Rectangle has height >= 2 and width >= 2.
         *
         * Any cell on the boundary can be removed while
         * keeping the remaining cells connected.
         *
         * Upper part boundary consists of:
         *   - first row
         *   - last row (cut)
         */
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == need ||
                grid[cut][j] == need) {
                return true;
            }
        }

        return false;
    }

    // Lower part = rows [cut + 1 ... m - 1]
    private boolean canDeleteHorizontalLower(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int n = grid[0].length;

        int firstRow = cut + 1;
        int lastRow = m - 1;
        int height = lastRow - firstRow + 1;

        // Only one row.
        if (height == 1) {
            return grid[firstRow][0] == need ||
                   grid[firstRow][n - 1] == need;
        }

        // Only one column.
        if (n == 1) {
            return grid[firstRow][0] == need ||
                   grid[lastRow][0] == need;
        }

        // Boundary rows of lower rectangle.
        for (int j = 0; j < n; j++) {
            if (grid[firstRow][j] == need ||
                grid[lastRow][j] == need) {
                return true;
            }
        }

        return false;
    }

    // Check vertical cuts.
    private boolean checkVertical(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (n == 1) {
            return false;
        }

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        long leftSum = 0;

        for (int cut = 0; cut < n - 1; cut++) {

            for (int i = 0; i < m; i++) {
                leftSum += grid[i][cut];
            }

            long rightSum = total - leftSum;

            // Already equal.
            if (leftSum == rightSum) {
                return true;
            }

            if (leftSum > rightSum) {
                long need = leftSum - rightSum;

                if (canDeleteVerticalLeft(grid, cut, need)) {
                    return true;
                }

            } else {
                long need = rightSum - leftSum;

                if (canDeleteVerticalRight(grid, cut, need)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Left part = columns [0 ... cut]
    private boolean canDeleteVerticalLeft(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int width = cut + 1;

        // Only one column.
        if (width == 1) {
            return grid[0][0] == need ||
                   grid[m - 1][0] == need;
        }

        // Only one row.
        if (m == 1) {
            return grid[0][0] == need ||
                   grid[0][cut] == need;
        }

        /*
         * Boundary columns of the left rectangle:
         *   - first column
         *   - last column (cut)
         */
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == need ||
                grid[i][cut] == need) {
                return true;
            }
        }

        return false;
    }

    // Right part = columns [cut + 1 ... n - 1]
    private boolean canDeleteVerticalRight(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int n = grid[0].length;

        int firstCol = cut + 1;
        int lastCol = n - 1;
        int width = lastCol - firstCol + 1;

        // Only one column.
        if (width == 1) {
            return grid[0][firstCol] == need ||
                   grid[m - 1][firstCol] == need;
        }

        // Only one row.
        if (m == 1) {
            return grid[0][firstCol] == need ||
                   grid[0][lastCol] == need;
        }

        // Boundary columns of the right rectangle.
        for (int i = 0; i < m; i++) {
            if (grid[i][firstCol] == need ||
                grid[i][lastCol] == need) {
                return true;
            }
        }

        return false;
    }
}