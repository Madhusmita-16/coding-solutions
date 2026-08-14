import java.util.*;

class Solution {
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        int q = queries.length;
        int[] ans = new int[q];
        Arrays.fill(ans, -1);

        // Queries grouped by the larger index
        List<int[]>[] waiting = new ArrayList[heights.length];

        for (int i = 0; i < q; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            // Same building
            if (a == b) {
                ans[i] = a;
                continue;
            }

            // Normalize: a < b
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // Alice can directly move to Bob's building
            if (heights[a] < heights[b]) {
                ans[i] = b;
            } else {
                if (waiting[b] == null) {
                    waiting[b] = new ArrayList<>();
                }

                // Store: [height needed, query index]
                waiting[b].add(new int[]{heights[a], i});
            }
        }

        /*
         * Monotonic stack of buildings to the right.
         * We maintain buildings in decreasing height order.
         */
        List<Integer> stack = new ArrayList<>();

        for (int i = heights.length - 1; i >= 0; i--) {

            // Process queries whose right endpoint is i
            if (waiting[i] != null) {
                for (int[] query : waiting[i]) {
                    int requiredHeight = query[0];
                    int queryIndex = query[1];

                    // Find leftmost building with height > requiredHeight
                    int left = 0;
                    int right = stack.size() - 1;
                    int result = -1;

                    while (left <= right) {
                        int mid = left + (right - left) / 2;

                        if (heights[stack.get(mid)] > requiredHeight) {
                            result = stack.get(mid);
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }

                    ans[queryIndex] = result;
                }
            }

            /*
             * Add current building to monotonic stack.
             * Remove buildings that can never be the leftmost answer.
             */
            while (!stack.isEmpty()
                    && heights[stack.get(stack.size() - 1)] <= heights[i]) {
                stack.remove(stack.size() - 1);
            }

            stack.add(i);
        }

        return ans;
    }
}