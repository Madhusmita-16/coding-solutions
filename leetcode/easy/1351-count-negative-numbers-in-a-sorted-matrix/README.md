# Count Negative Numbers in a Sorted Matrix

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a `m x n` matrix `grid` which is sorted in non-increasing order both row-wise and column-wise, return  *the number of  **negative**  numbers in*  `grid`.

 

 **Example 1:** 

```
Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
Output: 8
Explanation: There are 8 negatives number in the matrix.

```

 **Example 2:** 

```
Input: grid = [[3,2],[1,0]]
Output: 0

```

 

 **Constraints:** 

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 100
- -100 <= grid[i][j] <= 100

 

 **Follow up:**  Could you find an `O(n + m)` solution?

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 46.9 MB (beats 65.81%)  
**Submitted:** 2026-08-30T09:48:09.567Z  

```java
class Solution {
    public int countNegatives(int[][] grid) {
        int count = 0;

        for (int[] row : grid) {
            int low = 0;
            int high = row.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (row[mid] < 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // low is the first negative element
            count += row.length - low;
        }

        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/)