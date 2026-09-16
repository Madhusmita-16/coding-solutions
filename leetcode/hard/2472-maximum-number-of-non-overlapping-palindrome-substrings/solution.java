class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        // palindrome[i][j] = true if s[i...j] is a palindrome
        boolean[][] palindrome = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) &&
                    (len <= 2 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                }
            }
        }

        // dp[i] = maximum number of valid non-overlapping
        // palindromes in s[0...i-1]
        int[] dp = new int[n + 1];

        for (int end = 1; end <= n; end++) {
            // Don't select a palindrome ending at end - 1
            dp[end] = dp[end - 1];

            for (int start = 0; start < end; start++) {
                if (end - start >= k && palindrome[start][end - 1]) {
                    dp[end] = Math.max(dp[end],
                                       dp[start] + 1);
                }
            }
        }

        return dp[n];
    }
}