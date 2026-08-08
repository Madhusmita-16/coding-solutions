class Solution {

    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

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
                return false;
            }

            if (size[pa] < size[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }

            parent[pb] = pa;
            size[pa] += size[pb];
            components--;

            return true;
        }
    }

    private int n;
    private int[][] edges;
    private int k;

    private boolean canBuild(int limit) {
        DSU dsu = new DSU(n);

        /*
         * First take all edges whose original strength
         * is already >= limit.
         *
         * Mandatory edges are included automatically.
         */
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int strength = edge[2];

            if (strength >= limit) {
                dsu.union(u, v);
            }
        }

        int upgradesLeft = k;

        /*
         * Now consider optional edges that can reach
         * 'limit' after doubling.
         *
         * strength * 2 >= limit
         *
         * We can use at most k upgrades.
         */
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int strength = edge[2];
            int must = edge[3];

            // Mandatory edges cannot be upgraded.
            if (must == 1) {
                continue;
            }

            if (upgradesLeft > 0 && strength * 2 >= limit) {
                if (dsu.union(u, v)) {
                    upgradesLeft--;
                }
            }
        }

        return dsu.components == 1;
    }

    public int maxStability(int n, int[][] edges, int k) {

        this.n = n;
        this.edges = edges;
        this.k = k;

        DSU dsu = new DSU(n);

        /*
         * Mandatory edges must all be present.
         *
         * If they form a cycle, no valid spanning tree exists.
         */
        int minMandatory = Integer.MAX_VALUE;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int strength = edge[2];
            int must = edge[3];

            if (must == 1) {
                minMandatory = Math.min(minMandatory, strength);

                if (!dsu.union(u, v)) {
                    return -1;
                }
            }
        }

        /*
         * Check whether the complete graph is connected at all.
         */
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        if (dsu.components != 1) {
            return -1;
        }

        /*
         * If there are mandatory edges, the answer cannot exceed
         * their minimum strength because mandatory edges cannot
         * be upgraded.
         *
         * If there are no mandatory edges, we can potentially
         * reach 2 * maxStrength.
         */
        int maxStrength = 0;

        for (int[] edge : edges) {
            maxStrength = Math.max(maxStrength, edge[2]);
        }

        int right;

        if (minMandatory == Integer.MAX_VALUE) {
            right = maxStrength * 2;
        } else {
            right = minMandatory;
        }

        int left = 1;

        /*
         * Binary search for the maximum feasible stability.
         */
        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (canBuild(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}