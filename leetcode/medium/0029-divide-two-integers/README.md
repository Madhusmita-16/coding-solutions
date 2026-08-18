# Divide Two Integers

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given two integers `dividend` and `divisor`, divide two integers  **without**  using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. For example, `8.345` would be truncated to `8`, and `-2.7335` would be truncated to `-2`.

Return  *the  **quotient**  after dividing* `dividend` *by* `divisor`.

 **Note:** Assume we are dealing with an environment that could only store integers within the  **32-bit**  signed integer range: `[−231, 231 − 1]`. For this problem, if the quotient is  **strictly greater than**  `231 - 1`, then return `231 - 1`, and if the quotient is  **strictly less than**  `-231`, then return `-231`.

 

 **Example 1:** 

```
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = 3.33333.. which is truncated to 3.

```

 **Example 2:** 

```
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = -2.33333.. which is truncated to -2.

```

 

 **Constraints:** 

- -231 <= dividend, divisor <= 231 - 1
- divisor != 0

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 94.56%)  
**Memory:** 42.6 MB (beats 69.13%)  
**Submitted:** 2026-08-18T14:42:39.044Z  

```java
class Solution {
    public int divide(int dividend, int divisor) {

        // Overflow case: -2^31 / -1 = 2^31
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine whether the result is negative
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to positive long values safely
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long quotient = 0;

        // Find the quotient using powers of 2
        while (a >= b) {

            long temp = b;
            long multiple = 1;

            // Keep doubling divisor while possible
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            // Subtract the largest possible multiple
            a -= temp;
            quotient += multiple;
        }

        if (negative) {
            quotient = -quotient;
        }

        // Clamp to 32-bit integer range
        if (quotient > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (quotient < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) quotient;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/divide-two-integers/)