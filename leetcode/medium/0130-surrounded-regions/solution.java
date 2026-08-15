import java.util.*;

class Solution {
    public void solve(char[][] board) {

        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Add boundary O's
        for (int i = 0; i < m; i++) {

            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
                board[i][0] = '#';
            }

            if (board[i][n - 1] == 'O') {
                queue.offer(new int[]{i, n - 1});
                board[i][n - 1] = '#';
            }
        }

        for (int j = 0; j < n; j++) {

            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
                board[0][j] = '#';
            }

            if (board[m - 1][j] == 'O') {
                queue.offer(new int[]{m - 1, j});
                board[m - 1][j] = '#';
            }
        }

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        // Mark all O's connected to the boundary
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];

            for (int[] dir : directions) {

                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < m &&
                    newCol >= 0 && newCol < n &&
                    board[newRow][newCol] == 'O') {

                    board[newRow][newCol] = '#';
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        // Capture surrounded regions
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
}