class Solution {

    private int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return maxPath;
    }

    private int dfs(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftPath = 0;
        int rightPath = 0;

        // Extend path through the left child
        if (node.left != null && node.left.val == node.val) {
            leftPath = left + 1;
        }

        // Extend path through the right child
        if (node.right != null && node.right.val == node.val) {
            rightPath = right + 1;
        }

        // Path passing through the current node
        maxPath = Math.max(maxPath, leftPath + rightPath);

        // Return the longest single-side path
        return Math.max(leftPath, rightPath);
    }
}