import java.util.*;

class Graph {

    private int n;
    private List<int[]>[] graph;

    public Graph(int n, int[][] edges) {
        this.n = n;

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }
    }

    public void addEdge(int[] edge) {
        int from = edge[0];
        int to = edge[1];
        int cost = edge[2];

        graph[from].add(new int[]{to, cost});
    }

    public int shortestPath(int node1, int node2) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[node1] = 0;

        // {node, distance}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[1], b[1])
        );

        pq.offer(new int[]{node1, 0});

        while (!pq.isEmpty()) {

            int[] current = pq.poll();

            int node = current[0];
            int distance = current[1];

            // Ignore outdated entries
            if (distance > dist[node]) {
                continue;
            }

            // Destination reached
            if (node == node2) {
                return distance;
            }

            for (int[] edge : graph[node]) {

                int next = edge[0];
                int cost = edge[1];

                int newDistance = distance + cost;

                if (newDistance < dist[next]) {
                    dist[next] = newDistance;
                    pq.offer(new int[]{next, newDistance});
                }
            }
        }

        return -1;
    }
}