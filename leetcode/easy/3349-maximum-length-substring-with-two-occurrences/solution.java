class Solution {
    public int maximumLengthSubstring(String s) {

        int[] count = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);
            count[ch - 'a']++;

            // If any character occurs more than twice,
            // shrink the window from the left.
            while (count[ch - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            // Current window is valid
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}