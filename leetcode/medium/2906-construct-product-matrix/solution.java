class Solution {

    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 12345;

        // Step 1: Store suffix products directly in grid
        long suffix = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                int current = grid[i][j];

                // Store product of all elements after current
                grid[i][j] = (int) suffix;

                // Update suffix for next element
                suffix = (suffix * current) % MOD;
            }
        }

        // Step 2: Multiply by prefix product
        long prefix = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int currentSuffix = grid[i][j];

                // grid[i][j] = prefix * suffix
                grid[i][j] = (int) ((prefix * currentSuffix) % MOD);

                // We need the original value here.
                // It is no longer available, so we cannot update
                // prefix from grid directly.
            }
        }

        return grid;
    }
}