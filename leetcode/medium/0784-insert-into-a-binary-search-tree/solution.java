class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If tree is empty, new node becomes the root
        if (root == null) {
            return new TreeNode(val);
        }

        // Insert into the left subtree
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        // Insert into the right subtree
        else {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }
}