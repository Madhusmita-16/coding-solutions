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
**Runtime:** 205 ms (beats 78.77%)  
**Memory:** 279 MB (beats 42.47%)  
**Submitted:** 2026-08-08T12:44:27.198Z  

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

        if (m <= 1) return false;

        long total = 0;

        // Value -> rows containing this value
        Map<Long, List<Integer>> rows = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                total += val;

                rows.computeIfAbsent(val, x -> new ArrayList<>())
                    .add(i);
            }
        }

        long upperSum = 0;

        for (int cut = 0; cut < m - 1; cut++) {

            for (int j = 0; j < n; j++) {
                upperSum += grid[cut][j];
            }

            long lowerSum = total - upperSum;

            // No deletion
            if (upperSum == lowerSum) {
                return true;
            }

            long need;

            if (upperSum > lowerSum) {
                // Delete from upper part
                need = upperSum - lowerSum;

                if (canDeleteHorizontalUpper(
                        grid, cut, need, rows)) {
                    return true;
                }

            } else {
                // Delete from lower part
                need = lowerSum - upperSum;

                if (canDeleteHorizontalLower(
                        grid, cut, need, rows)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Upper = rows [0 ... cut]
    private boolean canDeleteHorizontalUpper(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> rows) {

        int m = grid.length;
        int n = grid[0].length;

        List<Integer> list = rows.get(need);

        if (list == null) return false;

        // If width > 1 and height > 1,
        // ANY cell can be deleted.
        if (n > 1 && cut + 1 > 1) {
            for (int row : list) {
                if (row <= cut) {
                    return true;
                }
            }
            return false;
        }

        // Single row
        if (cut + 1 == 1) {
            return grid[0][0] == need ||
                   grid[0][n - 1] == need;
        }

        // Single column
        if (n == 1) {
            return grid[0][0] == need ||
                   grid[cut][0] == need;
        }

        return false;
    }

    // Lower = rows [cut + 1 ... m-1]
    private boolean canDeleteHorizontalLower(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> rows) {

        int m = grid.length;
        int n = grid[0].length;

        int firstRow = cut + 1;
        int lastRow = m - 1;

        List<Integer> list = rows.get(need);

        if (list == null) return false;

        // Both dimensions > 1:
        // any cell can be removed.
        if (n > 1 && firstRow < lastRow) {
            for (int row : list) {
                if (row >= firstRow) {
                    return true;
                }
            }
            return false;
        }

        // Single row
        if (firstRow == lastRow) {
            return grid[firstRow][0] == need ||
                   grid[firstRow][n - 1] == need;
        }

        // Single column
        if (n == 1) {
            return grid[firstRow][0] == need ||
                   grid[lastRow][0] == need;
        }

        return false;
    }

    // --------------------------------------------------
    // Vertical partition
    // --------------------------------------------------
    private boolean checkVertical(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (n <= 1) return false;

        long total = 0;

        // Value -> columns containing this value
        Map<Long, List<Integer>> cols = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long val = grid[i][j];
                total += val;

                cols.computeIfAbsent(val, x -> new ArrayList<>())
                    .add(j);
            }
        }

        long leftSum = 0;

        for (int cut = 0; cut < n - 1; cut++) {

            for (int i = 0; i < m; i++) {
                leftSum += grid[i][cut];
            }

            long rightSum = total - leftSum;

            // No deletion
            if (leftSum == rightSum) {
                return true;
            }

            long need;

            if (leftSum > rightSum) {
                // Delete from left
                need = leftSum - rightSum;

                if (canDeleteVerticalLeft(
                        grid, cut, need, cols)) {
                    return true;
                }

            } else {
                // Delete from right
                need = rightSum - leftSum;

                if (canDeleteVerticalRight(
                        grid, cut, need, cols)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Left = columns [0 ... cut]
    private boolean canDeleteVerticalLeft(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> cols) {

        int m = grid.length;

        List<Integer> list = cols.get(need);

        if (list == null) return false;

        // Width > 1 and height > 1:
        // any cell can be deleted.
        if (m > 1 && cut + 1 > 1) {
            for (int col : list) {
                if (col <= cut) {
                    return true;
                }
            }
            return false;
        }

        // Single column
        if (cut + 1 == 1) {
            return grid[0][0] == need ||
                   grid[m - 1][0] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][0] == need ||
                   grid[0][cut] == need;
        }

        return false;
    }

    // Right = columns [cut + 1 ... n-1]
    private boolean canDeleteVerticalRight(
            int[][] grid,
            int cut,
            long need,
            Map<Long, List<Integer>> cols) {

        int m = grid.length;
        int n = grid[0].length;

        int firstCol = cut + 1;
        int lastCol = n - 1;

        List<Integer> list = cols.get(need);

        if (list == null) return false;

        // Width > 1 and height > 1:
        // any cell can be deleted.
        if (m > 1 && firstCol < lastCol) {
            for (int col : list) {
                if (col >= firstCol) {
                    return true;
                }
            }
            return false;
        }

        // Single column
        if (firstCol == lastCol) {
            return grid[0][firstCol] == need ||
                   grid[m - 1][firstCol] == need;
        }

        // Single row
        if (m == 1) {
            return grid[0][firstCol] == need ||
                   grid[0][lastCol] == need;
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/equal-sum-grid-partition-ii/)