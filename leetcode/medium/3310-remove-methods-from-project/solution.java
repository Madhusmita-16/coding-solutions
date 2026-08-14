import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // Build invocation graph
        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Find all suspicious methods reachable from k
        boolean[] suspicious = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        suspicious[k] = true;
        queue.offer(k);

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v : graph[u]) {
                if (!suspicious[v]) {
                    suspicious[v] = true;
                    queue.offer(v);
                }
            }
        }

        // If any non-suspicious method invokes a suspicious method,
        // the suspicious group cannot be removed.
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                // Cannot remove suspicious methods
                List<Integer> result = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    result.add(i);
                }

                return result;
            }
        }

        // All suspicious methods can be safely removed
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }

        return result;
    }
}