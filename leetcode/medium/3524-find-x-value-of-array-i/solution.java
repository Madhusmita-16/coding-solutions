class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        // dp[r] = number of subarrays ending at the previous index
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] next = new long[k];

            int value = num % k;

            // Start a new subarray containing only nums[i]
            next[value]++;

            // Extend every subarray ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRemainder = (int) ((long) r * value % k);
                    next[newRemainder] += dp[r];
                }
            }

            // Every subarray ending here contributes to the answer
            for (int r = 0; r < k; r++) {
                result[r] += next[r];
            }

            dp = next;
        }

        return result;
    }
}