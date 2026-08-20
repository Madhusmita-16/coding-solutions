import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int islands = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == '1') {

                    islands++;

                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    grid[i][j] = '0';

                    while (!queue.isEmpty()) {

                        int[] cell = queue.poll();

                        int row = cell[0];
                        int col = cell[1];

                        for (int[] dir : directions) {

                            int newRow = row + dir[0];
                            int newCol = col + dir[1];

                            if (newRow >= 0 && newRow < m &&
                                newCol >= 0 && newCol < n &&
                                grid[newRow][newCol] == '1') {

                                grid[newRow][newCol] = '0';
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }

        return islands;
    }
}