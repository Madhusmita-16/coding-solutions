# Maximum Sum Circular Subarray

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given a  **circular integer array**  `nums` of length `n`, return  *the maximum possible sum of a non-empty  **subarray**  of* `nums`.

A  **circular array**  means the end of the array connects to the beginning of the array. Formally, the next element of `nums[i]` is `nums[(i + 1) % n]` and the previous element of `nums[i]` is `nums[(i - 1 + n) % n]`.

A  **subarray**  may only include each element of the fixed buffer `nums` at most once. Formally, for a subarray `nums[i], nums[i + 1],..., nums[j]`, there does not exist `i <= k1`, `k2 <= j` with `k1 % n == k2 % n`.

 

 **Example 1:** 

```
Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.

```

 **Example 2:** 

```
Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.

```

 **Example 3:** 

```
Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.

```

 

 **Constraints:** 

- n == nums.length
- 1 <= n <= 3 * 104
- -3  *104 <= nums[i] <= 3*  104

## Solution

**Language:** Java  
**Runtime:** 7 ms (beats 49.48%)  
**Memory:** 51.1 MB (beats 42.46%)  
**Submitted:** 2026-08-15T08:31:57.413Z  

```java
class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int totalSum = 0;

        int currentMax = 0;
        int maxSum = nums[0];

        int currentMin = 0;
        int minSum = nums[0];

        for (int num : nums) {

            // Maximum subarray
            currentMax = Math.max(num, currentMax + num);
            maxSum = Math.max(maxSum, currentMax);

            // Minimum subarray
            currentMin = Math.min(num, currentMin + num);
            minSum = Math.min(minSum, currentMin);

            totalSum += num;
        }

        // All numbers are negative
        if (maxSum < 0) {
            return maxSum;
        }

        // Either normal subarray or circular subarray
        return Math.max(maxSum, totalSum - minSum);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-sum-circular-subarray/)