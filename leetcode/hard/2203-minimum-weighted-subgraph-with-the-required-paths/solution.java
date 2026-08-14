import java.util.*;

class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        List<long[]>[] graph = new ArrayList[n];
        List<long[]>[] reverseGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new long[]{v, w});
            reverseGraph[v].add(new long[]{u, w});
        }

        // Shortest distances from src1
        long[] dist1 = dijkstra(graph, src1);

        // Shortest distances from src2
        long[] dist2 = dijkstra(graph, src2);

        // Shortest distances from every node to dest
        // = shortest distances from dest in reversed graph
        long[] distDest = dijkstra(reverseGraph, dest);

        long answer = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (dist1[i] == Long.MAX_VALUE ||
                dist2[i] == Long.MAX_VALUE ||
                distDest[i] == Long.MAX_VALUE) {
                continue;
            }

            long total = dist1[i] + dist2[i] + distDest[i];

            answer = Math.min(answer, total);
        }

        return answer == Long.MAX_VALUE ? -1 : answer;
    }

    private long[] dijkstra(List<long[]>[] graph, int source) {

        int n = graph.length;

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[source] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[1], b[1])
        );

        pq.offer(new long[]{source, 0});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            int node = (int) current[0];
            long distance = current[1];

            // Ignore outdated entry
            if (distance != dist[node]) {
                continue;
            }

            for (long[] edge : graph[node]) {

                int next = (int) edge[0];
                long weight = edge[1];

                long newDistance = distance + weight;

                if (newDistance < dist[next]) {
                    dist[next] = newDistance;
                    pq.offer(new long[]{next, newDistance});
                }
            }
        }

        return dist;
    }
}