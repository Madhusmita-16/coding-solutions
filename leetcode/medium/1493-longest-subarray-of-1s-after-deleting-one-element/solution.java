class Solution {
    public int longestSubarray(int[] nums) {

        int left = 0;
        int zeros = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0) {
                zeros++;
            }

            // We can have at most one zero because
            // we are allowed to delete only one element.
            while (zeros > 1) {
                if (nums[left] == 0) {
                    zeros--;
                }
                left++;
            }

            // Subtract 1 because one element must be deleted.
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }
}