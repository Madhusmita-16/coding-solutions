class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";

        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                initialOnes++;
            }
        }

        // Run lengths of t.
        int len = t.length();
        int[] runs = new int[len];
        char[] chars = new char[len];

        int m = 0;
        int i = 0;

        while (i < len) {
            char c = t.charAt(i);
            int j = i;

            while (j < len && t.charAt(j) == c) {
                j++;
            }

            chars[m] = c;
            runs[m] = j - i;
            m++;

            i = j;
        }

        int bestGain = 0;

        /*
         * Pattern:
         *
         *     0-block | 1-block | 0-block
         *
         * Remove the middle 1-block.
         * The two zero blocks merge and become 1s.
         *
         * Gain = leftZero + rightZero
         */
        for (i = 1; i < m - 1; i++) {
            if (chars[i] == '1'
                    && chars[i - 1] == '0'
                    && chars[i + 1] == '0') {

                int gain = runs[i - 1] + runs[i + 1];

                bestGain = Math.max(bestGain, gain);
            }
        }

        return initialOnes + bestGain;
    }
}