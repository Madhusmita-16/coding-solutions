import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {

        int[] degree = new int[n + 1];

        // Store undirected edges
        Set<Long> edgeSet = new HashSet<>();

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            degree[u]++;
            degree[v]++;

            edgeSet.add(getKey(u, v));
        }

        // Find odd-degree vertices
        List<Integer> odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if ((degree[i] & 1) == 1) {
                odd.add(i);
            }
        }

        // Already all even
        if (odd.size() == 0) {
            return true;
        }

        // More than 4 odd vertices cannot be fixed with 2 edges
        if (odd.size() > 4) {
            return false;
        }

        // ------------------------------------------------
        // Case 1: Exactly 2 odd vertices
        // ------------------------------------------------
        if (odd.size() == 2) {

            int a = odd.get(0);
            int b = odd.get(1);

            // Add one edge directly between a and b
            if (!hasEdge(a, b, edgeSet)) {
                return true;
            }

            // a -- x -- b
            // Add two edges through some third vertex x
            for (int x = 1; x <= n; x++) {

                if (x == a || x == b) {
                    continue;
                }

                if (!hasEdge(a, x, edgeSet)
                        && !hasEdge(b, x, edgeSet)) {
                    return true;
                }
            }

            return false;
        }

        // ------------------------------------------------
        // Case 2: Exactly 4 odd vertices
        // ------------------------------------------------
        if (odd.size() == 4) {

            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            // Pair: (a,b) and (c,d)
            if (!hasEdge(a, b, edgeSet)
                    && !hasEdge(c, d, edgeSet)) {
                return true;
            }

            // Pair: (a,c) and (b,d)
            if (!hasEdge(a, c, edgeSet)
                    && !hasEdge(b, d, edgeSet)) {
                return true;
            }

            // Pair: (a,d) and (b,c)
            if (!hasEdge(a, d, edgeSet)
                    && !hasEdge(b, c, edgeSet)) {
                return true;
            }

            return false;
        }

        return false;
    }

    private boolean hasEdge(int u, int v, Set<Long> edgeSet) {
        return edgeSet.contains(getKey(u, v));
    }

    // Since graph is undirected, (u,v) == (v,u)
    private long getKey(int u, int v) {

        if (u > v) {
            int temp = u;
            u = v;
            v = temp;
        }

        return ((long) u << 32) | (v & 0xffffffffL);
    }
}