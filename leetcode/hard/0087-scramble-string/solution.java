class Solution {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        if (s1.equals(s2)) {
            return true;
        }

        // dp[len][i][j]
        boolean[][][] dp = new boolean[n + 1][n][n];

        // Length 1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }

        // Build for increasing lengths
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++) {

                    // Try every possible split
                    for (int k = 1; k < len; k++) {

                        // Case 1: No swap
                        boolean noSwap =
                            dp[k][i][j] &&
                            dp[len - k][i + k][j + k];

                        // Case 2: Swap
                        boolean swap =
                            dp[k][i][j + len - k] &&
                            dp[len - k][i + k][j];

                        if (noSwap || swap) {
                            dp[len][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[n][0][0];
    }
}