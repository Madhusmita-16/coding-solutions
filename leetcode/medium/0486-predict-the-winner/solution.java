class Solution {
    public boolean predictTheWinner(int[] nums) {

        int n = nums.length;

        // dp[i] = best score difference for subarray [i ... j]
        int[] dp = new int[n];

        // Base case: only one number
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }

        // Consider subarrays of increasing length
        for (int len = 2; len <= n; len++) {

            for (int left = 0; left + len <= n; left++) {

                int right = left + len - 1;

                // Choose left number
                int chooseLeft = nums[left] - dp[left + 1];

                // Choose right number
                int chooseRight = nums[right] - dp[left];

                dp[left] = Math.max(chooseLeft, chooseRight);
            }
        }

        // Player 1 wins or ties
        return dp[0] >= 0;
    }
}