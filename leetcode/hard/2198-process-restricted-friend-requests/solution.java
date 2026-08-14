class Solution {
    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {

        DSU dsu = new DSU(n);

        boolean[] result = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {

            int u = requests[i][0];
            int v = requests[i][1];

            int rootU = dsu.find(u);
            int rootV = dsu.find(v);

            // Already in the same group
            if (rootU == rootV) {
                result[i] = true;
                continue;
            }

            boolean allowed = true;

            // Check every restriction
            for (int[] restriction : restrictions) {

                int a = restriction[0];
                int b = restriction[1];

                int rootA = dsu.find(a);
                int rootB = dsu.find(b);

                // If merging u and v would put restricted
                // people into the same connected component
                if ((rootA == rootU && rootB == rootV) ||
                    (rootA == rootV && rootB == rootU)) {

                    allowed = false;
                    break;
                }
            }

            if (allowed) {
                dsu.union(rootU, rootV);
                result[i] = true;
            } else {
                result[i] = false;
            }
        }

        return result;
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

        void union(int a, int b) {

            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }
}