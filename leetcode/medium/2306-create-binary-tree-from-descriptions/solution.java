import java.util.*;

class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] d : descriptions) {
            int parent = d[0];
            int child = d[1];
            int isLeft = d[2];

            // Create nodes if they don't exist
            map.putIfAbsent(parent, new TreeNode(parent));
            map.putIfAbsent(child, new TreeNode(child));

            // Connect parent and child
            if (isLeft == 1) {
                map.get(parent).left = map.get(child);
            } else {
                map.get(parent).right = map.get(child);
            }

            // Child cannot be the root
            children.add(child);
        }

        // Find the node that never appeared as a child
        for (int value : map.keySet()) {
            if (!children.contains(value)) {
                return map.get(value);
            }
        }

        return null;
    }
}