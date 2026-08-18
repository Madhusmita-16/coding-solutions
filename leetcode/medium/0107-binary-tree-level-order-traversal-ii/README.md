# Binary Tree Level Order Traversal II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree, return  *the bottom-up level order traversal of its nodes' values*. (i.e., from left to right, level by level from leaf to root).

 

 **Example 1:** 

```
Input: root = [3,9,20,null,null,15,7]
Output: [[15,7],[9,20],[3]]

```

 **Example 2:** 

```
Input: root = [1]
Output: [[1]]

```

 **Example 3:** 

```
Input: root = []
Output: []

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 2000].
- -1000 <= Node.val <= 1000

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 97.56%)  
**Memory:** 44.3 MB (beats 77.22%)  
**Submitted:** 2026-08-18T15:52:16.281Z  

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Add current level at the beginning
            result.add(0, level);
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/)