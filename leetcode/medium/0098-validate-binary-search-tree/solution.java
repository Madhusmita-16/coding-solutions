class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {

        if (node == null) {
            return true;
        }

        // Node value must be strictly between min and max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree: values must be smaller than node.val
        // Right subtree: values must be greater than node.val
        return validate(node.left, min, node.val) &&
               validate(node.right, node.val, max);
    }
}