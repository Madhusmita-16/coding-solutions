# Single Number II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums` where every element appears  **three times**  except for one, which appears  **exactly once**.  *Find the single element and return it*.

You must implement a solution with a linear runtime complexity and use only constant extra space.

 

 **Example 1:** 

```
Input: nums = [2,2,3,2]
Output: 3

```

 **Example 2:** 

```
Input: nums = [0,1,0,1,0,1,99]
Output: 99

```

 

 **Constraints:** 

- 1 <= nums.length <= 3 * 104
- -231 <= nums[i] <= 231 - 1
- Each element in nums appears exactly three times except for one element which appears once.

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.6 MB  
**Submitted:** 2026-08-15T06:53:41.319Z  

```java
class Solution {
    public int singleNumber(int[] nums) {

        int ones = 0;
        int twos = 0;

        for (int num : nums) {

            // Bits appearing twice
            twos |= ones & num;

            // Bits appearing once
            ones ^= num;

            // Remove bits that appeared three times
            int threes = ones & twos;

            ones &= ~threes;
            twos &= ~threes;
        }

        return ones;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/single-number-ii/)