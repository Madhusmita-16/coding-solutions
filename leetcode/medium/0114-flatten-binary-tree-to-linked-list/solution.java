class Solution {
    public void flatten(TreeNode root) {
        TreeNode current = root;

        while (current != null) {

            if (current.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode predecessor = current.left;

                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // Connect right subtree after the left subtree
                predecessor.right = current.right;

                // Move left subtree to the right
                current.right = current.left;
                current.left = null;
            }

            // Move to next node
            current = current.right;
        }
    }
}