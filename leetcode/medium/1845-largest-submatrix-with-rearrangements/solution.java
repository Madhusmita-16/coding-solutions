import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] prev = new int[n];
        int ans = 0;

        for (int r = 0; r < m; r++) {

            int[] curr = new int[n];

            // Calculate consecutive 1s ending at this row.
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 1) {
                    curr[c] = prev[c] + 1;
                } else {
                    curr[c] = 0;
                }
            }

            // Sort heights in ascending order.
            int[] sorted = curr.clone();
            Arrays.sort(sorted);

            /*
             * Since sorted[] is ascending:
             *
             * sorted[i] is the height shared by
             * all columns from i to n-1.
             *
             * Width = n - i
             */
            for (int i = 0; i < n; i++) {
                int height = sorted[i];
                int width = n - i;

                ans = Math.max(ans, height * width);
            }

            prev = curr;
        }

        return ans;
    }
}