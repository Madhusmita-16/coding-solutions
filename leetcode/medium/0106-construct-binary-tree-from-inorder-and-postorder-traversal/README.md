# Construct Binary Tree from Inorder and Postorder Traversal

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two integer arrays `inorder` and `postorder` where `inorder` is the inorder traversal of a binary tree and `postorder` is the postorder traversal of the same tree, construct and return  *the binary tree*.

 

 **Example 1:** 

```
Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
Output: [3,9,20,null,null,15,7]

```

 **Example 2:** 

```
Input: inorder = [-1], postorder = [-1]
Output: [-1]

```

 

 **Constraints:** 

- 1 <= inorder.length <= 3000
- postorder.length == inorder.length
- -3000 <= inorder[i], postorder[i] <= 3000
- inorder and postorder consist of unique values.
- Each value of postorder also appears in inorder.
- inorder is guaranteed to be the inorder traversal of the tree.
- postorder is guaranteed to be the postorder traversal of the tree.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 99.10%)  
**Memory:** 46.6 MB (beats 9.52%)  
**Submitted:** 2026-08-15T14:55:53.182Z  

```java
import java.util.HashMap;

class Solution {

    private int postIndex;
    private HashMap<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        inorderMap = new HashMap<>();

        // Store value -> index in inorder
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        postIndex = postorder.length - 1;

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {

        if (left > right) {
            return null;
        }

        // Last element in postorder is the root
        int rootValue = postorder[postIndex--];

        TreeNode root = new TreeNode(rootValue);

        int rootIndex = inorderMap.get(rootValue);

        // IMPORTANT:
        // Build right subtree first because we are
        // traversing postorder from right to left.
        root.right = build(postorder, rootIndex + 1, right);

        root.left = build(postorder, left, rootIndex - 1);

        return root;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)