class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int len = n + m - 1;

        char[] word = new char[len];
        boolean[] fixed = new boolean[len];

        // Step 1: Process all 'T' constraints.
        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {

                for (int j = 0; j < m; j++) {
                    int pos = i + j;

                    // Conflict with another T constraint
                    if (word[pos] != '\0'
                            && word[pos] != str2.charAt(j)) {
                        return "";
                    }

                    word[pos] = str2.charAt(j);
                    fixed[pos] = true;
                }
            }
        }

        // Step 2: Fill all unfixed positions with 'a'
        // to obtain the lexicographically smallest string.
        for (int i = 0; i < len; i++) {
            if (word[i] == '\0') {
                word[i] = 'a';
            }
        }

        // Step 3: Process all 'F' constraints.
        for (int i = 0; i < n; i++) {

            if (str1.charAt(i) != 'F') {
                continue;
            }

            // Check whether word[i ... i + m - 1] == str2
            boolean same = true;

            for (int j = 0; j < m; j++) {
                if (word[i + j] != str2.charAt(j)) {
                    same = false;
                    break;
                }
            }

            // Already different -> F condition is satisfied.
            if (!same) {
                continue;
            }

            /*
             * The substring is equal to str2,
             * so we must change one unfixed character.
             *
             * To keep the result lexicographically smallest,
             * change the RIGHTMOST unfixed character.
             */
            int pos = -1;

            for (int j = m - 1; j >= 0; j--) {
                int index = i + j;

                if (!fixed[index]) {
                    pos = index;
                    break;
                }
            }

            // No position can be changed.
            if (pos == -1) {
                return "";
            }

            // Current character is 'a', so changing to 'b'
            // is the smallest possible change.
            word[pos] = 'b';
        }

        return new String(word);
    }
}