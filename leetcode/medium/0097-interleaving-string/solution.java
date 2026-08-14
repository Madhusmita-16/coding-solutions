class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {

        int m = s1.length();
        int n = s2.length();

        // Length must match
        if (m + n != s3.length()) {
            return false;
        }

        // dp[j] = whether s1[0..i) and s2[0..j)
        // can form s3[0..i+j)
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        // Initialize using only s2
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1]
                    && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= m; i++) {

            // Using only s1
            dp[0] = dp[0]
                    && s1.charAt(i - 1) == s3.charAt(i - 1);

            for (int j = 1; j <= n; j++) {

                char current = s3.charAt(i + j - 1);

                boolean fromS1 =
                        dp[j]
                        && s1.charAt(i - 1) == current;

                boolean fromS2 =
                        dp[j - 1]
                        && s2.charAt(j - 1) == current;

                dp[j] = fromS1 || fromS2;
            }
        }

        return dp[n];
    }
}