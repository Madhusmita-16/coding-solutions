# Spiral Matrix II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a positive integer `n`, generate an `n x n` `matrix` filled with elements from `1` to `n2` in spiral order.

 

 **Example 1:** 

```
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

```

 **Example 2:** 

```
Input: n = 1
Output: [[1]]

```

 

 **Constraints:** 

- 1 <= n <= 20

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.6 MB (beats 95.88%)  
**Submitted:** 2026-08-18T15:16:04.574Z  

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int num = 1;

        while (top <= bottom && left <= right) {

            // Left → Right
            for (int j = left; j <= right; j++) {
                matrix[top][j] = num++;
            }
            top++;

            // Top → Bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // Right → Left
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = num++;
                }
                bottom--;
            }

            // Bottom → Top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/spiral-matrix-ii/)