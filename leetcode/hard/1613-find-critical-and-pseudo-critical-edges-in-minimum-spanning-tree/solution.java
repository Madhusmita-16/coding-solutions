import java.util.*;

class Solution {

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        int m = edges.length;

        // Add original index to every edge:
        // [u, v, weight, originalIndex]
        int[][] e = new int[m][4];

        for (int i = 0; i < m; i++) {
            e[i][0] = edges[i][0];
            e[i][1] = edges[i][1];
            e[i][2] = edges[i][2];
            e[i][3] = i;
        }

        // Sort by weight
        Arrays.sort(e, (a, b) -> Integer.compare(a[2], b[2]));

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        // Weight of the original MST
        int baseWeight = kruskal(n, e, -1, -1);

        for (int i = 0; i < m; i++) {

            // If removing this edge makes MST impossible
            // or increases its weight, it is critical.
            int withoutEdge = kruskal(n, e, i, -1);

            if (withoutEdge > baseWeight) {
                critical.add(e[i][3]);
                continue;
            }

            // Force this edge into the MST.
            int withEdge = kruskal(n, e, -1, i);

            if (withEdge == baseWeight) {
                pseudo.add(e[i][3]);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudo);

        return result;
    }

    private int kruskal(int n, int[][] edges, int skip, int force) {

        DSU dsu = new DSU(n);
        int weight = 0;
        int count = 0;

        // Force the selected edge first
        if (force != -1) {
            int u = edges[force][0];
            int v = edges[force][1];
            int w = edges[force][2];

            dsu.union(u, v);
            weight += w;
            count++;
        }

        for (int i = 0; i < edges.length; i++) {

            if (i == skip || i == force) {
                continue;
            }

            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            if (dsu.union(u, v)) {
                weight += w;
                count++;

                if (count == n - 1) {
                    break;
                }
            }
        }

        // If we couldn't connect all vertices,
        // return a very large value.
        if (count != n - 1) {
            return Integer.MAX_VALUE;
        }

        return weight;
    }

    static class DSU {

        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        boolean union(int a, int b) {

            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return false;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }

            return true;
        }
    }
}