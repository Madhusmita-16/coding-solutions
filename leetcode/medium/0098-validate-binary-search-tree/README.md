# Validate Binary Search Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree,  *determine if it is a valid binary search tree (BST)*.

A  **valid BST**  is defined as follows:

- The left subtree of a node contains only nodes with keys strictly less than the node's key.
- The right subtree of a node contains only nodes with keys strictly greater than the node's key.
- Both the left and right subtrees must also be binary search trees.

 

 **Example 1:** 

```
Input: root = [2,1,3]
Output: true

```

 **Example 2:** 

```
Input: root = [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [1, 104].
- -231 <= Node.val <= 231 - 1

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 45.2 MB (beats 35.09%)  
**Submitted:** 2026-08-15T09:07:23.141Z  

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {

        if (node == null) {
            return true;
        }

        // Node value must be strictly between min and max
        if (node.val <= min || node.val >= max) {
            return false;
        }

        // Left subtree: values must be smaller than node.val
        // Right subtree: values must be greater than node.val
        return validate(node.left, min, node.val) &&
               validate(node.right, node.val, max);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/validate-binary-search-tree/)