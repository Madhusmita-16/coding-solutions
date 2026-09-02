import java.util.*;

class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Original direction: u -> v
            graph[u].add(new int[]{v, 0});

            // Reverse direction: v -> u
            graph[v].add(new int[]{u, 1});
        }

        int[] answer = new int[n];

        // Find answer[0]
        dfs(0, -1, graph, answer);

        // Calculate answers for all other nodes
        dfsReroot(0, -1, graph, answer);

        return answer;
    }

    private void dfs(int node, int parent,
                     List<int[]>[] graph, int[] answer) {

        for (int[] edge : graph[node]) {

            int next = edge[0];
            int cost = edge[1];

            if (next == parent) {
                continue;
            }

            answer[0] += cost;

            dfs(next, node, graph, answer);
        }
    }

    private void dfsReroot(int node, int parent,
                           List<int[]>[] graph, int[] answer) {

        for (int[] edge : graph[node]) {

            int next = edge[0];
            int cost = edge[1];

            if (next == parent) {
                continue;
            }

            /*
             * Moving the starting point from node -> next
             *
             * cost == 0:
             *   Original edge is node -> next.
             *   For next, this edge must be reversed.
             *   Therefore +1.
             *
             * cost == 1:
             *   Original edge is next -> node.
             *   For next, this edge is already correct.
             *   Therefore -1.
             */
            answer[next] = answer[node] + (cost == 0 ? 1 : -1);

            dfsReroot(next, node, graph, answer);
        }
    }
}