# Recover Binary Search Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given the `root` of a binary search tree (BST), where the values of  **exactly**  two nodes of the tree were swapped by mistake.  *Recover the tree without changing its structure*.

 

 **Example 1:** 

```
Input: root = [1,3,null,null,2]
Output: [3,1,null,null,2]
Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.

```

 **Example 2:** 

```
Input: root = [3,1,4,null,null,2]
Output: [2,1,4,null,null,3]
Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [2, 1000].
- -231 <= Node.val <= 231 - 1

 

 **Follow up:**  A solution using `O(n)` space is pretty straight-forward. Could you devise a constant `O(1)` space solution?

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 100.00%)  
**Memory:** 46.3 MB (beats 87.70%)  
**Submitted:** 2026-08-18T15:43:50.722Z  

```java
class Solution {
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inorder(root);

        // Swap the incorrect values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);

        // Detect incorrect ordering
        if (prev != null && prev.val > root.val) {

            if (first == null) {
                first = prev;
            }

            second = root;
        }

        prev = root;

        inorder(root.right);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/recover-binary-search-tree/)