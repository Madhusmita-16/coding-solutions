class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        int n = s.length();

        // dp[i] = true if s[0...i-1] can be segmented
        boolean[] dp = new boolean[n + 1];

        dp[0] = true;

        for (int i = 1; i <= n; i++) {

            for (String word : wordDict) {

                int len = word.length();

                if (len <= i && dp[i - len]
                        && s.substring(i - len, i).equals(word)) {

                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}