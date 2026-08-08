# Count Submatrices with Top-Left Element and Sum Less Than k

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a  **0-indexed**  integer matrix `grid` and an integer `k`.

Return  *the  **number**  of submatrices that contain the top-left element of the*  `grid`,  *and have a sum less than or equal to* `k`.

 

 **Example 1:** 

```
Input: grid = [[7,6,3],[6,6,1]], k = 18
Output: 4
Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 18.
```

 **Example 2:** 

```
Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
Output: 6
Explanation: There are only 6 submatrices, shown in the image above, that contain the top-left element of grid, and have a sum less than or equal to 20.

```

 

 **Constraints:** 

- m == grid.length
- n == grid[i].length
- 1 <= n, m <= 1000
- 0 <= grid[i][j] <= 1000
- 1 <= k <= 109

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 96.33%)  
**Memory:** 161.3 MB (beats 69.03%)  
**Submitted:** 2026-08-08T12:29:57.819Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/count-submatrices-with-top-left-element-and-sum-less-than-k/)