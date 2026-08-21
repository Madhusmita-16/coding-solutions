# Kth Smallest Amount With Single Denomination Combination

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `coins` representing coins of different denominations and an integer `k`.

You have an infinite number of coins of each denomination. However, you are  **not allowed**  to combine coins of different denominations.

Return the `kth`  **smallest**  amount that can be made using these coins.

 

 **Example 1:** 

 **Input:**  coins = [3,6,9], k = 3

 **Output:**  9

 **Explanation:**  The given coins can make the following amounts:
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
All of the coins combined produce: 3, 6,  **9**, 12, 15, etc.

 **Example 2:** 

 **Input:**  coins = [5,2], k = 7

 **Output:**  12

 **Explanation:**  The given coins can make the following amounts:
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
All of the coins combined produce: 2, 4, 5, 6, 8, 10,  **12**, 14, 15, etc.

 

 **Constraints:** 

- 1 <= coins.length <= 15
- 1 <= coins[i] <= 25
- 1 <= k <= 2 * 109
- coins contains pairwise distinct integers.

## Solution

**Language:** Java  
**Runtime:** 164 ms (beats 23.53%)  
**Memory:** 43.1 MB (beats 100.00%)  
**Submitted:** 2026-08-21T06:36:28.782Z  

```java
import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        long lo = 1;
        long hi = (long) coins[0] * k;

        for (int c : coins) {
            hi = Math.min(hi, (long) c * k);
        }

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (count(mid, coins) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        // Inclusion-exclusion over all subsets.
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    long g = gcd(lcm, coins[i]);
                    lcm = lcm / g * coins[i];

                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) continue;

            long cnt = x / lcm;

            if ((bits & 1) == 1) {
                total += cnt;
            } else {
                total -= cnt;
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/)