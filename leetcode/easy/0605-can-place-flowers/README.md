# Can Place Flowers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in  **adjacent**  plots.

Given an integer array `flowerbed` containing `0`'s and `1`'s, where `0` means empty and `1` means not empty, and an integer `n`, return `true`  *if*  `n`  *new flowers can be planted in the*  `flowerbed`  *without violating the no-adjacent-flowers rule and*  `false`  *otherwise*.

 

 **Example 1:** 

```
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true

```

 **Example 2:** 

```
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false

```

 

 **Constraints:** 

- 1 <= flowerbed.length <= 2 * 104
- flowerbed[i] is 0 or 1.
- There are no two adjacent flowers in flowerbed.
- 0 <= n <= flowerbed.length

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.5 MB  
**Submitted:** 2026-08-27T07:55:12.291Z  

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        for (int i = 0; i < flowerbed.length; i++) {

            if (flowerbed[i] == 0) {

                // Check left side
                boolean leftEmpty = (i == 0 || flowerbed[i - 1] == 0);

                // Check right side
                boolean rightEmpty = (i == flowerbed.length - 1
                                      || flowerbed[i + 1] == 0);

                // We can plant here
                if (leftEmpty && rightEmpty) {
                    flowerbed[i] = 1;
                    n--;

                    if (n == 0) {
                        return true;
                    }
                }
            }
        }

        return n <= 0;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/can-place-flowers/)