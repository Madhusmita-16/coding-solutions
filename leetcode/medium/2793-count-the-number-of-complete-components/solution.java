import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build adjacency list
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int vertices = 0;
            int degreeSum = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visited[i] = true;

            while (!queue.isEmpty()) {
                int node = queue.poll();

                vertices++;
                degreeSum += graph[node].size();

                for (int next : graph[node]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            // Each edge is counted twice in degreeSum
            int edgeCount = degreeSum / 2;

            // Complete graph with v vertices has v * (v - 1) / 2 edges
            int requiredEdges = vertices * (vertices - 1) / 2;

            if (edgeCount == requiredEdges) {
                answer++;
            }
        }

        return answer;
    }
}
