# Q3. Maximum Area of Two Non-Overlapping Square Submatrices

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a 2D integer matrix `mat` of size `m × n`, where:

- mat[r][c] == 1 means the cell at row r and column c is usable.
- mat[r][c] == 0 means it is not usable.

Your task is to find  **two submatrices**  that satisfy the following conditions:

- Both submatrices must be squares of the same side length k.
- The two submatrices must not share any cell.
- Each submatrix can only cover cells where mat[r][c] == 1.

Return the  **maximum possible area**  of each of the two squares. If it is not possible to choose two such squares, return 0.

 

 **Example 1:** 

 **Input:**  mat = [[1,1,1,0],[1,1,1,1],[0,0,1,1]]

 **Output:**  4

 **Explanation:** 

The largest equal non-overlapping squares have side length `k = 2` with area 4.

- First square starts at top-left (0, 0) and covers cells (0, 0), (0, 1), (1, 0), and (1, 1).
- Second square starts at top-left (1, 2) and covers cells (1, 2), (1, 3), (2, 2), and (2, 3).

Thus, the answer is 4.

 **Example 2:** 

 **Input:**  mat = [[0,1],[1,0]]

 **Output:**  1

 **Explanation:** 

The largest equal non-overlapping squares have side length `k = 1` with area 1.

- First square starts at top-left (0, 1) and covers cell (0, 1).
- Second square starts at top-left (1, 0) and covers cell (1, 0).

Thus, the answer is 1.

 **Example 3:** 

 **Input:**  mat = [[0,0],[0,1]]

 **Output:**  0

 **Explanation:** 

There is only one usable cell, so it is impossible to choose two non-overlapping squares. Thus, the answer is 0.

 

 **Constraints:** 

- mat.length == m
- mat[i].length == n
- 1 <= m, n <= 500
- mat[i][j] is either 0 or 1.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.4 MB  
**Submitted:** 2026-08-11T15:43:35.987Z  

```java
class Solution {
    public int maxArea(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // dp[r][c] = largest all-1 square ending at (r,c)
        int[][] dp = new int[m][n];

        int maxK = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 1) {
                    if (r == 0 || c == 0) {
                        dp[r][c] = 1;
                    } else {
                        dp[r][c] = 1 + Math.min(
                            dp[r - 1][c - 1],
                            Math.min(dp[r - 1][c], dp[r][c - 1])
                        );
                    }

                    maxK = Math.max(maxK, dp[r][c]);
                }
            }
        }

        int left = 1;
        int right = maxK;
        int answer = 0;

        while (left <= right) {
            int k = left + (right - left) / 2;

            if (canPlaceTwo(dp, m, n, k)) {
                answer = k;
                left = k + 1;
            } else {
                right = k - 1;
            }
        }

        return answer * answer;
    }

    private boolean canPlaceTwo(int[][] dp, int m, int n, int k) {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (int r = 0; r + k <= m; r++) {
            for (int c = 0; c + k <= n; c++) {

                int bottom = r + k - 1;
                int right = c + k - 1;

                // Check whether this k x k square contains only 1s
                if (dp[bottom][right] < k) {
                    continue;
                }

                /*
                 * If another square is:
                 *
                 * completely above:
                 * previousRow + k <= r
                 *
                 * completely below:
                 * r + k <= previousRow
                 *
                 * completely left:
                 * previousCol + k <= c
                 *
                 * completely right:
                 * c + k <= previousCol
                 */
                if (minRow + k <= r ||
                    maxRow >= r + k ||
                    minCol + k <= c ||
                    maxCol >= c + k) {
                    return true;
                }

                minRow = Math.min(minRow, r);
                maxRow = Math.max(maxRow, r);
                minCol = Math.min(minCol, c);
                maxCol = Math.max(maxCol, c);
            }
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-area-of-two-non-overlapping-square-submatrices/)