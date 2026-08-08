class Solution {

    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        /*
         * d1[i][j]:
         * Sum along the diagonal going top-left.
         *
         * d2[i][j]:
         * Sum along the diagonal going top-right.
         *
         * Extra columns are used so j + 1 / j + 2
         * are always safe.
         */
        long[][] d1 = new long[m + 1][n + 2];
        long[][] d2 = new long[m + 1][n + 2];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                // Top-left -> current
                d1[i][j] =
                        d1[i - 1][j - 1]
                        + grid[i - 1][j - 1];

                // Top-right -> current
                d2[i][j] =
                        d2[i - 1][j + 1]
                        + grid[i - 1][j - 1];
            }
        }

        /*
         * best[0] = largest
         * best[1] = second largest
         * best[2] = third largest
         *
         * All must be DISTINCT.
         */
        long[] best = {
            Long.MIN_VALUE,
            Long.MIN_VALUE,
            Long.MIN_VALUE
        };

        /*
         * Choose the top vertex (r, c).
         *
         * k = distance from top vertex to left/right vertex.
         */
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                // A single cell is a valid rhombus.
                add(best, grid[r][c]);

                for (int k = 1;
                     r + 2 * k < m &&
                     c - k >= 0 &&
                     c + k < n;
                     k++) {

                    /*
                     * Vertices:
                     *
                     *             top
                     *              |
                     *          /       \
                     *       left       right
                     *          \       /
                     *              |
                     *            bottom
                     */

                    int ux = r;
                    int uy = c;

                    int lx = r + k;
                    int ly = c - k;

                    int rx = r + k;
                    int ry = c + k;

                    int dx = r + 2 * k;
                    int dy = c;

                    /*
                     * 1. Top -> Left
                     *
                     * d2[lx + 1][ly + 1]
                     *     -
                     * d2[ux][uy + 2]
                     */
                    long topLeft =
                            d2[lx + 1][ly + 1]
                            - d2[ux][uy + 2];

                    /*
                     * 2. Top -> Right
                     *
                     * d1[rx + 1][ry + 1]
                     *     -
                     * d1[ux][uy]
                     */
                    long topRight =
                            d1[rx + 1][ry + 1]
                            - d1[ux][uy];

                    /*
                     * 3. Left -> Bottom
                     *
                     * d1[dx + 1][dy + 1]
                     *     -
                     * d1[lx][ly]
                     */
                    long leftBottom =
                            d1[dx + 1][dy + 1]
                            - d1[lx][ly];

                    /*
                     * 4. Right -> Bottom
                     *
                     * d2[dx + 1][dy + 1]
                     *     -
                     * d2[rx][ry + 2]
                     */
                    long rightBottom =
                            d2[dx + 1][dy + 1]
                            - d2[rx][ry + 2];

                    /*
                     * Each corner is counted twice.
                     * Subtract each corner once.
                     */
                    long sum =
                            topLeft
                            + topRight
                            + leftBottom
                            + rightBottom
                            - grid[ux][uy]
                            - grid[dx][dy]
                            - grid[lx][ly]
                            - grid[rx][ry];

                    add(best, sum);
                }
            }
        }

        int count = 0;

        for (int i = 0; i < 3; i++) {
            if (best[i] != Long.MIN_VALUE) {
                count++;
            }
        }

        int[] answer = new int[count];

        for (int i = 0; i < count; i++) {
            answer[i] = (int) best[i];
        }

        return answer;
    }

    /*
     * Maintain the 3 largest DISTINCT values.
     */
    private void add(long[] best, long value) {

        // Duplicate -> ignore.
        if (best[0] == value ||
            best[1] == value ||
            best[2] == value) {
            return;
        }

        if (value > best[0]) {
            best[2] = best[1];
            best[1] = best[0];
            best[0] = value;
        }
        else if (value > best[1]) {
            best[2] = best[1];
            best[1] = value;
        }
        else if (value > best[2]) {
            best[2] = value;
        }
    }
}