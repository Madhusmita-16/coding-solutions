# Count Palindromic Strings with Constraints

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two integers  **n**   and  **k**, consider an alphabet consisting of the first k lowercase English letters. Find the number of palindromic strings whose length is less than or equal to n, such that:

- Every character in the string belongs to the given alphabet.
- No character appears more than twice in the string.

 **Note:** Since the answer can be very large, return it modulo  **10^9+7**.

 **Examples:** 

```
Input: n = 3, k = 2
Output: 6
Explanation: The possible strings are: "a", "b", "aa", "bb", "aba", "bab".
```

```
Input: n = 4, k = 3
Output: 18
Explanation: The possible strings are:"a", "b", "c", "aa", "bb", "cc", "aba", "aca", "bab", "bcb", "cac", "cbc", "abba", "acca", "baab", "bccb", "caac", "cbbc". 
```

 **Constraints:** 
1 ≤ k ≤ 26
1 ≤ n ≤ 52
n ≤ 2*k

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-01T05:32:20.723Z  

```java
class Solution {
    public int palindromicStrings(int n, int k) {
        final long MOD = 1000000007L;

        long ans = 0;
        long ways = 1;

        for (int half = 0; 2 * half <= n; half++) {

            if (half > 0) {
                ways = (ways * (k - half + 1)) % MOD;
            }

            // Even length
            if (half > 0) {
                ans = (ans + ways) % MOD;
            }

            // Odd length
            if (2 * half + 1 <= n) {
                ans = (ans + ways * (k - half)) % MOD;
            }
        }

        return (int) ans;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/number-of-palindromic-strings2706/1)