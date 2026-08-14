import java.util.*;

class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] height = new int[m][n];

        // -1 means unvisited
        for (int i = 0; i < m; i++) {
            Arrays.fill(height[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Add all water cells as starting points
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // Multi-source BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    height[newRow][newCol] == -1) {

                    height[newRow][newCol] = height[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return height;
    }
}