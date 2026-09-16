class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        // dp[j] = number of ways to choose j items
        // while processing the available coordinates.
        long[] dp = new long[2 * k + 1];
        dp[0] = 1;

        for (int i = 1; i <= n + k - 1; i++) {
            for (int j = Math.min(2 * k, i); j >= 1; j--) {
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
            }
        }

        return (int) dp[2 * k];
    }
}