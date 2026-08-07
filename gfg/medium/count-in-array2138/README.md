# Sequences where Adjacent Divide

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two positive integer  **n**  and  **m.**  Find the number of arrays of size n that can be formed such that:

- Each element is in the range [1, m].
- All adjacent are such that one of them divide the another i.e element Ai divides Ai + 1 or Ai+1 divides Ai.

 **Examples:** 

```
Input: n = 3, m = 3
Output : 17
Explanation: The possible arrays are [1, 1, 1], [1, 1, 2], [1, 1, 3], [1, 2, 1], [1, 2, 2], [1, 3, 1], [1, 3, 3], [2, 1, 1], [2, 1, 2], [2, 1, 3], [2, 2, 1], [2, 2, 2], [3, 1, 1], [3, 1, 2], [3, 1, 3], [3, 3, 1] and [3, 3, 3].

```

```
Input: n = 1, m = 10 
Output: 10
Explanation: The possible arrays are [1], [2], [3], [4], [5], [6], [7], [8], [9] and [10].
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T18:35:43.806Z  

```java
class Solution {
    public int count(int n, int m) {
        // dp[x] = number of valid arrays ending with x
        long[] dp = new long[m + 1];

        // Arrays of length 1: [1], [2], ..., [m]
        for (int x = 1; x <= m; x++) {
            dp[x] = 1;
        }

        // Build arrays of length 2 to n
        for (int len = 2; len <= n; len++) {
            long[] next = new long[m + 1];

            for (int x = 1; x <= m; x++) {
                for (int y = 1; y <= m; y++) {

                    // x and y are adjacent and one divides the other
                    if (x % y == 0 || y % x == 0) {
                        next[y] += dp[x];
                    }
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int x = 1; x <= m; x++) {
            ans += dp[x];
        }

        return (int) ans;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/count-in-array2138/1)