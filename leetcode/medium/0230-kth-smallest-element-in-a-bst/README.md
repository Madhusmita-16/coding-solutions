# Kth Smallest Element in a BST

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary search tree, and an integer `k`, return  *the*  `kth`  *smallest value (**1-indexed**) of all the values of the nodes in the tree*.

 

 **Example 1:** 

```
Input: root = [3,1,4,null,2], k = 1
Output: 1

```

 **Example 2:** 

```
Input: root = [5,3,6,2,4,null,null,1], k = 3
Output: 3

```

 

 **Constraints:** 

- The number of nodes in the tree is n.
- 1 <= k <= n <= 104
- 0 <= Node.val <= 104

 

 **Follow up:**  If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 36.77%)  
**Memory:** 47 MB (beats 6.66%)  
**Submitted:** 2026-08-15T09:09:53.347Z  

```java
import java.util.*;

class Solution {
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            // Go to the smallest node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Process node
            current = stack.pop();
            k--;

            // kth smallest found
            if (k == 0) {
                return current.val;
            }

            // Move to right subtree
            current = current.right;
        }

        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)