# Surrounded Regions

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an `m x n` matrix `board` containing  **letters**  `'X'` and `'O'`,  **capture regions**  that are  **surrounded** :

- Connect: A cell is connected to adjacent cells horizontally or vertically.
- Region: To form a region connect every 'O' cell.
- Surround: A region is surrounded if none of the 'O' cells in that region are on the edge of the board. Such regions are completely enclosed by 'X' cells.

To capture a  **surrounded region**, replace all `'O'`s with `'X'`s  **in-place**  within the original board. You do not need to return anything.

 

 **Example 1:** 

 **Input:**  board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

 **Output:**  [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

 **Explanation:** 

In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

 **Example 2:** 

 **Input:**  board = [["X"]]

 **Output:**  [["X"]]

 

 **Constraints:** 

- m == board.length
- n == board[i].length
- 1 <= m, n <= 200
- board[i][j] is 'X' or 'O'.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.3 MB  
**Submitted:** 2026-08-15T09:04:35.172Z  

```java
import java.util.*;

class Solution {
    public void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Add boundary O's
        for (int i = 0; i < m; i++) {

            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = '#';
            }

            if (board[i][n - 1] == 'O') {
                queue.offer(new int[]{i, n - 1});
                board[i][n - 1] = '#';
            }
        }

        for (int j = 0; j < n; j++) {

            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
                board[0][j] = '#';
            }

            if (board[m - 1][j] == 'O') {
                queue.offer(new int[]{m - 1, j});
                board[m - 1][j] = '#';
            }
        }

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // Mark all O's connected to the boundary
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    board[newRow][newCol] == 'O') {

                    board[newRow][newCol] = '#';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        // Capture surrounded regions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/surrounded-regions/)