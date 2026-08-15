# Number of Islands

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water), return  *the number of islands*.

An  **island**  is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

 **Example 1:** 

```
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

```

 **Example 2:** 

```
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3

```

 

 **Constraints:** 

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 300
- grid[i][j] is '0' or '1'.

## Solution

**Language:** Java  
**Runtime:** 7 ms (beats 13.70%)  
**Memory:** 51.3 MB (beats 89.41%)  
**Submitted:** 2026-08-15T09:06:20.653Z  

```java
import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int islands = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == '1') {

                    islands++;

                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    // Mark as visited
                    grid[i][j] = '0';

                    while (!queue.isEmpty()) {

                        int[] current = queue.poll();

                        int row = current[0];
                        int col = current[1];

                        for (int[] dir : directions) {

                            int newRow = row + dir[0];
                            int newCol = col + dir[1];

                            if (newRow >= 0 && newRow < m &&
                                newCol >= 0 && newCol < n &&
                                grid[newRow][newCol] == '1') {

                                grid[newRow][newCol] = '0';
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }

        return islands;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-islands/)