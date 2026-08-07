class Solution {
    public int count(int n, int m) {
        // dp[x] = number of valid arrays ending with x
        long[] dp = new long[m + 1];

        // Arrays of length 1: [1], [2], ..., [m]
        for (int x = 1; x <= m; x++) {
            dp[x] = 1;
        }

        // Build arrays of length 2 to n
        for (int len = 2; len <= n; len++) {
            long[] next = new long[m + 1];

            for (int x = 1; x <= m; x++) {
                for (int y = 1; y <= m; y++) {

                    // x and y are adjacent and one divides the other
                    if (x % y == 0 || y % x == 0) {
                        next[y] += dp[x];
                    }
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int x = 1; x <= m; x++) {
            ans += dp[x];
        }

        return (int) ans;
    }
}