import java.util.*;

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        
        Map<Long, Integer> map = new HashMap<>();
        
        // Empty path has sum 0
        map.put(0L, 1);
        
        return dfs(root, 0L, targetSum, map);
    }

    private int dfs(TreeNode root, long currentSum, int targetSum,
                    Map<Long, Integer> map) {

        if (root == null) {
            return 0;
        }

        currentSum += root.val;

        // Number of paths ending at current node
        // whose sum is targetSum
        int count = map.getOrDefault(
            currentSum - targetSum, 0
        );

        // Add current prefix sum
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        // Search left and right
        count += dfs(root.left, currentSum, targetSum, map);
        count += dfs(root.right, currentSum, targetSum, map);

        // Backtrack: remove current path's prefix sum
        map.put(currentSum, map.get(currentSum) - 1);

        return count;
    }
}