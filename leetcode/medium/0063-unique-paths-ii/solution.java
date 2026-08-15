class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];

        // Starting point
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Obstacle blocks all paths
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } 
                else if (j > 0) {
                    // Paths from top + paths from left
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n - 1];
    }
}