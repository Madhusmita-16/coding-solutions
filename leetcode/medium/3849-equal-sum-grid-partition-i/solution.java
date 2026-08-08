class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long total = 0;

        // Calculate total sum
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        // Horizontal partition
        long rowSum = 0;

        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n; j++) {
                rowSum += grid[i][j];
            }

            if (rowSum * 2 == total) {
                return true;
            }
        }

        // Vertical partition
        long colSum = 0;

        for (int j = 0; j < n - 1; j++) {
            for (int i = 0; i < m; i++) {
                colSum += grid[i][j];
            }

            if (colSum * 2 == total) {
                return true;
            }
        }

        return false;
    }
}