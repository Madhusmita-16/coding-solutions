import java.util.*;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int n = s.length();

        Set<String> dict = new HashSet<>(wordDict);

        // dp[i] = true if s[0...i-1] can be segmented
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (int len = 1; len <= 20 && len <= i; len++) {

                if (dp[i - len] &&
                    dict.contains(s.substring(i - len, i))) {

                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}