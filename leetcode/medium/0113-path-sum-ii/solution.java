class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        dfs(root, targetSum, path, result);

        return result;
    }

    private void dfs(TreeNode node, int remaining,
                     List<Integer> path,
                     List<List<Integer>> result) {

        if (node == null) {
            return;
        }

        // Add current node to path
        path.add(node.val);

        // Check if current node is a leaf
        if (node.left == null && node.right == null) {

            if (remaining == node.val) {
                result.add(new ArrayList<>(path));
            }

        } else {

            // Explore left subtree
            dfs(node.left, remaining - node.val, path, result);

            // Explore right subtree
            dfs(node.right, remaining - node.val, path, result);
        }

        // Backtrack
        path.remove(path.size() - 1);
    }
}