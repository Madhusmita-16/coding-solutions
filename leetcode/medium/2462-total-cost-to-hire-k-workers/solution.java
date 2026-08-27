import java.util.*;

class Solution {
    public long totalCost(int[] costs, int k, int candidates) {

        int n = costs.length;

        // Each element: [cost, index]
        PriorityQueue<int[]> left =
            new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });

        PriorityQueue<int[]> right =
            new PriorityQueue<>((a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });

        int l = 0;
        int r = n - 1;

        // Add first candidates
        while (l <= r && left.size() < candidates) {
            left.offer(new int[]{costs[l], l});
            l++;
        }

        // Add last candidates without overlapping
        while (l <= r && right.size() < candidates) {
            right.offer(new int[]{costs[r], r});
            r--;
        }

        long total = 0;

        for (int i = 0; i < k; i++) {

            // Choose the cheaper worker
            if (right.isEmpty() ||
                (!left.isEmpty() &&
                 (left.peek()[0] < right.peek()[0] ||
                  (left.peek()[0] == right.peek()[0] &&
                   left.peek()[1] < right.peek()[1])))) {

                int[] worker = left.poll();
                total += worker[0];

                // Add next worker from the middle
                if (l <= r) {
                    left.offer(new int[]{costs[l], l});
                    l++;
                }

            } else {

                int[] worker = right.poll();
                total += worker[0];

                // Add next worker from the middle
                if (l <= r) {
                    right.offer(new int[]{costs[r], r});
                    r--;
                }
            }
        }

        return total;
    }
}