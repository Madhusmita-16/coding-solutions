# Q3. Cat and Mouse

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

A game on an  **undirected**  graph is played by two players, Mouse and Cat, who alternate turns.

The graph is given as follows: `graph[a]` is a list of all nodes `b` such that `ab` is an edge of the graph.

The mouse starts at node `1` and goes first, the cat starts at node `2` and goes second, and there is a hole at node `0`.

During each player's turn, they  **must**  travel along one edge of the graph that meets where they are.  For example, if the Mouse is at node 1, it  **must**  travel to any node in `graph[1]`.

Additionally, it is not allowed for the Cat to travel to the Hole (node `0`).

Then, the game can end in three ways:

- If ever the Cat occupies the same node as the Mouse, the Cat wins.
- If ever the Mouse reaches the Hole, the Mouse wins.
- If ever a position is repeated (i.e., the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.

Given a `graph`, and assuming both players play optimally, return

- 1 if the mouse wins the game,
- 2 if the cat wins the game, or
- 0 if the game is a draw.

 

 **Example 1:** 

```
Input: graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
Output: 0

```

 **Example 2:** 

```
Input: graph = [[1,3],[0],[3],[0,2]]
Output: 1

```

 

 **Constraints:** 

- 3 <= graph.length <= 50
- 1 <= graph[i].length < graph.length
- 0 <= graph[i][j] < graph.length
- graph[i][j] != i
- graph[i] is unique.
- The mouse and the cat can always move.

## Solution

**Language:** Java  
**Runtime:** 21 ms (beats 85.62%)  
**Memory:** 47.1 MB (beats 76.47%)  
**Submitted:** 2026-08-14T18:07:32.655Z  

```java
import java.util.*;

class Solution {

    // 0 = Draw
    // 1 = Mouse wins
    // 2 = Cat wins

    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        // result[mouse][cat][turn]
        int[][][] result = new int[n][n][2];

        // degree[mouse][cat][turn]
        int[][][] degree = new int[n][n][2];

        Queue<int[]> queue = new ArrayDeque<>();

        // Calculate the number of possible moves for every state
        for (int mouse = 0; mouse < n; mouse++) {
            for (int cat = 0; cat < n; cat++) {

                // Mouse can move to every neighbor
                degree[mouse][cat][0] = graph[mouse].length;

                // Cat cannot move to node 0
                for (int next : graph[cat]) {
                    if (next != 0) {
                        degree[mouse][cat][1]++;
                    }
                }
            }
        }

        // Mouse reaches the hole -> Mouse wins
        for (int cat = 1; cat < n; cat++) {
            result[0][cat][0] = 1;
            result[0][cat][1] = 1;

            queue.offer(new int[]{0, cat, 0});
            queue.offer(new int[]{0, cat, 1});
        }

        // Cat catches Mouse -> Cat wins
        for (int node = 1; node < n; node++) {
            result[node][node][0] = 2;
            result[node][node][1] = 2;

            queue.offer(new int[]{node, node, 0});
            queue.offer(new int[]{node, node, 1});
        }

        // Process known states backwards
        while (!queue.isEmpty()) {

            int[] state = queue.poll();

            int mouse = state[0];
            int cat = state[1];
            int turn = state[2];

            int winner = result[mouse][cat][turn];

            if (turn == 0) {
                /*
                 * Current state: Mouse's turn
                 *
                 * Previous state must have been:
                 * (mouse, previousCat, Cat's turn)
                 */
                for (int previousCat : graph[cat]) {

                    // Cat is not allowed to move to node 0
                    if (previousCat == 0) {
                        continue;
                    }

                    if (result[mouse][previousCat][1] != 0) {
                        continue;
                    }

                    if (winner == 2) {
                        /*
                         * Cat can move to a Cat-winning state.
                         * Therefore Cat can force a win.
                         */
                        result[mouse][previousCat][1] = 2;

                        queue.offer(
                            new int[]{mouse, previousCat, 1}
                        );

                    } else {
                        /*
                         * This move does not help Cat.
                         * Remove it from Cat's possible winning moves.
                         */
                        degree[mouse][previousCat][1]--;

                        /*
                         * If Cat has no moves left that avoid
                         * Mouse's win, Mouse wins.
                         */
                        if (degree[mouse][previousCat][1] == 0) {
                            result[mouse][previousCat][1] = 1;

                            queue.offer(
                                new int[]{mouse, previousCat, 1}
                            );
                        }
                    }
                }

            } else {
                /*
                 * Current state: Cat's turn
                 *
                 * Previous state must have been:
                 * (previousMouse, cat, Mouse's turn)
                 */
                for (int previousMouse : graph[mouse]) {

                    if (result[previousMouse][cat][0] != 0) {
                        continue;
                    }

                    if (winner == 1) {
                        /*
                         * Mouse can move to a Mouse-winning state.
                         * Therefore Mouse can force a win.
                         */
                        result[previousMouse][cat][0] = 1;

                        queue.offer(
                            new int[]{previousMouse, cat, 0}
                        );

                    } else {
                        /*
                         * This move does not help Mouse.
                         */
                        degree[previousMouse][cat][0]--;

                        /*
                         * If Mouse has no winning move left,
                         * Cat wins.
                         */
                        if (degree[previousMouse][cat][0] == 0) {
                            result[previousMouse][cat][0] = 2;

                            queue.offer(
                                new int[]{previousMouse, cat, 0}
                            );
                        }
                    }
                }
            }
        }

        // Initial position:
        // Mouse = 1
        // Cat = 2
        // Mouse moves first
        return result[1][2][0];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/cat-and-mouse/)