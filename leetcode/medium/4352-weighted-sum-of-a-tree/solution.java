import java.util.*;

class Solution {
    public long weightedSum(int[] parent, int[] nums) {
        int n = parent.length;

        int[] depth = new int[n];
        depth[0] = 1;

        int[] path = new int[n];
        int height = 1;

        // Calculate depths even when parent index > child index
        for (int i = 1; i < n; i++) {

            int cur = i;
            int size = 0;

            // Go upward until we reach a node whose depth is known
            while (depth[cur] == 0) {
                path[size++] = cur;
                cur = parent[cur];
            }

            // Fill depths from the known parent downward
            while (size > 0) {
                int node = path[--size];
                depth[node] = depth[parent[node]] + 1;
            }
        }

        // Find tree height
        for (int i = 0; i < n; i++) {
            height = Math.max(height, depth[i]);
        }

        // Calculate weighted sum
        long answer = 0;

        for (int i = 0; i < n; i++) {
            answer += (long) nums[i] * (height - depth[i] + 1);
        }

        return answer;
    }
}