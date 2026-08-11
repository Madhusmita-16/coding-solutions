# Q1. Set Mismatch

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You have a set of integers `s`, which originally contains all the numbers from `1` to `n`. Unfortunately, due to some error, one of the numbers in `s` got duplicated to another number in the set, which results in  **repetition of one**  number and  **loss of another**  number.

You are given an integer array `nums` representing the data status of this set after the error.

Find the number that occurs twice and the number that is missing and return  *them in the form of an array*.

 

 **Example 1:** 

```
Input: nums = [1,2,2,4]
Output: [2,3]

```

 **Example 2:** 

```
Input: nums = [1,1]
Output: [1,2]

```

 

 **Constraints:** 

- 2 <= nums.length <= 104
- 1 <= nums[i] <= 104

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 94.51%)  
**Memory:** 47.6 MB (beats 48.66%)  
**Submitted:** 2026-08-11T15:35:07.801Z  

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] count = new int[n + 1];

        int duplicate = 0;
        int missing = 0;

        for (int num : nums) {
            count[num]++;
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == 2) {
                duplicate = i;
            } else if (count[i] == 0) {
                missing = i;
            }
        }

        return new int[]{duplicate, missing};
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/set-mismatch/)