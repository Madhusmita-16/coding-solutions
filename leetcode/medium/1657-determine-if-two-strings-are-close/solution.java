class Solution {
    public boolean closeStrings(String word1, String word2) {

        // Different lengths -> impossible
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // Count characters
        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }

        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }

        // Both strings must contain exactly the same characters
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0) != (freq2[i] == 0)) {
                return false;
            }
        }

        // Sort the frequencies
        java.util.Arrays.sort(freq1);
        java.util.Arrays.sort(freq2);

        // Frequency multisets must be identical
        return java.util.Arrays.equals(freq1, freq2);
    }
}