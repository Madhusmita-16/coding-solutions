# Snakes and Ladders

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an `n x n` integer matrix `board` where the cells are labeled from `1` to `n2` in a  **Boustrophedon style**  starting from the bottom left of the board (i.e. `board[n - 1][0]`) and alternating direction each row.

You start on square `1` of the board. In each move, starting from square `curr`, do the following:

- Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)]. This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations, regardless of the size of the board.
- If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
- The game ends when you reach the square n2.

A board square on row `r` and column `c` has a snake or ladder if `board[r][c] != -1`. The destination of that snake or ladder is `board[r][c]`. Squares `1` and `n2` are not the starting points of any snake or ladder.

Note that you only take a snake or ladder at most once per dice roll. If the destination to a snake or ladder is the start of another snake or ladder, you do  **not**  follow the subsequent snake or ladder.

- For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You follow the ladder to square 3, but do not follow the subsequent ladder to 4.

Return  *the least number of dice rolls required to reach the square* `n2` *. If it is not possible to reach the square, return* `-1`.

 

 **Example 1:** 

```
Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation: 
In the beginning, you start at square 1 (at row 5, column 0).
You decide to move to square 2 and must take the ladder to square 15.
You then decide to move to square 17 and must take the snake to square 13.
You then decide to move to square 14 and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
This is the lowest possible number of moves to reach the last square, so return 4.

```

 **Example 2:** 

```
Input: board = [[-1,-1],[-1,3]]
Output: 1

```

 

 **Constraints:** 

- n == board.length == board[i].length
- 2 <= n <= 20
- board[i][j] is either -1 or in the range [1, n2].
- The squares labeled 1 and n2 are not the starting points of any snake or ladder.

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 74.50%)  
**Memory:** 46.8 MB (beats 52.93%)  
**Submitted:** 2026-08-15T08:59:39.292Z  

```java
import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        int target = n * n;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[target + 1];

        queue.offer(1);
        visited[1] = true;

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int current = queue.poll();

                if (current == target) {
                    return moves;
                }

                // Try all possible dice rolls
                for (int dice = 1; dice <= 6; dice++) {

                    int next = current + dice;

                    if (next > target) {
                        break;
                    }

                    // Convert square number to board coordinates
                    int[] position = getPosition(next, n);

                    int row = position[0];
                    int col = position[1];

                    // Take snake or ladder if present
                    if (board[row][col] != -1) {
                        next = board[row][col];
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private int[] getPosition(int square, int n) {

        // Convert square number to zero-based index
        int index = square - 1;

        // Row counted from bottom
        int row = n - 1 - index / n;

        // Column within the row
        int col = index % n;

        // Every alternate row is reversed
        if ((n - row) % 2 == 0) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/snakes-and-ladders/)