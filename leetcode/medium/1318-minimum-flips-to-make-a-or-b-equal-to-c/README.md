# Minimum Flips to Make a OR b Equal to c

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given 3 positives numbers `a`, `b` and `c`. Return the minimum flips required in some bits of `a` and `b` to make ( `a` OR `b` == `c` ). (bitwise OR operation).
Flip operation consists of change  **any**  single bit 1 to 0 or change the bit 0 to 1 in their binary representation.

 

 **Example 1:** 

```
Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1, b = 4, c = 5 such that (a OR b == c)
```

 **Example 2:** 

```
Input: a = 4, b = 2, c = 7
Output: 1

```

 **Example 3:** 

```
Input: a = 1, b = 2, c = 3
Output: 0

```

 

 **Constraints:** 

- 1 <= a <= 10^9
- 1 <= b <= 10^9
- 1 <= c <= 10^9

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.2 MB (beats 40.53%)  
**Submitted:** 2026-08-19T14:45:20.499Z  

```java
class Solution {
    public int minFlips(int a, int b, int c) {

        int flips = 0;

        while (a > 0 || b > 0 || c > 0) {

            int bitA = a & 1;
            int bitB = b & 1;
            int bitC = c & 1;

            if (bitC == 1) {
                if (bitA == 0 && bitB == 0) {
                    flips++;
                }
            } else {
                flips += bitA + bitB;
            }

            a >>= 1;
            b >>= 1;
            c >>= 1;
        }

        return flips;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/)