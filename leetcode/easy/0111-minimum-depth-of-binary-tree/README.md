# Minimum Depth of Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 **Note:**  A leaf is a node with no children.

 

 **Example 1:** 

```
Input: root = [3,9,20,null,null,15,7]
Output: 2

```

 **Example 2:** 

```
Input: root = [2,null,3,null,4,null,5,null,6]
Output: 5

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 105].
- -1000 <= Node.val <= 1000

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 95.15%)  
**Memory:** 82.2 MB (beats 13.12%)  
**Submitted:** 2026-09-08T05:33:44.880Z  

```java
class Solution {
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();

                // First leaf found = minimum depth
                if (node.left == null && node.right == null) {
                    return depth;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            depth++;
        }

        return depth;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-depth-of-binary-tree/)