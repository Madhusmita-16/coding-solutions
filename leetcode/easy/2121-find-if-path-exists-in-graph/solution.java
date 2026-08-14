class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (source == destination) {
            return true;
        }

        int[] parent = new int[n];

        // Initialize DSU
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Connect all vertices
        for (int[] edge : edges) {
            union(parent, edge[0], edge[1]);
        }

        // Same component => path exists
        return find(parent, source) == find(parent, destination);
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    private void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}