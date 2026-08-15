# Longest Increasing Subsequence

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums`, return *the length of the longest  **strictly increasing**   subsequence***.

 

 **Example 1:** 

```
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

```

 **Example 2:** 

```
Input: nums = [0,1,0,3,2,3]
Output: 4

```

 **Example 3:** 

```
Input: nums = [7,7,7,7,7,7,7]
Output: 1

```

 

 **Constraints:** 

- 1 <= nums.length <= 2500
- -104 <= nums[i] <= 104

 

 **Follow up:**  Can you come up with an algorithm that runs in `O(n log(n))` time complexity?

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.1 MB  
**Submitted:** 2026-08-15T06:39:38.907Z  

```java
class Solution {
    public int lengthOfLIS(int[] nums) {

        int[] tails = new int[nums.length];
        int size = 0;

        for (int num : nums) {

            int left = 0;
            int right = size;

            // Find the first position where tails[pos] >= num
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            tails[left] = num;

            if (left == size) {
                size++;
            }
        }

        return size;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/longest-increasing-subsequence/)