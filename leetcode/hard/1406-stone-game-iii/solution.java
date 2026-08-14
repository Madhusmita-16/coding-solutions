class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        // dp[i] = maximum score difference starting from i
        int[] dp = new int[n + 1];

        // If no stones remain, difference is 0
        dp[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            int sum = 0;

            // Take 1, 2, or 3 stones
            dp[i] = Integer.MIN_VALUE;

            for (int j = i; j < Math.min(n, i + 3); j++) {
                sum += stoneValue[j];

                // Current player takes these stones
                // and then the opponent gets dp[j + 1]
                dp[i] = Math.max(
                    dp[i],
                    sum - dp[j + 1]
                );
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}