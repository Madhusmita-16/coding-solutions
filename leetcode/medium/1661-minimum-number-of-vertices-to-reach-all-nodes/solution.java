import java.util.*;

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        int[] indegree = new int[n];

        // Calculate indegree of every vertex
        for (List<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);

            indegree[to]++;
        }

        List<Integer> result = new ArrayList<>();

        // Vertices with no incoming edge must be starting points
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                result.add(i);
            }
        }

        return result;
    }
}