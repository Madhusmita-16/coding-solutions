class Solution {
    public int goodNodes(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    private int dfs(TreeNode root, int maxValue) {

        if (root == null) {
            return 0;
        }

        int count = 0;

        // Check if current node is good
        if (root.val >= maxValue) {
            count = 1;
        }

        // Update maximum value for this path
        maxValue = Math.max(maxValue, root.val);

        // Check left and right subtrees
        count += dfs(root.left, maxValue);
        count += dfs(root.right, maxValue);

        return count;
    }
}