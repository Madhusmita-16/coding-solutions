import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {

        int[] degree = new int[n + 1];
        boolean[][] graph = new boolean[n + 1][n + 1];

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            degree[u]++;
            degree[v]++;

            graph[u][v] = true;
            graph[v][u] = true;
        }

        List<Integer> odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                odd.add(i);
            }
        }

        // No odd-degree vertices
        if (odd.size() == 0) {
            return true;
        }

        // More than 4 odd vertices cannot be fixed with at most 2 edges
        if (odd.size() > 4) {
            return false;
        }

        // Two odd vertices
        if (odd.size() == 2) {
            int a = odd.get(0);
            int b = odd.get(1);

            // Case 1: Directly connect a and b
            if (!graph[a][b]) {
                return true;
            }

            // Case 2: a-x-b using two new edges
            for (int x = 1; x <= n; x++) {
                if (x != a && x != b
                        && !graph[a][x]
                        && !graph[b][x]) {
                    return true;
                }
            }

            return false;
        }

        // Four odd vertices
        if (odd.size() == 4) {
            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            // Pair 1
            if (!graph[a][b] && !graph[c][d]) {
                return true;
            }

            // Pair 2
            if (!graph[a][c] && !graph[b][d]) {
                return true;
            }

            // Pair 3
            if (!graph[a][d] && !graph[b][c]) {
                return true;
            }

            return false;
        }

        return false;
    }
}