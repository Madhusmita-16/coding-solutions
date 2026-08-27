class Solution {
    public int maxVowels(String s, int k) {

        int count = 0;

        // Count vowels in the first window
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                count++;
            }
        }

        int max = count;

        // Slide the window
        for (int i = k; i < s.length(); i++) {

            // Add the new character
            if (isVowel(s.charAt(i))) {
                count++;
            }

            // Remove the character leaving the window
            if (isVowel(s.charAt(i - k))) {
                count--;
            }

            max = Math.max(max, count);
        }

        return max;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' ||
               c == 'o' || c == 'u';
    }
}