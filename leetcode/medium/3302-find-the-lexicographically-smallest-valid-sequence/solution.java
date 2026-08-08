import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] = rightmost position in word1 where word2[j]
        // can be matched while matching word2[j...m-1].
        int[] last = new int[m];

        int p = n - 1;

        for (int j = m - 1; j >= 0; j--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }

            if (p < 0) {
                // Even the suffix cannot be matched exactly.
                // But one mismatch may be used for one character.
                // We handle this naturally below.
                last[j] = -1;
            } else {
                last[j] = p;
                p--;
            }
        }

        int[] ans = new int[m];

        int j = 0;
        boolean changed = false;

        for (int i = 0; i < n && j < m; i++) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
                continue;
            }

            // Try using our one allowed modification here.
            if (!changed) {
                boolean possible = false;

                // If this is the last character, we can always
                // change word1[i] to word2[j].
                if (j == m - 1) {
                    possible = true;
                } else {
                    /*
                     * After using the modification at i,
                     * word2[j+1...] must be matched exactly.
                     *
                     * We need its first feasible position
                     * to be after i.
                     */
                    if (last[j + 1] > i) {
                        possible = true;
                    }
                }

                if (possible) {
                    ans[j] = i;
                    j++;
                    changed = true;
                }
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}