# Minimum Absolute Difference in Sliding Submatrix

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an `m x n` integer matrix `grid` and an integer `k`.

For every contiguous `k x k`  **submatrix**  of `grid`, compute the  **minimum absolute**  difference between any two  **distinct**  values within that  **submatrix**.

Return a 2D array `ans` of size `(m - k + 1) x (n - k + 1)`, where `ans[i][j]` is the minimum absolute difference in the submatrix whose top-left corner is `(i, j)` in `grid`.

 **Note** : If all elements in the submatrix have the same value, the answer will be 0.

A submatrix `(x1, y1, x2, y2)` is a matrix that is formed by choosing all cells `matrix[x][y]` where `x1 <= x <= x2` and `y1 <= y <= y2`.

 

 **Example 1:** 

 **Input:**  grid = [[1,8],[3,-2]], k = 2

 **Output:**  [[2]]

 **Explanation:** 

- There is only one possible k x k submatrix: [[1, 8], [3, -2]].
- Distinct values in the submatrix are [1, 8, 3, -2].
- The minimum absolute difference in the submatrix is |1 - 3| = 2. Thus, the answer is [[2]].

 **Example 2:** 

 **Input:**  grid = [[3,-1]], k = 1

 **Output:**  [[0,0]]

 **Explanation:** 

- Both k x k submatrix has only one distinct element.
- Thus, the answer is [[0, 0]].

 **Example 3:** 

 **Input:**  grid = [[1,-2,3],[2,3,5]], k = 2

 **Output:**  [[1,2]]

 **Explanation:** 

- There are two possible k × k submatrix: Starting at (0, 0): [[1, -2], [2, 3]]. Distinct values in the submatrix are [1, -2, 2, 3]. The minimum absolute difference in the submatrix is |1 - 2| = 1. Starting at (0, 1): [[-2, 3], [3, 5]]. Distinct values in the submatrix are [-2, 3, 5]. The minimum absolute difference in the submatrix is |3 - 5| = 2.
- Thus, the answer is [[1, 2]].

 

 **Constraints:** 

- 1 <= m == grid.length <= 30
- 1 <= n == grid[i].length <= 30
- -105 <= grid[i][j] <= 105
- 1 <= k <= min(m, n)

## Solution

**Language:** Java  
**Runtime:** 11 ms (beats 96.15%)  
**Memory:** 48.2 MB (beats 7.34%)  
**Submitted:** 2026-08-11T15:19:43.575Z  

```java
import java.util.*;

class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int rows = m - k + 1;
        int cols = n - k + 1;

        int[][] ans = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                int[] values = new int[k * k];
                int index = 0;

                // Collect elements of the k x k submatrix
                for (int i = r; i < r + k; i++) {
                    for (int j = c; j < c + k; j++) {
                        values[index++] = grid[i][j];
                    }
                }

                // Sort the elements
                Arrays.sort(values);

                int minDiff = Integer.MAX_VALUE;

                // Minimum difference occurs between adjacent
                // distinct values after sorting
                for (int i = 1; i < values.length; i++) {
                    if (values[i] != values[i - 1]) {
                        minDiff = Math.min(
                            minDiff,
                            values[i] - values[i - 1]
                        );
                    }
                }

                // All elements are equal
                if (minDiff == Integer.MAX_VALUE) {
                    minDiff = 0;
                }

                ans[r][c] = minDiff;
            }
        }

        return ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-absolute-difference-in-sliding-submatrix/)