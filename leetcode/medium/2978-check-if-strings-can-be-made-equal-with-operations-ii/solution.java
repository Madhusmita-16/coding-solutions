class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        // 0 = even positions, 1 = odd positions
        int[][] freq = new int[2][26];

        for (int i = 0; i < n; i++) {
            int parity = i % 2;

            freq[parity][s1.charAt(i) - 'a']++;
            freq[parity][s2.charAt(i) - 'a']--;
        }

        // All frequencies must be zero
        for (int parity = 0; parity < 2; parity++) {
            for (int c = 0; c < 26; c++) {
                if (freq[parity][c] != 0) {
                    return false;
                }
            }
        }

        return true;
    }
}