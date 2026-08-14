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