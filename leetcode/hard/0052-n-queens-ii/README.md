# N-Queens II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

The  **n-queens**  puzzle is the problem of placing `n` queens on an `n x n` chessboard such that no two queens attack each other.

Given an integer `n`, return  *the number of distinct solutions to the  **n-queens puzzle***.

 

 **Example 1:** 

```
Input: n = 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown.

```

 **Example 2:** 

```
Input: n = 1
Output: 1

```

 

 **Constraints:** 

- 1 <= n <= 9

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 95.91%)  
**Memory:** 41.9 MB (beats 86.76%)  
**Submitted:** 2026-08-15T08:47:53.717Z  

```java
class Solution {

    private int count;

    public int totalNQueens(int n) {

        count = 0;

        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1];
        boolean[] diag2 = new boolean[2 * n - 1];

        backtrack(0, n, cols, diag1, diag2);

        return count;
    }

    private void backtrack(int row, int n,
                           boolean[] cols,
                           boolean[] diag1,
                           boolean[] diag2) {

        // All queens have been placed
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {

            // Diagonal indexes
            int d1 = row - col + n - 1;
            int d2 = row + col;

            // Position is already attacked
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }

            // Place queen
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            // Move to next row
            backtrack(row + 1, n, cols, diag1, diag2);

            // Backtrack
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/n-queens-ii/)