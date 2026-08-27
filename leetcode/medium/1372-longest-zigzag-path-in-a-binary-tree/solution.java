class Solution {

    private int max = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{-1, -1};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Move left, then must move right
        int goLeft = left[1] + 1;

        // Move right, then must move left
        int goRight = right[0] + 1;

        max = Math.max(max, Math.max(goLeft, goRight));

        return new int[]{goLeft, goRight};
    }
}