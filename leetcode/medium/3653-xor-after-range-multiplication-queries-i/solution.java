class Solution {
    public int xorAfterQueries(int[] nums, int[][] queries) {
        final long MOD = 1_000_000_007L;

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            long v = q[3];

            for (int i = l; i <= r; i += k) {
                nums[i] = (int) ((nums[i] * v) % MOD);
            }
        }

        int ans = 0;

        for (int num : nums) {
            ans ^= num;
        }

        return ans;
    }
}