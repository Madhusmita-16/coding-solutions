class Solution {
    public int maxArea(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] dp = new int[m][n];
        int maxK = 0;

        // dp[r][c] = largest all-1 square ending at (r, c)
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 1) {
                    if (r == 0 || c == 0) {
                        dp[r][c] = 1;
                    } else {
                        dp[r][c] = 1 + Math.min(
                            dp[r - 1][c],
                            Math.min(
                                dp[r][c - 1],
                                dp[r - 1][c - 1]
                            )
                        );
                    }

                    maxK = Math.max(maxK, dp[r][c]);
                }
            }
        }

        int low = 1;
        int high = maxK;
        int best = 0;

        while (low <= high) {
            int k = low + (high - low) / 2;

            if (canPlaceTwo(dp, m, n, k)) {
                best = k;
                low = k + 1;
            } else {
                high = k - 1;
            }
        }

        return best * best;
    }

    private boolean canPlaceTwo(int[][] dp, int m, int n, int k) {

        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        boolean found = false;

        for (int r = 0; r + k <= m; r++) {
            for (int c = 0; c + k <= n; c++) {

                int bottom = r + k - 1;
                int right = c + k - 1;

                // Not a valid k x k square
                if (dp[bottom][right] < k) {
                    continue;
                }

                // This is the first valid square
                if (!found) {
                    minRow = maxRow = r;
                    minCol = maxCol = c;
                    found = true;
                    continue;
                }

                /*
                 * Check whether current square is completely
                 * separated from at least one previous square.
                 */

                // Previous square is completely above
                if (minRow + k <= r) {
                    return true;
                }

                // Previous square is completely below
                if (maxRow >= r + k) {
                    return true;
                }

                // Previous square is completely left
                if (minCol + k <= c) {
                    return true;
                }

                // Previous square is completely right
                if (maxCol >= c + k) {
                    return true;
                }

                // Store current square's position
                minRow = Math.min(minRow, r);
                maxRow = Math.max(maxRow, r);
                minCol = Math.min(minCol, c);
                maxCol = Math.max(maxCol, c);
            }
        }

        return false;
    }
}