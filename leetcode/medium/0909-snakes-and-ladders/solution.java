import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        int target = n * n;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[target + 1];

        queue.offer(1);
        visited[1] = true;

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int current = queue.poll();

                if (current == target) {
                    return moves;
                }

                // Try all possible dice rolls
                for (int dice = 1; dice <= 6; dice++) {

                    int next = current + dice;

                    if (next > target) {
                        break;
                    }

                    // Convert square number to board coordinates
                    int[] position = getPosition(next, n);

                    int row = position[0];
                    int col = position[1];

                    // Take snake or ladder if present
                    if (board[row][col] != -1) {
                        next = board[row][col];
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private int[] getPosition(int square, int n) {

        // Convert square number to zero-based index
        int index = square - 1;

        // Row counted from bottom
        int row = n - 1 - index / n;

        // Column within the row
        int col = index % n;

        // Every alternate row is reversed
        if ((n - row) % 2 == 0) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }
}