# Q3. Maximum Product of Splitted Binary Tree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.

Return  *the maximum product of the sums of the two subtrees*. Since the answer may be too large, return it  **modulo**  `109 + 7`.

 **Note**  that you need to maximize the answer before taking the mod and not after taking it.

 

 **Example 1:** 

```
Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)

```

 **Example 2:** 

```
Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [2, 5 * 104].
- 1 <= Node.val <= 104

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 89.47%)  
**Memory:** 62.5 MB (beats 88.89%)  
**Submitted:** 2026-08-27T09:17:18.434Z  

```java
import java.util.*;

class Solution {

    private static final long MOD = 1_000_000_007L;
    private long totalSum = 0;
    private long maxProduct = 0;

    public int maxProduct(TreeNode root) {

        // First calculate total sum of the tree
        totalSum = getTotalSum(root);

        // Find the best subtree sum
        findMaxProduct(root);

        return (int) (maxProduct % MOD);
    }

    // Calculate total sum of the tree
    private long getTotalSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return root.val
                + getTotalSum(root.left)
                + getTotalSum(root.right);
    }

    // Calculate every subtree sum
    private long findMaxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }

        long leftSum = findMaxProduct(root.left);
        long rightSum = findMaxProduct(root.right);

        long subtreeSum = root.val + leftSum + rightSum;

        // Removing the edge above this subtree:
        // one part = subtreeSum
        // other part = totalSum - subtreeSum
        long product = subtreeSum * (totalSum - subtreeSum);

        maxProduct = Math.max(maxProduct, product);

        return subtreeSum;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/)