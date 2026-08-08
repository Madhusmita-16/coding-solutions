import java.util.*;

class Solution {
    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        int NEG = Integer.MIN_VALUE / 4;

        // dp[i][j][k] = maximum amount at (i,j)
        // using at most k neutralizations
        int[][][] dp = new int[m][n][3];

        // Initialize all states
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], NEG);
            }
        }

        // Starting cell
        for (int k = 0; k <= 2; k++) {
            dp[0][0][k] = coins[0][0];

            // We can neutralize a negative starting cell
            if (coins[0][0] < 0 && k > 0) {
                dp[0][0][k] = 0;
            }
        }

        // DP
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                for (int k = 0; k <= 2; k++) {

                    // Option 1: Don't neutralize current cell
                    int best = NEG;

                    if (i > 0) {
                        best = Math.max(best, dp[i - 1][j][k]);
                    }

                    if (j > 0) {
                        best = Math.max(best, dp[i][j - 1][k]);
                    }

                    if (best != NEG) {
                        dp[i][j][k] = best + coins[i][j];
                    }

                    // Option 2: Neutralize current negative cell
                    if (coins[i][j] < 0 && k > 0) {

                        int neutralized = NEG;

                        if (i > 0) {
                            neutralized = Math.max(
                                neutralized,
                                dp[i - 1][j][k - 1]
                            );
                        }

                        if (j > 0) {
                            neutralized = Math.max(
                                neutralized,
                                dp[i][j - 1][k - 1]
                            );
                        }

                        dp[i][j][k] = Math.max(
                            dp[i][j][k],
                            neutralized
                        );
                    }
                }
            }
        }

        return dp[m - 1][n - 1][2];
    }
}