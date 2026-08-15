# Invert Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given the `root` of a binary tree, invert the tree, and return  *its root*.

 

 **Example 1:** 

```
Input: root = [4,2,7,1,3,6,9]
Output: [4,7,2,9,6,3,1]

```

 **Example 2:** 

```
Input: root = [2,1,3]
Output: [2,3,1]

```

 **Example 3:** 

```
Input: root = []
Output: []

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [0, 100].
- -100 <= Node.val <= 100

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.1 MB (beats 28.12%)  
**Submitted:** 2026-08-15T15:00:03.361Z  

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Swap left and right
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // Invert both subtrees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/invert-binary-tree/)