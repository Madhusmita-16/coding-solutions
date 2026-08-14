# Q3. K-th Smallest in Lexicographical Order

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given two integers `n` and `k`, return  *the*  `kth`  *lexicographically smallest integer in the range*  `[1, n]`.

 

 **Example 1:** 

```
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.

```

 **Example 2:** 

```
Input: n = 1, k = 1
Output: 1

```

 

 **Constraints:** 

- 1 <= k <= n <= 109

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.2 MB (beats 38.51%)  
**Submitted:** 2026-08-14T17:45:26.431Z  

```java
class Solution {
    public int findKthNumber(int n, int k) {

        int current = 1;
        k--; // We are already at the first number: 1

        while (k > 0) {

            long steps = countSteps(n, current, current + 1);

            if (steps <= k) {
                // Skip this entire prefix subtree
                current++;
                k -= steps;
            } else {
                // Go deeper into this prefix
                current *= 10;
                k--;
            }
        }

        return current;
    }

    private long countSteps(long n, long prefix1, long prefix2) {

        long steps = 0;

        while (prefix1 <= n) {
            steps += Math.min(n + 1, prefix2) - prefix1;

            prefix1 *= 10;
            prefix2 *= 10;
        }

        return steps;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/)