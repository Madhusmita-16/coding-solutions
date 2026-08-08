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
**Memory:** 42.4 MB  
**Submitted:** 2026-08-08T12:43:18.442Z  

```java
import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {
        return checkHorizontal(grid) || checkVertical(grid);
    }

    // --------------------------------------------------
    // Horizontal partition
    // --------------------------------------------------
    private boolean checkHorizontal(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (m == 1) {
            return false;
        }

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        long upperSum = 0;

        for (int cut = 0; cut < m - 1; cut++) {

            for (int j = 0; j < n; j++) {
                upperSum += grid[cut][j];
            }

            long lowerSum = total - upperSum;

            // No deletion needed
            if (upperSum == lowerSum) {
                return true;
            }

            if (upperSum > lowerSum) {
                long need = upperSum - lowerSum;

                // Delete one cell from upper part
                if (canDeleteHorizontalUpper(grid, cut, need)) {
                    return true;
                }
            } else {
                long need = lowerSum - upperSum;

                // Delete one cell from lower part
                if (canDeleteHorizontalLower(grid, cut, need)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Upper rectangle: rows [0 ... cut]
    private boolean canDeleteHorizontalUpper(
            int[][] grid,
            int cut,
            long need) {

        int n = grid[0].length;
        int height = cut + 1;

        // Single row:
        // only endpoints can be removed without disconnecting.
        if (height == 1) {
            return grid[0][0] == need ||
                   grid[0][n - 1] == need;
        }

        // Single column:
        // only endpoints can be removed.
        if (n == 1) {
            return grid[0][0] == need ||
                   grid[cut][0] == need;
        }

        // Height >= 2 and width >= 2.
        // Any boundary cell can be removed.

        // First and last rows
        for (int j = 0; j < n; j++) {
            if (grid[0][j] == need ||
                grid[cut][j] == need) {
                return true;
            }
        }

        // First and last columns
        for (int i = 1; i < cut; i++) {
            if (grid[i][0] == need ||
                grid[i][n - 1] == need) {
                return true;
            }
        }

        return false;
    }

    // Lower rectangle: rows [cut + 1 ... m - 1]
    private boolean canDeleteHorizontalLower(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int n = grid[0].length;

        int firstRow = cut + 1;
        int lastRow = m - 1;

        int height = lastRow - firstRow + 1;

        // Single row
        if (height == 1) {
            return grid[firstRow][0] == need ||
                   grid[firstRow][n - 1] == need;
        }

        // Single column
        if (n == 1) {
            return grid[firstRow][0] == need ||
                   grid[lastRow][0] == need;
        }

        // First and last rows
        for (int j = 0; j < n; j++) {
            if (grid[firstRow][j] == need ||
                grid[lastRow][j] == need) {
                return true;
            }
        }

        // First and last columns
        for (int i = firstRow + 1; i < lastRow; i++) {
            if (grid[i][0] == need ||
                grid[i][n - 1] == need) {
                return true;
            }
        }

        return false;
    }

    // --------------------------------------------------
    // Vertical partition
    // --------------------------------------------------
    private boolean checkVertical(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (n == 1) {
            return false;
        }

        long total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total += grid[i][j];
            }
        }

        long leftSum = 0;

        for (int cut = 0; cut < n - 1; cut++) {

            for (int i = 0; i < m; i++) {
                leftSum += grid[i][cut];
            }

            long rightSum = total - leftSum;

            // No deletion needed
            if (leftSum == rightSum) {
                return true;
            }

            if (leftSum > rightSum) {
                long need = leftSum - rightSum;

                // Delete one cell from left part
                if (canDeleteVerticalLeft(grid, cut, need)) {
                    return true;
                }
            } else {
                long need = rightSum - leftSum;

                // Delete one cell from right part
                if (canDeleteVerticalRight(grid, cut, need)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Left rectangle: columns [0 ... cut]
    private boolean canDeleteVerticalLeft(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int width = cut + 1;

        // Single column
        if (width == 1) {
            return grid[0][0] == need ||
                   grid[m - 1][0] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][0] == need ||
                   grid[0][cut] == need;
        }

        // First and last columns
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == need ||
                grid[i][cut] == need) {
                return true;
            }
        }

        // First and last rows
        for (int j = 1; j < cut; j++) {
            if (grid[0][j] == need ||
                grid[m - 1][j] == need) {
                return true;
            }
        }

        return false;
    }

    // Right rectangle: columns [cut + 1 ... n - 1]
    private boolean canDeleteVerticalRight(
            int[][] grid,
            int cut,
            long need) {

        int m = grid.length;
        int n = grid[0].length;

        int firstCol = cut + 1;
        int lastCol = n - 1;

        int width = lastCol - firstCol + 1;

        // Single column
        if (width == 1) {
            return grid[0][firstCol] == need ||
                   grid[m - 1][firstCol] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][firstCol] == need ||
                   grid[0][lastCol] == need;
        }

        // First and last columns
        for (int i = 0; i < m; i++) {
            if (grid[i][firstCol] == need ||
                grid[i][lastCol] == need) {
                return true;
            }
        }

        // First and last rows
        for (int j = firstCol + 1; j < lastCol; j++) {
            if (grid[0][j] == need ||
                grid[m - 1][j] == need) {
                return true;
            }
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/equal-sum-grid-partition-ii/)