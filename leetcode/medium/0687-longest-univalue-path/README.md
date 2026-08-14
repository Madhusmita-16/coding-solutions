# Q3. Longest Univalue Path

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree, return  *the length of the longest path, where each node in the path has the same value*. This path may or may not pass through the root.

 **The length of the path**  between two nodes is represented by the number of edges between them.

 

 **Example 1:** 

```
Input: root = [5,4,5,1,1,null,5]
Output: 2
Explanation: The shown image shows that the longest path of the same value (i.e. 5).

```

 **Example 2:** 

```
Input: root = [1,4,5,4,4,null,5]
Output: 2
Explanation: The shown image shows that the longest path of the same value (i.e. 4).

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 104].
- -1000 <= Node.val <= 1000
- The depth of the tree will not exceed 1000.

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 98.46%)  
**Memory:** 50.4 MB (beats 10.48%)  
**Submitted:** 2026-08-14T17:28:49.758Z  

```java
class Solution {

    private int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);
        return maxPath;
    }

    private int dfs(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        int leftPath = 0;
        int rightPath = 0;

        // Extend path through the left child
        if (node.left != null && node.left.val == node.val) {
            leftPath = left + 1;
        }

        // Extend path through the right child
        if (node.right != null && node.right.val == node.val) {
            rightPath = right + 1;
        }

        // Path passing through the current node
        maxPath = Math.max(maxPath, leftPath + rightPath);

        // Return the longest single-side path
        return Math.max(leftPath, rightPath);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-univalue-path/)