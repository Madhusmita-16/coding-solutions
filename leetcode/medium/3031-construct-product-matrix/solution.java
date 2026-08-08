class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 12345;

        int[][] result = new int[m][n];

        // Store suffix products.
        long suffix = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                result[i][j] = (int) suffix;

                suffix = (suffix * grid[i][j]) % MOD;
            }
        }

        // Multiply by prefix products.
        long prefix = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Save suffix before modifying result.
                long suffixValue = result[i][j];

                // Product of everything before and after current cell.
                result[i][j] = (int) ((prefix * suffixValue) % MOD);

                // Update prefix AFTER calculating current answer.
                prefix = (prefix * grid[i][j]) % MOD;
            }
        }

        return result;
    }
}