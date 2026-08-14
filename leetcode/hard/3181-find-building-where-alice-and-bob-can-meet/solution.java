import java.util.*;

class Solution {
    private int[] tree;
    private int[] heights;

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        this.heights = heights;

        int n = heights.length;
        tree = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);

        for (int i = 0; i < queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            // Same building
            if (a == b) {
                ans[i] = a;
                continue;
            }

            // Make a the smaller index
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // Alice can directly move to Bob's building
            if (heights[a] < heights[b]) {
                ans[i] = b;
                continue;
            }

            // Need the first building after b
            // whose height is greater than heights[a]
            ans[i] = findFirst(1, 0, n - 1, b + 1, heights[a]);
        }

        return ans;
    }

    // Build segment tree storing maximum height in each range
    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = heights[left];
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    /*
     * Find the leftmost index >= queryLeft
     * whose height > targetHeight.
     */
    private int findFirst(int node, int left, int right,
                          int queryLeft, int targetHeight) {

        // Range is completely before queryLeft
        if (right < queryLeft) {
            return -1;
        }

        // No value in this range can satisfy the condition
        if (tree[node] <= targetHeight) {
            return -1;
        }

        // Leaf node
        if (left == right) {
            return left;
        }

        int mid = left + (right - left) / 2;

        // Search left side first to get the leftmost index
        int result = findFirst(
            node * 2,
            left,
            mid,
            queryLeft,
            targetHeight
        );

        if (result != -1) {
            return result;
        }

        // Then search right side
        return findFirst(
            node * 2 + 1,
            mid + 1,
            right,
            queryLeft,
            targetHeight
        );
    }
}