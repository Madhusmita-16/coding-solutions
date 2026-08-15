class Solution {
    public Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    private Node build(int[][] grid, int row, int col, int size) {

        // Check whether the current region contains the same value
        boolean same = true;
        int value = grid[row][col];

        for (int i = row; i < row + size && same; i++) {
            for (int j = col; j < col + size; j++) {
                if (grid[i][j] != value) {
                    same = false;
                    break;
                }
            }
        }

        // All values are the same -> leaf node
        if (same) {
            return new Node(value == 1, true);
        }

        // Divide into four sub-grids
        int half = size / 2;

        Node topLeft = build(grid, row, col, half);
        Node topRight = build(grid, row, col + half, half);
        Node bottomLeft = build(grid, row + half, col, half);
        Node bottomRight = build(grid, row + half, col + half, half);

        return new Node(
            true,           // val can be anything for non-leaf nodes
            false,
            topLeft,
            topRight,
            bottomLeft,
            bottomRight
        );
    }
}