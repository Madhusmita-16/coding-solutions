import java.util.*;

class Solution {
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            // Go to the smallest node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process node
            current = stack.pop();
            k--;

            // kth smallest found
            if (k == 0) {
                return current.val;
            }

            // Move to right subtree
            current = current.right;
        }

        return -1;
    }
}