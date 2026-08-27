import java.util.*;

class Solution {
    public int minReorder(int n, int[][] connections) {

        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : connections) {
            int from = edge[0];
            int to = edge[1];

            // Original direction: from -> to
            graph[from].add(new int[]{to, 1});

            // Reverse direction for traversal: to -> from
            graph[to].add(new int[]{from, 0});
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        int changes = 0;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int[] edge : graph[current]) {

                int next = edge[0];
                int cost = edge[1];

                if (visited[next]) {
                    continue;
                }

                visited[next] = true;

                // Original edge points away from city 0
                changes += cost;

                queue.offer(next);
            }
        }

        return changes;
    }
}