class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        // Check if this is a leaf
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }

        // Check left or right subtree
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }
}