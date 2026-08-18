# Maximum Product of Three Numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given an integer array `nums`.

Find three numbers whose product is  **maximum**  and return the  **maximum**  product.

 

 **Example 1:** 

 **Input:**  nums = [1,2,3]

 **Output:**  6

 **Explanation:** 

The only three numbers are 1, 2, and 3, so the maximum product is `1  *2*  3 = 6`.

 **Example 2:** 

 **Input:**  nums = [1,2,3,4]

 **Output:**  24

 **Explanation:** 

The largest product comes from the three greatest numbers: `2  *3*  4 = 24`.

 **Example 3:** 

 **Input:**  nums = [-1,-2,-3]

 **Output:**  -6

 **Explanation:** 

The only three numbers are -1, -2, and -3, so the maximum product is `(-1)  *(-2)*  (-3) = -6`.

 

 **Constraints:** 

- 3 <= nums.length <= 104
- -1000 <= nums[i] <= 1000

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 99.49%)  
**Memory:** 47.3 MB (beats 78.12%)  
**Submitted:** 2026-08-18T14:33:47.128Z  

```java
class Solution {
    public int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int num : nums) {

            // Track 3 largest
            if (num >= max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num >= max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }

            // Track 2 smallest
            if (num <= min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }

        int product1 = max1 * max2 * max3;
        int product2 = min1 * min2 * max1;

        return Math.max(product1, product2);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-product-of-three-numbers/)