class Solution {
    public int lengthOfLongestSubstring(String s) {

        int[] lastSeen = new int[128];
        java.util.Arrays.fill(lastSeen, -1);

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // Move left if this character is already in the window
            if (lastSeen[ch] >= left) {
                left = lastSeen[ch] + 1;
            }

            lastSeen[ch] = right;

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}