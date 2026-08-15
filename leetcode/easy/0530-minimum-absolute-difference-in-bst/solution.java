class Solution {

    private int previous = -1;
    private int minimum = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {

        inorder(root);

        return minimum;
    }

    private void inorder(TreeNode node) {

        if (node == null) {
            return;
        }

        // Visit left subtree
        inorder(node.left);

        // Compare with previous value
        if (previous != -1) {
            minimum = Math.min(minimum, node.val - previous);
        }

        previous = node.val;

        // Visit right subtree
        inorder(node.right);
    }
}