# Count Submatrices With Equal Frequency of X and Y

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a 2D character matrix `grid`, where `grid[i][j]` is either `'X'`, `'Y'`, or `'.'`, return the number of submatrices that contain:

- grid[0][0]
- an equal frequency of 'X' and 'Y'.
- at least one 'X'.

 

 **Example 1:** 

 **Input:**  grid = [["X","Y","."],["Y",".","."]]

 **Output:**  3

 **Explanation:** 

 **Example 2:** 

 **Input:**  grid = [["X","X"],["X","Y"]]

 **Output:**  0

 **Explanation:** 

No submatrix has an equal frequency of `'X'` and `'Y'`.

 **Example 3:** 

 **Input:**  grid = [[".","."],[".","."]]

 **Output:**  0

 **Explanation:** 

No submatrix has at least one `'X'`.

 

 **Constraints:** 

- 1 <= grid.length, grid[i].length <= 1000
- grid[i][j] is either 'X', 'Y', or '.'.

## Solution

**Language:** Java  
**Runtime:** 29 ms (beats 71.00%)  
**Memory:** 249.5 MB (beats 43.50%)  
**Submitted:** 2026-08-08T12:32:35.902Z  

```java
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // balance[i][j] = X count - Y count
        int[][] balance = new int[m + 1][n + 1];

        // xCount[i][j] = number of X's
        int[][] xCount = new int[m + 1][n + 1];

        int ans = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                int value = 0;
                int x = 0;

                if (grid[i - 1][j - 1] == 'X') {
                    value = 1;
                    x = 1;
                } else if (grid[i - 1][j - 1] == 'Y') {
                    value = -1;
                }

                // 2D prefix sum for X - Y balance
                balance[i][j] =
                        balance[i - 1][j]
                        + balance[i][j - 1]
                        - balance[i - 1][j - 1]
                        + value;

                // 2D prefix sum for number of X's
                xCount[i][j] =
                        xCount[i - 1][j]
                        + xCount[i][j - 1]
                        - xCount[i - 1][j - 1]
                        + x;

                /*
                 * Since every valid submatrix must start at (0,0),
                 * its bottom-right corner is (i-1,j-1).
                 *
                 * balance == 0 means:
                 * number of X == number of Y
                 *
                 * xCount > 0 means:
                 * there is at least one X.
                 */
                if (balance[i][j] == 0 && xCount[i][j] > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/count-submatrices-with-equal-frequency-of-x-and-y/)