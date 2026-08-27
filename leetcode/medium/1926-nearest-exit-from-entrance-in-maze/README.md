# Nearest Exit from Entrance in Maze

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an `m x n` matrix `maze` (**0-indexed**) with empty cells (represented as `'.'`) and walls (represented as `'+'`). You are also given the `entrance` of the maze, where `entrance = [entrancerow, entrancecol]` denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell  **up**,  **down**,  **left**, or  **right**. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the  **nearest exit**  from the `entrance`. An  **exit**  is defined as an  **empty cell**  that is at the  **border**  of the `maze`. The `entrance`  **does not count**  as an exit.

Return  *the  **number of steps**  in the shortest path from the* `entrance` *to the nearest exit, or* `-1` *if no such path exists*.

 

 **Example 1:** 

```
Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.

```

 **Example 2:** 

```
Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.

```

 **Example 3:** 

```
Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.

```

 

 **Constraints:** 

- maze.length == m
- maze[i].length == n
- 1 <= m, n <= 100
- maze[i][j] is either '.' or '+'.
- entrance.length == 2
- 0 <= entrancerow < m
- 0 <= entrancecol < n
- entrance will always be an empty cell.

## Solution

**Language:** Java  
**Runtime:** 7 ms (beats 63.38%)  
**Memory:** 47.9 MB (beats 95.21%)  
**Submitted:** 2026-08-27T10:42:58.491Z  

```java
import java.util.*;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {

        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int startRow = entrance[0];
        int startCol = entrance[1];

        queue.offer(new int[]{startRow, startCol, 0});

        // Mark entrance as visited
        maze[startRow][startCol] = '+';

        int[][] directions = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int steps = current[2];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check boundaries
                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                // Cannot enter walls or already visited cells
                if (maze[newRow][newCol] == '+') {
                    continue;
                }

                int newSteps = steps + 1;

                // Mark visited
                maze[newRow][newCol] = '+';

                // Border cell = exit
                if (newRow == 0 || newRow == m - 1 ||
                    newCol == 0 || newCol == n - 1) {
                    return newSteps;
                }

                queue.offer(new int[]{newRow, newCol, newSteps});
            }
        }

        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/)