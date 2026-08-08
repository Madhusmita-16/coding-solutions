class Solution {
    int[] parent, rank_;

    int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return;
        if (rank_[ra] < rank_[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        if (rank_[ra] == rank_[rb]) rank_[ra]++;
    }

    int minEdgesReq(int n, int[][] edges) {
        parent = new int[n];
        rank_ = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        int c = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) c++;
        }

        int m = edges.length;
        int treeEdgesNeeded = n - c;   // edges required for a spanning forest
        int redundant = m - treeEdgesNeeded; // extra/removable edges available
        int needed = c - 1;            // edges needed to link c components into 1

        return (redundant >= needed) ? needed : -1;
    }
}