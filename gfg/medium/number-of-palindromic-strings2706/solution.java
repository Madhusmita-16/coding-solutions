class Solution {
    public int palindromicStrings(int n, int k) {
        final long MOD = 1000000007L;

        long ans = 0;
        long ways = 1;

        for (int half = 0; 2 * half <= n; half++) {

            if (half > 0) {
                ways = (ways * (k - half + 1)) % MOD;
            }

            // Even length
            if (half > 0) {
                ans = (ans + ways) % MOD;
            }

            // Odd length
            if (2 * half + 1 <= n) {
                ans = (ans + ways * (k - half)) % MOD;
            }
        }

        return (int) ans;
    }
}