import java.util.*;

class Solution {

    static class Edge {
        int to;
        int time;

        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    private List<Edge>[] graph;
    private int[] values;
    private int[] visited;
    private int maxTime;
    private int answer;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {

        this.values = values;
        this.maxTime = maxTime;
        this.answer = values[0];

        int n = values.length;

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build undirected graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int time = edge[2];

            graph[u].add(new Edge(v, time));
            graph[v].add(new Edge(u, time));
        }

        visited = new int[n];

        // Node 0 is already counted
        visited[0] = 1;

        dfs(0, 0, values[0]);

        return answer;
    }

    private void dfs(int node, int time, int quality) {

        // We only care about paths that return to node 0
        if (node == 0) {
            answer = Math.max(answer, quality);
        }

        // Try every neighboring edge
        for (Edge edge : graph[node]) {

            int next = edge.to;
            int newTime = time + edge.time;

            // Cannot exceed maxTime
            if (newTime > maxTime) {
                continue;
            }

            boolean firstVisit = visited[next] == 0;

            // Add value only on first visit
            if (firstVisit) {
                visited[next] = 1;
                dfs(next, newTime, quality + values[next]);
                visited[next] = 0;
            } else {
                dfs(next, newTime, quality);
            }
        }
    }
}