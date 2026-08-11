# Largest Submatrix With Rearrangements

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a binary matrix `matrix` of size `m x n`, and you are allowed to rearrange the  **columns**  of the `matrix` in any order.

Return  *the area of the largest submatrix within* `matrix` *where  **every**  element of the submatrix is* `1` *after reordering the columns optimally.* 

 

 **Example 1:** 

```
Input: matrix = [[0,0,1],[1,1,1],[1,0,1]]
Output: 4
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 4.

```

 **Example 2:** 

```
Input: matrix = [[1,0,1,0,1]]
Output: 3
Explanation: You can rearrange the columns as shown above.
The largest submatrix of 1s, in bold, has an area of 3.

```

 **Example 3:** 

```
Input: matrix = [[1,1,0],[1,0,1]]
Output: 2
Explanation: Notice that you must rearrange entire columns, and there is no way to make a submatrix of 1s larger than an area of 2.

```

 

 **Constraints:** 

- m == matrix.length
- n == matrix[i].length
- 1 <= m * n <= 105
- matrix[i][j] is either 0 or 1.

## Solution

**Language:** Java  
**Runtime:** 21 ms (beats 11.19%)  
**Memory:** 110.1 MB (beats 99.05%)  
**Submitted:** 2026-08-11T15:16:31.163Z  

```java
import java.util.*;

class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] prev = new int[n];
        int ans = 0;

        for (int r = 0; r < m; r++) {

            int[] curr = new int[n];

            // Calculate consecutive 1s ending at this row
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == 1) {
                    curr[c] = prev[c] + 1;
                }
            }

            // Sort heights in ascending order
            int[] sorted = curr.clone();
            Arrays.sort(sorted);

            /*
             * sorted[i] is the height that can be
             * used by all columns from i to n - 1.
             *
             * Width = n - i
             */
            for (int i = 0; i < n; i++) {
                int height = sorted[i];
                int width = n - i;

                ans = Math.max(ans, height * width);
            }

            prev = curr;
        }

        return ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/largest-submatrix-with-rearrangements/)