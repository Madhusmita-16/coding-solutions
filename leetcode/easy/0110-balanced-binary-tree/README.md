# Balanced Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a binary tree, determine if it is  **height-balanced**.

 

 **Example 1:** 

```
Input: root = [3,9,20,null,null,15,7]
Output: true

```

 **Example 2:** 

```
Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

```

 **Example 3:** 

```
Input: root = []
Output: true

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 5000].
- -104 <= Node.val <= 104

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 45.4 MB (beats 89.08%)  
**Submitted:** 2026-09-08T05:32:24.809Z  

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode root) {
        
        // Empty tree is balanced
        if (root == null) {
            return 0;
        }

        // Height of left subtree
        int left = height(root.left);

        // Left subtree is unbalanced
        if (left == -1) {
            return -1;
        }

        // Height of right subtree
        int right = height(root.right);

        // Right subtree is unbalanced
        if (right == -1) {
            return -1;
        }

        // Current node is unbalanced
        if (Math.abs(left - right) > 1) {
            return -1;
        }

        // Return height of current subtree
        return Math.max(left, right) + 1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/balanced-binary-tree/)