class Solution {
    public int longestSubsequence(int[] nums) {

        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        // If XOR of all elements is non-zero,
        // the entire array is the longest valid subsequence.
        if (xor != 0) {
            return nums.length;
        }

        // If total XOR is zero, remove one non-zero element.
        // The remaining XOR becomes non-zero.
        for (int num : nums) {
            if (num != 0) {
                return nums.length - 1;
            }
        }

        // All elements are zero, so every subsequence has XOR = 0.
        return 0;
    }
}