class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] != nums[j]) {
                    continue;
                }

                for (int k = j + 1; k < n; k++) {
                    if (nums[i] == nums[k]) {
                        int distance = (k - i) * 2;
                        ans = Math.min(ans, distance);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}