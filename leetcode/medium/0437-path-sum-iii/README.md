# Path Sum III

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree and an integer `targetSum`, return  *the number of paths where the sum of the values along the path equals*  `targetSum`.

The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only from parent nodes to child nodes).

 

 **Example 1:** 

```
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.

```

 **Example 2:** 

```
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 1000].
- -109 <= Node.val <= 109
- -1000 <= targetSum <= 1000

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 97.84%)  
**Memory:** 46.4 MB (beats 26.09%)  
**Submitted:** 2026-08-27T08:44:49.148Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/path-sum-iii/)