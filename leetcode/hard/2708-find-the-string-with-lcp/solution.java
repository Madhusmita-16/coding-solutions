class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] word = new char[n];

        char current = 'a';

        // Greedy construction
        for (int i = 0; i < n; i++) {

            // Already assigned
            if (word[i] != '\0') {
                continue;
            }

            // We need another character
            if (current > 'z') {
                return "";
            }

            word[i] = current;

            // Every position j with lcp[i][j] > 0
            // must have the same character.
            for (int j = i + 1; j < n; j++) {
                if (lcp[i][j] > 0) {
                    word[j] = current;
                }
            }

            current++;
        }

        // Verify the entire LCP matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int expected;

                if (word[i] != word[j]) {
                    expected = 0;
                } else {
                    if (i == n - 1 || j == n - 1) {
                        expected = 1;
                    } else {
                        expected = lcp[i + 1][j + 1] + 1;
                    }
                }

                if (lcp[i][j] != expected) {
                    return "";
                }
            }
        }

        return new String(word);
    }
}