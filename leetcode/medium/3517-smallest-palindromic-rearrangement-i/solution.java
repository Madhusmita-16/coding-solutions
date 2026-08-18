class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();

        // Build the smallest possible left half
        for (int i = 0; i < 26; i++) {
            int count = freq[i] / 2;

            while (count-- > 0) {
                left.append((char) ('a' + i));
            }
        }

        // Find middle character for odd-length palindrome
        char middle = 0;

        if (s.length() % 2 == 1) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    middle = (char) ('a' + i);
                    break;
                }
            }
        }

        // Right half is reverse of left half
        String right = new StringBuilder(left).reverse().toString();

        return left.toString()
                + (middle == 0 ? "" : String.valueOf(middle))
                + right;
    }
}
