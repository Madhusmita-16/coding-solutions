class Solution {

    int[] parent;
    int[] rank;

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            // This edge is redundant
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }

        return true;
    }

    int minEdgesReq(int n, int[][] edges) {
        // Not enough edges to connect n vertices
        if (edges.length < n - 1) {
            return -1;
        }

        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int components = n;

        for (int[] edge : edges) {
            if (union(edge[0], edge[1])) {
                components--;
            }
        }

        // Need one operation for each connection between components
        return components - 1;
    }
}