# Leaf-Similar Trees

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a  **leaf value sequence**  *.* 

For example, in the given tree above, the leaf value sequence is `(6, 7, 4, 9, 8)`.

Two binary trees are considered  *leaf-similar*  if their leaf value sequence is the same.

Return `true` if and only if the two given trees with head nodes `root1` and `root2` are leaf-similar.

 

 **Example 1:** 

```
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

```

 **Example 2:** 

```
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false

```

 

 **Constraints:** 

- The number of nodes in each tree will be in the range [1, 200].
- Both of the given trees will have values in the range [0, 200].

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.2 MB (beats 52.37%)  
**Submitted:** 2026-08-27T08:42:15.662Z  

```java
import java.util.*;

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        getLeaves(root1, leaves1);
        getLeaves(root2, leaves2);

        return leaves1.equals(leaves2);
    }

    private void getLeaves(TreeNode root, List<Integer> leaves) {

        if (root == null) {
            return;
        }

        // If it is a leaf node
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }

        // Left first, then right
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/leaf-similar-trees/)