class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;

        // dp[i] = maximum score difference (current player - opponent)
        // for the interval starting at i with the current length.
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = piles[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                // Take left or take right
                dp[i] = Math.max(
                    piles[i] - dp[i + 1],
                    piles[j] - dp[i]
                );
            }
        }

        return dp[0] > 0;
    }
}