class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currentMax = 0;
        int maxSum = nums[0];

        int currentMin = 0;
        int minSum = nums[0];

        for (int num : nums) {

            // Maximum subarray
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            // Minimum subarray
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);

            totalSum += num;
        }

        // All numbers are negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Either normal subarray or circular subarray
        return Math.max(maxSum, totalSum - minSum);
    }
}