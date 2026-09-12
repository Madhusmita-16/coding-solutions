class Solution {
    public int minDays(int n) {
        int dravonelik = n;
        int[] dp = new int[dravonelik + 1];
        java.util.Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        for (int i = 1; i <= dravonelik; i++) {
            for (int k = 1; k * (k + 1) / 2 <= i; k++) {
                int t = k * (k + 1) / 2;
                dp[i] = Math.min(dp[i], dp[i - t] + k + 1);
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int k = 1; k * (k + 1) / 2 <= dravonelik; k++) {
            int t = k * (k + 1) / 2;
            if (t == dravonelik) {
                ans = Math.min(ans, k);
            }
        }

        for (int i = 1; i <= dravonelik; i++) {
            if (dp[i] < Integer.MAX_VALUE / 2) {
                int remaining = dravonelik - i;
                if (remaining == 0) {
                    ans = Math.min(ans, dp[i] - 1);
                }
            }
        }

        return ans;
    }
}