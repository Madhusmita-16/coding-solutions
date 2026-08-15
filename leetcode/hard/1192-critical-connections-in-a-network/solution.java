import java.util.*;

class Solution {

    private int time;
    private List<List<Integer>> graph;
    private List<List<Integer>> result;
    private int[] discovery;
    private int[] low;

    public List<List<Integer>> criticalConnections(
            int n, List<List<Integer>> connections) {

        graph = new ArrayList<>();
        result = new ArrayList<>();
        discovery = new int[n];
        low = new int[n];
        time = 0;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Build undirected graph
        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Start DFS
        dfs(0, -1);

        return result;
    }

    private void dfs(int node, int parent) {

        discovery[node] = low[node] = ++time;

        for (int next : graph.get(node)) {

            // Ignore the edge back to parent
            if (next == parent) {
                continue;
            }

            // Unvisited node
            if (discovery[next] == 0) {

                dfs(next, node);

                // Update lowest reachable discovery time
                low[node] = Math.min(low[node], low[next]);

                // Bridge condition
                if (low[next] > discovery[node]) {
                    result.add(Arrays.asList(node, next));
                }

            } else {
                // Back edge
                low[node] = Math.min(low[node], discovery[next]);
            }
        }
    }
}