class Solution {
    public int findTargetSumWays(int[] nums, int target) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        // Target must be reachable
        if (Math.abs(target) > sum) {
            return 0;
        }

        // sum + target must be even
        if ((sum + target) % 2 != 0) {
            return 0;
        }

        int positiveSum = (sum + target) / 2;

        int[] dp = new int[positiveSum + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = positiveSum; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }

        return dp[positiveSum];
    }
}