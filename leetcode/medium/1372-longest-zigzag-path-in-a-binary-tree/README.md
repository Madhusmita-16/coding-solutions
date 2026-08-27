# Longest ZigZag Path in a Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given the `root` of a binary tree.

A ZigZag path for a binary tree is defined as follow:

- Choose any node in the binary tree and a direction (right or left).
- If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
- Change the direction from right to left or from left to right.
- Repeat the second and third steps until you can't move in the tree.

Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return  *the longest  **ZigZag**  path contained in that tree*.

 

 **Example 1:** 

```
Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).

```

 **Example 2:** 

```
Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

```

 **Example 3:** 

```
Input: root = [1]
Output: 0

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [1, 5 * 104].
- 1 <= Node.val <= 100

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 63.45%)  
**Memory:** 63.1 MB (beats 7.39%)  
**Submitted:** 2026-08-27T08:46:01.351Z  

```java
class Solution {

    private int max = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root);
        return max;
    }

    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{-1, -1};
        }

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Move left, then must move right
        int goLeft = left[1] + 1;

        // Move right, then must move left
        int goRight = right[0] + 1;

        max = Math.max(max, Math.max(goLeft, goRight));

        return new int[]{goLeft, goRight};
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/)