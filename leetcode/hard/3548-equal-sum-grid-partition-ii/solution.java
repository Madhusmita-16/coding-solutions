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

        if (m <= 1) return false;

        long total = 0;

        // Value -> rows containing this value
        Map<Long, List<Integer>> rows = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                total += val;

                rows.computeIfAbsent(val, x -> new ArrayList<>())
                    .add(i);
            }
        }

        long upperSum = 0;

        for (int cut = 0; cut < m - 1; cut++) {

            for (int j = 0; j < n; j++) {
                upperSum += grid[cut][j];
            }

            long lowerSum = total - upperSum;

            // No deletion
            if (upperSum == lowerSum) {
                return true;
            }

            long need;

            if (upperSum > lowerSum) {
                // Delete from upper part
                need = upperSum - lowerSum;

                if (canDeleteHorizontalUpper(
                        grid, cut, need, rows)) {
                    return true;
                }

            } else {
                // Delete from lower part
                need = lowerSum - upperSum;

                if (canDeleteHorizontalLower(
                        grid, cut, need, rows)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Upper = rows [0 ... cut]
    private boolean canDeleteHorizontalUpper(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> rows) {

        int m = grid.length;
        int n = grid[0].length;

        List<Integer> list = rows.get(need);

        if (list == null) return false;

        // If width > 1 and height > 1,
        // ANY cell can be deleted.
        if (n > 1 && cut + 1 > 1) {
            for (int row : list) {
                if (row <= cut) {
                    return true;
                }
            }
            return false;
        }

        // Single row
        if (cut + 1 == 1) {
            return grid[0][0] == need ||
                   grid[0][n - 1] == need;
        }

        // Single column
        if (n == 1) {
            return grid[0][0] == need ||
                   grid[cut][0] == need;
        }

        return false;
    }

    // Lower = rows [cut + 1 ... m-1]
    private boolean canDeleteHorizontalLower(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> rows) {

        int m = grid.length;
        int n = grid[0].length;

        int firstRow = cut + 1;
        int lastRow = m - 1;

        List<Integer> list = rows.get(need);

        if (list == null) return false;

        // Both dimensions > 1:
        // any cell can be removed.
        if (n > 1 && firstRow < lastRow) {
            for (int row : list) {
                if (row >= firstRow) {
                    return true;
                }
            }
            return false;
        }

        // Single row
        if (firstRow == lastRow) {
            return grid[firstRow][0] == need ||
                   grid[firstRow][n - 1] == need;
        }

        // Single column
        if (n == 1) {
            return grid[firstRow][0] == need ||
                   grid[lastRow][0] == need;
        }

        return false;
    }

    // --------------------------------------------------
    // Vertical partition
    // --------------------------------------------------
    private boolean checkVertical(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (n <= 1) return false;

        long total = 0;

        // Value -> columns containing this value
        Map<Long, List<Integer>> cols = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                total += val;

                cols.computeIfAbsent(val, x -> new ArrayList<>())
                    .add(j);
            }
        }

        long leftSum = 0;

        for (int cut = 0; cut < n - 1; cut++) {

            for (int i = 0; i < m; i++) {
                leftSum += grid[i][cut];
            }

            long rightSum = total - leftSum;

            // No deletion
            if (leftSum == rightSum) {
                return true;
            }

            long need;

            if (leftSum > rightSum) {
                // Delete from left
                need = leftSum - rightSum;

                if (canDeleteVerticalLeft(
                        grid, cut, need, cols)) {
                    return true;
                }

            } else {
                // Delete from right
                need = rightSum - leftSum;

                if (canDeleteVerticalRight(
                        grid, cut, need, cols)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Left = columns [0 ... cut]
    private boolean canDeleteVerticalLeft(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> cols) {

        int m = grid.length;

        List<Integer> list = cols.get(need);

        if (list == null) return false;

        // Width > 1 and height > 1:
        // any cell can be deleted.
        if (m > 1 && cut + 1 > 1) {
            for (int col : list) {
                if (col <= cut) {
                    return true;
                }
            }
            return false;
        }

        // Single column
        if (cut + 1 == 1) {
            return grid[0][0] == need ||
                   grid[m - 1][0] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][0] == need ||
                   grid[0][cut] == need;
        }

        return false;
    }

    // Right = columns [cut + 1 ... n-1]
    private boolean canDeleteVerticalRight(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> cols) {

        int m = grid.length;
        int n = grid[0].length;

        int firstCol = cut + 1;
        int lastCol = n - 1;

        List<Integer> list = cols.get(need);

        if (list == null) return false;

        // Width > 1 and height > 1:
        // any cell can be deleted.
        if (m > 1 && firstCol < lastCol) {
            for (int col : list) {
                if (col >= firstCol) {
                    return true;
                }
            }
            return false;
        }

        // Single column
        if (firstCol == lastCol) {
            return grid[0][firstCol] == need ||
                   grid[m - 1][firstCol] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][firstCol] == need ||
                   grid[0][lastCol] == need;
        }

        return false;
    }
}