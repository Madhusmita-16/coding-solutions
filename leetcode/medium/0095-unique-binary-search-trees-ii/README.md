# Unique Binary Search Trees II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer `n`, return  *all the structurally unique  **BST'** s (binary search trees), which has exactly* `n` *nodes of unique values from*  `1`  *to*  `n`. Return the answer in  **any order**.

 

 **Example 1:** 

```
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

```

 **Example 2:** 

```
Input: n = 1
Output: [[1]]

```

 

 **Constraints:** 

- 1 <= n <= 8

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 99.49%)  
**Memory:** 46.5 MB (beats 20.31%)  
**Submitted:** 2026-08-18T15:48:18.282Z  

```java
class Solution {
    public List<TreeNode> generateTrees(int n) {
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<>();

        // Empty subtree
        if (start > end) {
            result.add(null);
            return result;
        }

        // Try every value as root
        for (int i = start; i <= end; i++) {

            List<TreeNode> leftTrees = buildTrees(start, i - 1);
            List<TreeNode> rightTrees = buildTrees(i + 1, end);

            // Combine every left and right subtree
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {

                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;

                    result.add(root);
                }
            }
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/unique-binary-search-trees-ii/)