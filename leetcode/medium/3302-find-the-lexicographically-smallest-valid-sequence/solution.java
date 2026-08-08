class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        /*
         * dp[i] = number of characters that can be matched
         * from the END of word2 using word1[i...n-1].
         */
        int[] dp = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                dp[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int i = 0;
        j = 0;

        /*
         * First phase:
         * Find the lexicographically smallest prefix.
         *
         * We greedily take matching characters.
         * If we encounter a mismatch, we can use our
         * one allowed replacement IF the remaining
         * characters can all be matched.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                // Exact match: always prefer this index.
                ans[j] = i;
                j++;
            } else {
                /*
                 * Use the one allowed mismatch here.
                 *
                 * After using this mismatch, we need to
                 * match word2[j+1 ... m-1].
                 *
                 * Number of characters required:
                 * m - 1 - j
                 */
                if (dp[i + 1] >= m - 1 - j) {
                    ans[j] = i;
                    j++;

                    // Mismatch is now consumed.
                    i++;

                    break;
                }
            }

            i++;
        }

        /*
         * If we couldn't choose all characters, no solution.
         */
        if (j < m && i >= n) {
            return new int[0];
        }

        /*
         * The mismatch has now either been used or was not needed.
         * From here, match the remaining characters exactly.
         */
        while (i < n && j < m) {

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            i++;
        }

        /*
         * If all characters of word2 were matched,
         * ans is valid.
         */
        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}