# Maximal Square

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an `m x n` binary `matrix` filled with `0`'s and `1`'s,  *find the largest square containing only*  `1`'s  *and return its area*.

 

 **Example 1:** 

```
Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

```

 **Example 2:** 

```
Input: matrix = [["0","1"],["1","0"]]
Output: 1

```

 **Example 3:** 

```
Input: matrix = [["0"]]
Output: 0

```

 

 **Constraints:** 

- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 300
- matrix[i][j] is '0' or '1'.

## Solution

**Language:** Java  
**Runtime:** 8 ms (beats 75.88%)  
**Memory:** 70.7 MB (beats 94.48%)  
**Submitted:** 2026-08-15T06:31:23.916Z  

```java
class Solution {
    public int maximalSquare(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m + 1][n + 1];
        int maxSide = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (matrix[i - 1][j - 1] == '1') {

                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],
                        Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );

                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximal-square/)