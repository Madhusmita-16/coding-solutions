class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Ignore negative paths
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        // Path passing through current node
        int currentPath = root.val + left + right;

        // Update global maximum
        maxSum = Math.max(maxSum, currentPath);

        // Return the best one-sided path to parent
        return root.val + Math.max(left, right);
    }
}