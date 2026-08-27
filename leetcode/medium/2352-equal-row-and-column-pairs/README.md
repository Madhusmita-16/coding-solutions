# Equal Row and Column Pairs

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a  **0-indexed**  `n x n` integer matrix `grid`,  *return the number of pairs* `(ri, cj)` *such that row* `ri` *and column* `cj` *are equal*.

A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).

 

 **Example 1:** 

```
Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]

```

 **Example 2:** 

```
Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]

```

 

 **Constraints:** 

- n == grid.length == grid[i].length
- 1 <= n <= 200
- 1 <= grid[i][j] <= 105

## Solution

**Language:** Java  
**Runtime:** 39 ms (beats 33.64%)  
**Memory:** 55.7 MB (beats 14.14%)  
**Submitted:** 2026-08-27T08:31:09.040Z  

```java
import java.util.*;

class Solution {
    public int equalPairs(int[][] grid) {

        int n = grid.length;
        Map<String, Integer> map = new HashMap<>();

        // Store all rows
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();

            for (int j = 0; j < n; j++) {
                row.append(grid[i][j]).append(",");
            }

            String key = row.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int count = 0;

        // Check all columns
        for (int j = 0; j < n; j++) {
            StringBuilder column = new StringBuilder();

            for (int i = 0; i < n; i++) {
                column.append(grid[i][j]).append(",");
            }

            String key = column.toString();

            if (map.containsKey(key)) {
                count += map.get(key);
            }
        }

        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/equal-row-and-column-pairs/)