class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];

        // Count frequency of each number
        for (int num : nums) {
            count[num]++;
        }

        // Convert frequency into prefix count
        // count[i] = number of elements <= i
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }

        int[] ans = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                ans[i] = 0;
            } else {
                // Number of elements smaller than nums[i]
                ans[i] = count[nums[i] - 1];
            }
        }

        return ans;
    }
}