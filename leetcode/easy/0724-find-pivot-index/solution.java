class Solution {
    public int pivotIndex(int[] nums) {

        int totalSum = 0;

        // Calculate total sum
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {

            // Right sum = total - left - current
            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }

            // Add current element to left sum
            leftSum += nums[i];
        }

        return -1;
    }
}