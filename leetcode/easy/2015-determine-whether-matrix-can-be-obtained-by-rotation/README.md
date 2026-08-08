# Determine Whether Matrix Can Be Obtained By Rotation

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given two `n x n` binary matrices `mat` and `target`, return `true` *if it is possible to make* `mat` *equal to* `target` *by  **rotating*** `mat` *in  **90-degree increments**, or* `false` *otherwise.* 

 

 **Example 1:** 

```
Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.

```

 **Example 2:** 

```
Input: mat = [[0,1],[1,1]], target = [[1,0],[0,1]]
Output: false
Explanation: It is impossible to make mat equal to target by rotating mat.

```

 **Example 3:** 

```
Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
Output: true
Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.

```

 

 **Constraints:** 

- n == mat.length == target.length
- n == mat[i].length == target[i].length
- 1 <= n <= 10
- mat[i][j] and target[i][j] are either 0 or 1.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.5 MB (beats 77.22%)  
**Submitted:** 2026-08-08T12:35:46.977Z  

```java
class Solution {

    public boolean findRotation(int[][] mat, int[][] target) {
        // Check original matrix
        if (isSame(mat, target)) {
            return true;
        }

        // Try 90°, 180°, and 270° rotations
        for (int rotation = 0; rotation < 3; rotation++) {
            rotate(mat);

            if (isSame(mat, target)) {
                return true;
            }
        }

        return false;
    }

    // Rotate matrix 90 degrees clockwise in-place
    private void rotate(int[][] mat) {
        int n = mat.length;

        // Transpose the matrix
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
```

---

[View on LeetCode](https://leetcode.com/problems/determine-whether-matrix-can-be-obtained-by-rotation/)