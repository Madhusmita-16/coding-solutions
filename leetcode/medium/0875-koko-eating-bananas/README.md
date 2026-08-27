# Koko Eating Bananas

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Koko loves to eat bananas. There are `n` piles of bananas, the `ith` pile has `piles[i]` bananas. The guards have gone and will come back in `h` hours.

Koko can decide her bananas-per-hour eating speed of `k`. Each hour, she chooses some pile of bananas and eats `k` bananas from that pile. If the pile has less than `k` bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return  *the minimum integer*  `k`  *such that she can eat all the bananas within*  `h`  *hours*.

 

 **Example 1:** 

```
Input: piles = [3,6,7,11], h = 8
Output: 4

```

 **Example 2:** 

```
Input: piles = [30,11,23,4,20], h = 5
Output: 30

```

 **Example 3:** 

```
Input: piles = [30,11,23,4,20], h = 6
Output: 23

```

 

 **Constraints:** 

- 1 <= piles.length <= 104
- piles.length <= h <= 109
- 1 <= piles[i] <= 109

## Solution

**Language:** Java  
**Runtime:** 9 ms (beats 56.76%)  
**Memory:** 48 MB (beats 32.07%)  
**Submitted:** 2026-08-27T10:44:20.809Z  

```java
class Solution {
    public int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = 0;

        // Maximum pile is the maximum possible speed
        for (int pile : piles) {
            high = Math.max(high, pile);
        }

        while (low < high) {

            int k = low + (high - low) / 2;

            long hours = 0;

            for (int pile : piles) {
                hours += (pile + (long) k - 1) / k;

                // No need to continue if already too many hours
                if (hours > h) {
                    break;
                }
            }

            if (hours <= h) {
                // k works, try a smaller speed
                high = k;
            } else {
                // k is too slow
                low = k + 1;
            }
        }

        return low;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/koko-eating-bananas/)