class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] d1 = new long[m + 1][n + 2];
        long[][] d2 = new long[m + 1][n + 2];

        // Diagonal prefix sums
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                d1[i][j] = d1[i - 1][j - 1] + grid[i - 1][j - 1];
                d2[i][j] = d2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        long[] best = {
            Long.MIN_VALUE,
            Long.MIN_VALUE,
            Long.MIN_VALUE
        };

        // Enumerate top vertex and rhombus size
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {

                // Single cell is also a rhombus
                add(best, grid[r][c]);

                for (int k = 1;
                     r + 2 * k < m &&
                     c - k >= 0 &&
                     c + k < n;
                     k++) {

                    int ux = r,       uy = c;
                    int lx = r + k,   ly = c - k;
                    int rx = r + k,   ry = c + k;
                    int dx = r + 2*k, dy = c;

                    long topLeft =
                        d2[lx + 1][ly + 1] - d2[ux][uy + 2];

                    long topRight =
                        d1[rx + 1][ry + 1] - d1[ux][uy];

                    long leftBottom =
                        d1[dx + 1][dy + 1] - d1[lx][ly];

                    long rightBottom =
                        d2[dx + 1][dy + 1] - d2[rx][ry + 2];

                    long sum = topLeft
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

        int size = 0;

        while (size < 3 && best[size] != Long.MIN_VALUE) {
            size++;
        }

        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = (int) best[i];
        }

        return result;
    }

    private void add(long[] best, long value) {
        // Ignore duplicate values
        if (best[0] == value ||
            best[1] == value ||
            best[2] == value) {
            return;
        }

        if (value > best[0]) {
            best[2] = best[1];
            best[1] = best[0];
            best[0] = value;
        } else if (value > best[1]) {
            best[2] = best[1];
            best[1] = value;
        } else if (value > best[2]) {
            best[2] = value;
        }
    }
}