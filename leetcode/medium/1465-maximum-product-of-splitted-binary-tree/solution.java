import java.util.*;

class Solution {

    private static final long MOD = 1_000_000_007L;
    private long totalSum = 0;
    private long maxProduct = 0;

    public int maxProduct(TreeNode root) {

        // First calculate total sum of the tree
        totalSum = getTotalSum(root);

        // Find the best subtree sum
        findMaxProduct(root);

        return (int) (maxProduct % MOD);
    }

    // Calculate total sum of the tree
    private long getTotalSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return root.val
                + getTotalSum(root.left)
                + getTotalSum(root.right);
    }

    // Calculate every subtree sum
    private long findMaxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }

        long leftSum = findMaxProduct(root.left);
        long rightSum = findMaxProduct(root.right);

        long subtreeSum = root.val + leftSum + rightSum;

        // Removing the edge above this subtree:
        // one part = subtreeSum
        // other part = totalSum - subtreeSum
        long product = subtreeSum * (totalSum - subtreeSum);

        maxProduct = Math.max(maxProduct, product);

        return subtreeSum;
    }
}