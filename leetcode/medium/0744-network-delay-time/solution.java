import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            int u = time[0];
            int v = time[1];
            int w = time[2];

            graph[u].add(new int[]{v, w});
        }

        // Dijkstra's algorithm
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            int node = current[0];
            int time = current[1];

            // Ignore outdated entries
            if (time > dist[node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int weight = edge[1];

                int newTime = time + weight;

                if (newTime < dist[next]) {
                    dist[next] = newTime;
                    pq.offer(new int[]{next, newTime});
                }
            }
        }

        // Find the maximum shortest distance
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }
}