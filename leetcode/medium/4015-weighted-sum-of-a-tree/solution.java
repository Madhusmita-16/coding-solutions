class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;

        int[] depth = new int[n];
        depth[0] = 1;

        int height = 1;

        // Calculate depth of every node
        for (int i = 1; i < n; i++) {
            depth[i] = depth[parent[i]] + 1;
            height = Math.max(height, depth[i]);
        }

        long sum = 0;

        // Calculate weighted sum
        for (int i = 0; i < n; i++) {
            sum += (long) nums[i] * (height - depth[i] + 1);
        }

        return sum;
    }
}