class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        // Key not found
        if (root == null) {
            return null;
        }

        // Search in left subtree
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        // Search in right subtree
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        // Node found
        else {
            // Case 1: No left child
            if (root.left == null) {
                return root.right;
            }

            // Case 2: No right child
            if (root.right == null) {
                return root.left;
            }

            // Case 3: Two children
            // Find the smallest value in the right subtree
            TreeNode successor = root.right;

            while (successor.left != null) {
                successor = successor.left;
            }

            // Replace current value with successor value
            root.val = successor.val;

            // Delete the successor node
            root.right = deleteNode(root.right, successor.val);
        }

        return root;
    }
}