import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {
        return checkHorizontal(grid) || checkVertical(grid);
    }

    // --------------------------------------------------
    // Horizontal partition
    // --------------------------------------------------
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

            // No deletion needed
            if (upperSum == lowerSum) {
                return true;
            }

            if (upperSum > lowerSum) {
                long need = upperSum - lowerSum;

                // Delete one cell from upper part
                if (canDeleteHorizontalUpper(grid, cut, need)) {
                    return true;
                }
            } else {
                long need = lowerSum - upperSum;

                // Delete one cell from lower part
                if (canDeleteHorizontalLower(grid, cut, need)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Upper rectangle: rows [0 ... cut]
    private boolean canDeleteHorizontalUpper(
            int[][] grid,
            int cut,
            long need) {

        int n = grid[0].length;
        int height = cut + 1;

        // Single row:
        // only endpoints can be removed without disconnecting.
        if (height == 1) {
            return grid[0][0] == need ||
                   grid[0][n - 1] == need;
        }

        // Single column:
        // only endpoints can be removed.
        if (n == 1) {
            return grid[0][0] == need ||
                   grid[cut][0] == need;
        }

        // Height >= 2 and width >= 2.
        // Any boundary cell can be removed.

        // First and last rows
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == need ||
                grid[cut][j] == need) {
                return true;
            }
        }

        // First and last columns
        for (int i = 1; i < cut; i++) {
            if (grid[i][0] == need ||
                grid[i][n - 1] == need) {
                return true;
            }
        }

        return false;
    }

    // Lower rectangle: rows [cut + 1 ... m - 1]
    private boolean canDeleteHorizontalLower(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int n = grid[0].length;

        int firstRow = cut + 1;
        int lastRow = m - 1;

        int height = lastRow - firstRow + 1;

        // Single row
        if (height == 1) {
            return grid[firstRow][0] == need ||
                   grid[firstRow][n - 1] == need;
        }

        // Single column
        if (n == 1) {
            return grid[firstRow][0] == need ||
                   grid[lastRow][0] == need;
        }

        // First and last rows
        for (int j = 0; j < n; j++) {
            if (grid[firstRow][j] == need ||
                grid[lastRow][j] == need) {
                return true;
            }
        }

        // First and last columns
        for (int i = firstRow + 1; i < lastRow; i++) {
            if (grid[i][0] == need ||
                grid[i][n - 1] == need) {
                return true;
            }
        }

        return false;
    }

    // --------------------------------------------------
    // Vertical partition
    // --------------------------------------------------
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

            // No deletion needed
            if (leftSum == rightSum) {
                return true;
            }

            if (leftSum > rightSum) {
                long need = leftSum - rightSum;

                // Delete one cell from left part
                if (canDeleteVerticalLeft(grid, cut, need)) {
                    return true;
                }
            } else {
                long need = rightSum - leftSum;

                // Delete one cell from right part
                if (canDeleteVerticalRight(grid, cut, need)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Left rectangle: columns [0 ... cut]
    private boolean canDeleteVerticalLeft(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int width = cut + 1;

        // Single column
        if (width == 1) {
            return grid[0][0] == need ||
                   grid[m - 1][0] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][0] == need ||
                   grid[0][cut] == need;
        }

        // First and last columns
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == need ||
                grid[i][cut] == need) {
                return true;
            }
        }

        // First and last rows
        for (int j = 1; j < cut; j++) {
            if (grid[0][j] == need ||
                grid[m - 1][j] == need) {
                return true;
            }
        }

        return false;
    }

    // Right rectangle: columns [cut + 1 ... n - 1]
    private boolean canDeleteVerticalRight(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int n = grid[0].length;

        int firstCol = cut + 1;
        int lastCol = n - 1;

        int width = lastCol - firstCol + 1;

        // Single column
        if (width == 1) {
            return grid[0][firstCol] == need ||
                   grid[m - 1][firstCol] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][firstCol] == need ||
                   grid[0][lastCol] == need;
        }

        // First and last columns
        for (int i = 0; i < m; i++) {
            if (grid[i][firstCol] == need ||
                grid[i][lastCol] == need) {
                return true;
            }
        }

        // First and last rows
        for (int j = firstCol + 1; j < lastCol; j++) {
            if (grid[0][j] == need ||
                grid[m - 1][j] == need) {
                return true;
            }
        }

        return false;
    }
}