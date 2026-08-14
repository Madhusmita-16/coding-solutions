# Q1. K-Concatenation Maximum Sum

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `arr` and an integer `k`, modify the array by repeating it `k` times.

For example, if `arr = [1, 2]` and `k = 3 `then the modified array will be `[1, 2, 1, 2, 1, 2]`.

Return the maximum sub-array sum in the modified array. Note that the length of the sub-array can be `0` and its sum in that case is `0`.

As the answer can be very large, return the answer  **modulo**  `109 + 7`.

 

 **Example 1:** 

```
Input: arr = [1,2], k = 3
Output: 9

```

 **Example 2:** 

```
Input: arr = [1,-2,1], k = 5
Output: 2

```

 **Example 3:** 

```
Input: arr = [-1,-2], k = 7
Output: 0

```

 

 **Constraints:** 

- 1 <= arr.length <= 105
- 1 <= k <= 105
- -104 <= arr[i] <= 104

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.3 MB  
**Submitted:** 2026-08-14T17:53:58.897Z  

```java
class Solution {
    private static final long MOD = 1_000_000_007L;

    public int kConcatenationMaxSum(int[] arr, int k) {
        long totalSum = 0;
        long prefix = 0;
        long maxPrefix = 0;
        long suffix = 0;
        long maxSuffix = 0;
        long maxSubarray = 0;
        long current = 0;

        for (int x : arr) {
            totalSum += x;

            prefix += x;
            maxPrefix = Math.max(maxPrefix, prefix);

            current = Math.max(0, current + x);
            maxSubarray = Math.max(maxSubarray, current);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            suffix += arr[i];
            maxSuffix = Math.max(maxSuffix, suffix);
        }

        long answer;

        if (k == 1) {
            answer = maxSubarray;
        } else {
            // Best subarray lies within two copies
            answer = Math.max(maxSubarray, maxPrefix + maxSuffix);

            // If total sum is positive, middle copies can contribute
            if (totalSum > 0) {
                answer = Math.max(
                    answer,
                    maxPrefix + maxSuffix + totalSum * (k - 2L)
                );
            }
        }

        return (int) (answer % MOD);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/k-concatenation-maximum-sum/)