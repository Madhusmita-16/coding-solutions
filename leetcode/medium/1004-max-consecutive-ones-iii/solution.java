class Solution {
    public int longestOnes(int[] nums, int k) {

        int left = 0;
        int zeros = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            // Add the new element
            if (nums[right] == 0) {
                zeros++;
            }

            // Too many zeros, shrink from the left
            while (zeros > k) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }

            // Current window contains at most k zeros
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}