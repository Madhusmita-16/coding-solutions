# Equal Sum Grid Partition II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an `m x n` matrix `grid` of positive integers. Your task is to determine if it is possible to make  **either one horizontal or one vertical cut**  on the grid such that:

- Each of the two resulting sections formed by the cut is non-empty.
- The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in total (from either section).
- If a cell is discounted, the rest of the section must remain connected.

Return `true` if such a partition exists; otherwise, return `false`.

 **Note:**  A section is  **connected**  if every cell in it can be reached from any other cell by moving up, down, left, or right through other cells in the section.

 

 **Example 1:** 

 **Input:**  grid = [[1,4],[2,3]]

 **Output:**  true

 **Explanation:** 

- A horizontal cut after the first row gives sums 1 + 4 = 5 and 2 + 3 = 5, which are equal. Thus, the answer is true.

 **Example 2:** 

 **Input:**  grid = [[1,2],[3,4]]

 **Output:**  true

 **Explanation:** 

- A vertical cut after the first column gives sums 1 + 3 = 4 and 2 + 4 = 6.
- By discounting 2 from the right section (6 - 2 = 4), both sections have equal sums and remain connected. Thus, the answer is true.

 **Example 3:** 

 **Input:**  grid = [[1,2,4],[2,3,5]]

 **Output:**  false

 **Explanation:** 

- A horizontal cut after the first row gives 1 + 2 + 4 = 7 and 2 + 3 + 5 = 10.
- By discounting 3 from the bottom section (10 - 3 = 7), both sections have equal sums, but they do not remain connected as it splits the bottom section into two parts ([2] and [5]). Thus, the answer is false.

 **Example 4:** 

 **Input:**  grid = [[4,1,8],[3,2,6]]

 **Output:**  false

 **Explanation:** 

No valid cut exists, so the answer is `false`.

 

 **Constraints:** 

- 1 <= m == grid.length <= 105
- 1 <= n == grid[i].length <= 105
- 2 <= m * n <= 105
- 1 <= grid[i][j] <= 105

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.7 MB  
**Submitted:** 2026-08-08T12:39:50.699Z  

```java
import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {

        for (int rotation = 0; rotation < 4; rotation++) {

            if (check(grid)) {
                return true;
            }

            grid = rotate(grid);
        }

        return false;
    }

    // Check horizontal partitions where at most one cell
    // can be removed from the upper part.
    private boolean check(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // A horizontal split requires at least 2 rows.
        if (m < 2) {
            return false;
        }

        long total = 0;

        for (int[] row : grid) {
            for (int value : row) {
                total += value;
            }
        }

        long upperSum = 0;

        // Values already encountered in the upper part.
        HashSet<Integer> seen = new HashSet<>();

        // 0 represents "delete nothing".
        seen.add(0);

        for (int i = 0; i < m - 1; i++) {

            // Add current row to upper part.
            for (int j = 0; j < n; j++) {
                seen.add(grid[i][j]);
                upperSum += grid[i][j];
            }

            long need = 2L * upperSum - total;

            /*
             * If we remove x from upper part:
             *
             * upperSum - x = total - upperSum
             *
             * Therefore:
             *
             * x = 2 * upperSum - total
             */

            if (seen.contains((int) need)) {

                // For normal multi-column grids, any cell in
                // the upper part can be removed while keeping
                // the remaining part connected, except when
                // the upper part consists of a single row.
                if (i > 0 || n > 1) {
                    return true;
                }

                // For the first row, only the two boundary
                // cells can be removed while preserving connectivity.
                if (grid[0][0] == need ||
                    grid[0][n - 1] == need ||
                    need == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    // Rotate matrix 90 degrees clockwise.
    private int[][] rotate(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] rotated = new int[n][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][m - 1 - i] = grid[i][j];
            }
        }

        return rotated;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/equal-sum-grid-partition-ii/)