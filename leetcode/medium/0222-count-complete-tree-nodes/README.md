# Count Complete Tree Nodes

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a  **complete**  binary tree, return the number of the nodes in the tree.

According to  **Wikipedia**, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between `1` and `2h` nodes inclusive at the last level `h`.

Design an algorithm that runs in less than `O(n)` time complexity.

 

 **Example 1:** 

```
Input: root = [1,2,3,4,5,6]
Output: 6

```

 **Example 2:** 

```
Input: root = []
Output: 0

```

 **Example 3:** 

```
Input: root = [1]
Output: 1

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 5 * 104].
- 0 <= Node.val <= 5 * 104
- The tree is guaranteed to be complete.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.6 MB  
**Submitted:** 2026-08-15T15:10:52.621Z  

```java
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == rightHeight) {
            // Left subtree is perfect
            return (1 << leftHeight) + countNodes(root.right);
        } else {
            // Right subtree is perfect
            return (1 << rightHeight) + countNodes(root.left);
        }
    }

    private int getHeight(TreeNode node) {
        int height = 0;

        while (node != null) {
            height++;
            node = node.left;
        }

        return height;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/count-complete-tree-nodes/)