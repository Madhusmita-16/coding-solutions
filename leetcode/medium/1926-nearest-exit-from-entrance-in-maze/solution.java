import java.util.*;

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {

        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int startRow = entrance[0];
        int startCol = entrance[1];

        queue.offer(new int[]{startRow, startCol, 0});

        // Mark entrance as visited
        maze[startRow][startCol] = '+';

        int[][] directions = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
        };

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int steps = current[2];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check boundaries
                if (newRow < 0 || newRow >= m ||
                    newCol < 0 || newCol >= n) {
                    continue;
                }

                // Cannot enter walls or already visited cells
                if (maze[newRow][newCol] == '+') {
                    continue;
                }

                int newSteps = steps + 1;

                // Mark visited
                maze[newRow][newCol] = '+';

                // Border cell = exit
                if (newRow == 0 || newRow == m - 1 ||
                    newCol == 0 || newCol == n - 1) {
                    return newSteps;
                }

                queue.offer(new int[]{newRow, newCol, newSteps});
            }
        }

        return -1;
    }
}