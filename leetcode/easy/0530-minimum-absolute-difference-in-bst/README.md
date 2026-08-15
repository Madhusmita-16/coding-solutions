# Minimum Absolute Difference in BST

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given the `root` of a Binary Search Tree (BST), return  *the minimum absolute difference between the values of any two different nodes in the tree*.

 

 **Example 1:** 

```
Input: root = [4,2,6,1,3]
Output: 1

```

 **Example 2:** 

```
Input: root = [1,0,48,null,null,12,49]
Output: 1

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [2, 104].
- 0 <= Node.val <= 105

 

 **Note:**  This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 46.9 MB (beats 53.49%)  
**Submitted:** 2026-08-15T09:11:37.856Z  

```java
class Solution {

    private int previous = -1;
    private int minimum = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {

        inorder(root);

        return minimum;
    }

    private void inorder(TreeNode node) {

        if (node == null) {
            return;
        }

        // Visit left subtree
        inorder(node.left);

        // Compare with previous value
        if (previous != -1) {
            minimum = Math.min(minimum, node.val - previous);
        }

        previous = node.val;

        // Visit right subtree
        inorder(node.right);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-absolute-difference-in-bst/)