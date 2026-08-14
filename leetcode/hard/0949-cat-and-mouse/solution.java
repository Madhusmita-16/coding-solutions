import java.util.*;

class Solution {
    // 0 = DRAW
    // 1 = MOUSE wins
    // 2 = CAT wins

    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        // result[mouse][cat][turn]
        int[][][] result = new int[n][n][2];

        // degree[mouse][cat][turn]
        int[][][] degree = new int[n][n][2];

        Queue<int[]> queue = new ArrayDeque<>();

        // Initialize degrees
        for (int mouse = 0; mouse < n; mouse++) {
            for (int cat = 0; cat < n; cat++) {

                // Mouse's turn
                degree[mouse][cat][0] = graph[mouse].length;

                // Cat's turn: cat cannot move to node 0
                int count = 0;
                for (int next : graph[cat]) {
                    if (next != 0) {
                        count++;
                    }
                }
                degree[mouse][cat][1] = count;
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

        while (!queue.isEmpty()) {
            int[] state = queue.poll();

            int mouse = state[0];
            int cat = state[1];
            int turn = state[2];

            int winner = result[mouse][cat][turn];

            // Find all previous states that can move to this state
            if (turn == 0) {
                // Current state is Mouse's turn.
                // Previous turn was Cat's turn.
                // Previous cat could move to current cat.
                for (int prevCat : graph[cat]) {

                    // Cat cannot move to the hole
                    if (prevCat == 0) {
                        continue;
                    }

                    if (result[mouse][prevCat][1] != 0) {
                        continue;
                    }

                    // If Cat can move to a state where Cat wins,
                    // Cat chooses that move.
                    if (winner == 2) {
                        result[mouse][prevCat][1] = 2;
                        queue.offer(new int[]{
                            mouse, prevCat, 1
                        });
                    } else {
                        // This move is not winning for Cat.
                        // Remove it from Cat's available moves.
                        degree[mouse][prevCat][1]--;

                        // No winning move remains -> Mouse wins.
                        if (degree[mouse][prevCat][1] == 0) {
                            result[mouse][prevCat][1] = 1;
                            queue.offer(new int[]{
                                mouse, prevCat, 1
                            });
                        }
                    }
                }

            } else {
                // Current state is Cat's turn.
                // Previous turn was Mouse's turn.
                // Previous mouse could move to current mouse.
                for (int prevMouse : graph[mouse]) {

                    if (result[prevMouse][cat][0] != 0) {
                        continue;
                    }

                    // If Mouse can move to a state where Mouse wins,
                    // Mouse chooses that move.
                    if (winner == 1) {
                        result[prevMouse][cat][0] = 1;
                        queue.offer(new int[]{
                            prevMouse, cat, 0
                        });
                    } else {
                        // This move is not winning for Mouse.
                        degree[prevMouse][cat][0]--;

                        // No winning move remains -> Cat wins.
                        if (degree[prevMouse][cat][0] == 0) {
                            result[prevMouse][cat][0] = 2;
                            queue.offer(new int[]{
                                prevMouse, cat, 0
                            });
                        }
                    }
                }
            }
        }

        return result[1][2][0];
    }
}