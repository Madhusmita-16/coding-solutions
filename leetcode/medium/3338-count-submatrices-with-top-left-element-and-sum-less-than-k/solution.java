class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[] cols = new int[n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            int sum = 0;

            for (int j = 0; j < n; j++) {
                // Sum of this column from row 0 to row i
                cols[j] += grid[i][j];

                // Sum of rectangle (0,0) -> (i,j)
                sum += cols[j];

                if (sum <= k) {
                    count++;
                }
            }
        }

        return count;
    }
}