# Construct Binary Tree from Preorder and Inorder Traversal

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two integer arrays `preorder` and `inorder` where `preorder` is the preorder traversal of a binary tree and `inorder` is the inorder traversal of the same tree, construct and return  *the binary tree*.

 

 **Example 1:** 

```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

```

 **Example 2:** 

```
Input: preorder = [-1], inorder = [-1]
Output: [-1]

```

 

 **Constraints:** 

- 1 <= preorder.length <= 3000
- inorder.length == preorder.length
- -3000 <= preorder[i], inorder[i] <= 3000
- preorder and inorder consist of unique values.
- Each value of inorder also appears in preorder.
- preorder is guaranteed to be the preorder traversal of the tree.
- inorder is guaranteed to be the inorder traversal of the tree.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.7 MB  
**Submitted:** 2026-08-15T15:03:02.524Z  

```java
import java.util.HashMap;

class Solution {

    HashMap<Integer, Integer> map;
    int preorderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();

        // Store inorder value -> index
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }

        // First element in preorder is the root
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int mid = map.get(rootValue);

        // Build left subtree first
        root.left = build(preorder, left, mid - 1);

        // Then build right subtree
        root.right = build(preorder, mid + 1, right);

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)