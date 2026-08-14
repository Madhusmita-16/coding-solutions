# Q1. Map of Highest Peak

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer matrix `isWater` of size `m x n` that represents a map of  **land**  and  **water**  cells.

- If isWater[i][j] == 0, cell (i, j) is a land cell.
- If isWater[i][j] == 1, cell (i, j) is a water cell.

You must assign each cell a height in a way that follows these rules:

- The height of each cell must be non-negative.
- If the cell is a water cell, its height must be 0.
- Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).

Find an assignment of heights such that the maximum height in the matrix is  **maximized**.

Return  *an integer matrix* `height` *of size* `m x n` *where* `height[i][j]` *is cell* `(i, j)` *'s height. If there are multiple solutions, return  **any**  of them*.

 

 **Example 1:** 

```
Input: isWater = [[0,1],[0,0]]
Output: [[1,0],[2,1]]
Explanation: The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.

```

 **Example 2:** 

```
Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
Output: [[1,1,0],[0,1,1],[1,2,2]]
Explanation: A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.

```

 

 **Constraints:** 

- m == isWater.length
- n == isWater[i].length
- 1 <= m, n <= 1000
- isWater[i][j] is 0 or 1.
- There is at least one water cell.

 

 **Note:**  This question is the same as 542: https://leetcode.com/problems/01-matrix/

## Solution

**Language:** Java  
**Runtime:** 51 ms (beats 21.08%)  
**Memory:** 184.1 MB (beats 56.16%)  
**Submitted:** 2026-08-14T17:15:07.864Z  

```java
import java.util.*;

class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] height = new int[m][n];

        // -1 means unvisited
        for (int i = 0; i < m; i++) {
            Arrays.fill(height[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Add all water cells as starting points
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // Multi-source BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    height[newRow][newCol] == -1) {

                    height[newRow][newCol] = height[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return height;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/map-of-highest-peak/)