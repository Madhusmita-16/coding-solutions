import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build graph
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];

            graph[u].add(new int[]{v, distance});
            graph[v].add(new int[]{u, distance});
        }

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visited[1] = true;

        int answer = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int city = queue.poll();

            for (int[] edge : graph[city]) {
                int nextCity = edge[0];
                int distance = edge[1];

                // Every edge in city 1's component can affect the score
                answer = Math.min(answer, distance);

                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }

        return answer;
    }
}