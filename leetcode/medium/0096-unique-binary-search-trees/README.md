# Unique Binary Search Trees

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer `n`, return  *the number of structurally unique  **BST'** s (binary search trees) which has exactly* `n` *nodes of unique values from*  `1`  *to*  `n`.

 

 **Example 1:** 

```
Input: n = 3
Output: 5

```

 **Example 2:** 

```
Input: n = 1
Output: 1

```

 

 **Constraints:** 

- 1 <= n <= 19

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.1 MB (beats 51.33%)  
**Submitted:** 2026-08-18T15:50:55.027Z  

```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];

        // Empty tree
        dp[0] = 1;

        // Calculate for 1 to n nodes
        for (int nodes = 1; nodes <= n; nodes++) {

            for (int root = 1; root <= nodes; root++) {

                int left = root - 1;
                int right = nodes - root;

                dp[nodes] += dp[left] * dp[right];
            }
        }

        return dp[n];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/unique-binary-search-trees/)