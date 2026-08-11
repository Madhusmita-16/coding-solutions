class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {

        // Check 0°, 90°, 180°, and 270°
        for (int rotation = 0; rotation < 4; rotation++) {

            if (isSame(mat, target)) {
                return true;
            }

            rotate(mat);
        }

        return false;
    }

    // Rotate matrix 90 degrees clockwise in-place
    private void rotate(int[][] mat) {
        int n = mat.length;

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // Reverse every row
        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;

                left++;
                right--;
            }
        }
    }

    // Compare two matrices
    private boolean isSame(int[][] a, int[][] b) {
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] != b[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}