# Increasing Triplet Subsequence

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums`, return `true` *if there exists a triple of indices* `(i, j, k)` *such that* `i < j < k` *and* `nums[i] < nums[j] < nums[k]`. If no such indices exists, return `false`.

 

 **Example 1:** 

```
Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.

```

 **Example 2:** 

```
Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.

```

 **Example 3:** 

```
Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: One of the valid triplet is (1, 4, 5), because nums[1] == 1 < nums[4] == 4 < nums[5] == 6.

```

 

 **Constraints:** 

- 1 <= nums.length <= 5 * 105
- -231 <= nums[i] <= 231 - 1

 

 **Follow up:**  Could you implement a solution that runs in `O(n)` time complexity and `O(1)` space complexity?

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 99.32%)  
**Memory:** 122.7 MB (beats 39.98%)  
**Submitted:** 2026-08-27T07:57:33.143Z  

```java
class Solution {
    public boolean increasingTriplet(int[] nums) {

        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {

            // Smallest first element
            if (num <= first) {
                first = num;
            }

            // Smallest possible second element
            else if (num <= second) {
                second = num;
            }

            // num > first and num > second
            else {
                return true;
            }
        }

        return false;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/increasing-triplet-subsequence/)