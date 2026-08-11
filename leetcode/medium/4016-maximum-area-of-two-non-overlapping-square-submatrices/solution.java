class Solution {
    public int maxArea(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        // dp[r][c] = largest all-1 square ending at (r,c)
        int[][] dp = new int[m][n];

        int maxK = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 1) {
                    if (r == 0 || c == 0) {
                        dp[r][c] = 1;
                    } else {
                        dp[r][c] = 1 + Math.min(
                            dp[r - 1][c - 1],
                            Math.min(dp[r - 1][c], dp[r][c - 1])
                        );
                    }

                    maxK = Math.max(maxK, dp[r][c]);
                }
            }
        }

        int left = 1;
        int right = maxK;
        int answer = 0;

        while (left <= right) {
            int k = left + (right - left) / 2;

            if (canPlaceTwo(dp, m, n, k)) {
                answer = k;
                left = k + 1;
            } else {
                right = k - 1;
            }
        }

        return answer * answer;
    }

    private boolean canPlaceTwo(int[][] dp, int m, int n, int k) {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (int r = 0; r + k <= m; r++) {
            for (int c = 0; c + k <= n; c++) {

                int bottom = r + k - 1;
                int right = c + k - 1;

                // Check whether this k x k square contains only 1s
                if (dp[bottom][right] < k) {
                    continue;
                }

                /*
                 * If another square is:
                 *
                 * completely above:
                 * previousRow + k <= r
                 *
                 * completely below:
                 * r + k <= previousRow
                 *
                 * completely left:
                 * previousCol + k <= c
                 *
                 * completely right:
                 * c + k <= previousCol
                 */
                if (minRow + k <= r ||
                    maxRow >= r + k ||
                    minCol + k <= c ||
                    maxCol >= c + k) {
                    return true;
                }

                minRow = Math.min(minRow, r);
                maxRow = Math.max(maxRow, r);
                minCol = Math.min(minCol, c);
                maxCol = Math.max(maxCol, c);
            }
        }

        return false;
    }
}