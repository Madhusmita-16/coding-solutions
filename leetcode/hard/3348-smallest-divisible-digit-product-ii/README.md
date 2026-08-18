# Smallest Divisible Digit Product II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a string `num` which represents a  **positive**  integer, and an integer `t`.

A number is called  **zero-free**  if  *none*  of its digits are 0.

Return a string representing the  **smallest**   **zero-free**  number greater than or equal to `num` such that the  **product of its digits**  is divisible by `t`. If no such number exists, return `"-1"`.

 

 **Example 1:** 

 **Input:**  num = "1234", t = 256

 **Output:**  "1488"

 **Explanation:** 

The smallest zero-free number that is greater than 1234 and has the product of its digits divisible by 256 is 1488, with the product of its digits equal to 256.

 **Example 2:** 

 **Input:**  num = "12355", t = 50

 **Output:**  "12355"

 **Explanation:** 

12355 is already zero-free and has the product of its digits divisible by 50, with the product of its digits equal to 150.

 **Example 3:** 

 **Input:**  num = "11111", t = 26

 **Output:**  "-1"

 **Explanation:** 

No number greater than 11111 has the product of its digits divisible by 26.

 

 **Constraints:** 

- 2 <= num.length <= 2 * 105
- num consists only of digits in the range ['0', '9'].
- num does not contain leading zeros.
- 1 <= t <= 1014

## Solution

**Language:** Java  
**Runtime:** 39 ms (beats 37.49%)  
**Memory:** 52.6 MB (beats 21.31%)  
**Submitted:** 2026-08-18T08:13:19.714Z  

```java
class Solution {

    private static final int INF = 1_000_000;

    // Factors: 2, 3, 5, 7
    private final int[][] factors = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    private int[][] min23;

    public String smallestNumber(String num, long t) {

        // ---------------------------------------------------------
        // 1. Factorize t
        // ---------------------------------------------------------
        int[] need = new int[4];
        int[] primes = {2, 3, 5, 7};

        long x = t;

        for (int i = 0; i < 4; i++) {
            while (x % primes[i] == 0) {
                need[i]++;
                x /= primes[i];
            }
        }

        // t contains a prime factor other than 2,3,5,7
        if (x != 1) {
            return "-1";
        }

        // ---------------------------------------------------------
        // 2. DP for factors of 2 and 3
        // ---------------------------------------------------------
        int max2 = need[0];
        int max3 = need[1];

        min23 = new int[max2 + 1][max3 + 1];

        for (int i = 0; i <= max2; i++) {
            java.util.Arrays.fill(min23[i], INF);
        }

        min23[0][0] = 0;

        int[] usefulDigits = {2, 3, 4, 6, 8, 9};

        for (int a = 0; a <= max2; a++) {
            for (int b = 0; b <= max3; b++) {

                if (min23[a][b] == INF) {
                    continue;
                }

                for (int d : usefulDigits) {

                    int na = Math.min(max2, a + factors[d][0]);
                    int nb = Math.min(max3, b + factors[d][1]);

                    min23[na][nb] = Math.min(
                        min23[na][nb],
                        min23[a][b] + 1
                    );
                }
            }
        }

        int n = num.length();

        // ---------------------------------------------------------
        // 3. Prefix factor counts
        // ---------------------------------------------------------
        int[][] prefix = new int[4][n + 1];

        // zeroPrefix[i] = whether num[0 ... i-1] contains zero
        boolean[] zeroPrefix = new boolean[n + 1];

        for (int i = 0; i < n; i++) {

            int d = num.charAt(i) - '0';

            for (int p = 0; p < 4; p++) {
                prefix[p][i + 1] =
                    prefix[p][i] + factors[d][p];
            }

            zeroPrefix[i + 1] =
                zeroPrefix[i] || d == 0;
        }

        // ---------------------------------------------------------
        // 4. Is num itself valid?
        // ---------------------------------------------------------
        if (!zeroPrefix[n] &&
            productCanReach(
                0,
                need,
                prefix[0][n],
                prefix[1][n],
                prefix[2][n],
                prefix[3][n])) {

            return num;
        }

        // ---------------------------------------------------------
        // 5. Find smallest same-length number > num
        // ---------------------------------------------------------
        for (int i = n - 1; i >= 0; i--) {

            // IMPORTANT:
            // If prefix already contains zero, we cannot use it.
            if (zeroPrefix[i]) {
                continue;
            }

            int have2 = prefix[0][i];
            int have3 = prefix[1][i];
            int have5 = prefix[2][i];
            int have7 = prefix[3][i];

            int original = num.charAt(i) - '0';

            for (int d = original + 1; d <= 9; d++) {

                // d is automatically non-zero because original >= 0
                // and d starts at original + 1.
                int c2 = have2 + factors[d][0];
                int c3 = have3 + factors[d][1];
                int c5 = have5 + factors[d][2];
                int c7 = have7 + factors[d][3];

                int remaining = n - i - 1;

                if (!productCanReach(
                        remaining,
                        need,
                        c2,
                        c3,
                        c5,
                        c7)) {
                    continue;
                }

                char[] result = new char[n];

                // Copy valid zero-free prefix
                for (int j = 0; j < i; j++) {
                    result[j] = num.charAt(j);
                }

                result[i] = (char) ('0' + d);

                fillSuffix(
                    result,
                    i + 1,
                    need,
                    c2,
                    c3,
                    c5,
                    c7
                );

                return new String(result);
            }
        }

        // ---------------------------------------------------------
        // 6. If no same-length answer, try n+1, n+2, ...
        // ---------------------------------------------------------
        int minExtra =
            need[2] +
            need[3] +
            min23[need[0]][need[1]];

        for (int len = n + 1;
             len <= n + minExtra + 1;
             len++) {

            if (!productCanReach(
                    len,
                    need,
                    0,
                    0,
                    0,
                    0)) {
                continue;
            }

            char[] result = new char[len];

            // Smallest possible first digit
            for (int d = 1; d <= 9; d++) {

                int c2 = factors[d][0];
                int c3 = factors[d][1];
                int c5 = factors[d][2];
                int c7 = factors[d][3];

                if (!productCanReach(
                        len - 1,
                        need,
                        c2,
                        c3,
                        c5,
                        c7)) {
                    continue;
                }

                result[0] = (char) ('0' + d);

                fillSuffix(
                    result,
                    1,
                    need,
                    c2,
                    c3,
                    c5,
                    c7
                );

                return new String(result);
            }
        }

        return "-1";
    }

    // -------------------------------------------------------------
    // Can the remaining slots satisfy all missing prime factors?
    // -------------------------------------------------------------
    private boolean productCanReach(
            int slots,
            int[] need,
            int have2,
            int have3,
            int have5,
            int have7) {

        int rem2 = Math.max(0, need[0] - have2);
        int rem3 = Math.max(0, need[1] - have3);
        int rem5 = Math.max(0, need[2] - have5);
        int rem7 = Math.max(0, need[3] - have7);

        // Only digit 5 supplies factor 5.
        // Only digit 7 supplies factor 7.
        int mandatory = rem5 + rem7;

        if (mandatory > slots) {
            return false;
        }

        int needed23 = min23[rem2][rem3];

        if (needed23 == INF) {
            return false;
        }

        return mandatory + needed23 <= slots;
    }

    // -------------------------------------------------------------
    // Fill suffix with lexicographically smallest possible digits
    // -------------------------------------------------------------
    private void fillSuffix(
            char[] result,
            int pos,
            int[] need,
            int have2,
            int have3,
            int have5,
            int have7) {

        while (pos < result.length) {

            for (int d = 1; d <= 9; d++) {

                int c2 = have2 + factors[d][0];
                int c3 = have3 + factors[d][1];
                int c5 = have5 + factors[d][2];
                int c7 = have7 + factors[d][3];

                int remaining = result.length - pos - 1;

                if (productCanReach(
                        remaining,
                        need,
                        c2,
                        c3,
                        c5,
                        c7)) {

                    result[pos] = (char) ('0' + d);

                    have2 = c2;
                    have3 = c3;
                    have5 = c5;
                    have7 = c7;

                    pos++;
                    break;
                }
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-divisible-digit-product-ii/)