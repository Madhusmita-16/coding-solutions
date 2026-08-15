class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int currentNumber) {
        if (root == null) {
            return 0;
        }

        currentNumber = currentNumber * 10 + root.val;

        // Leaf node
        if (root.left == null && root.right == null) {
            return currentNumber;
        }

        return dfs(root.left, currentNumber)
                + dfs(root.right, currentNumber);
    }
}