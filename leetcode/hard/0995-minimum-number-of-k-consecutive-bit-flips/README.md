# Minimum Number of K Consecutive Bit Flips

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a binary array `nums` and an integer `k`.

A  **k-bit flip**  is choosing a  **subarray**  of length `k` from `nums` and simultaneously changing every `0` in the subarray to `1`, and every `1` in the subarray to `0`.

Return  *the minimum number of  **k-bit flips**  required so that there is no* `0` *in the array*. If it is not possible, return `-1`.

A  **subarray**  is a  **contiguous**  part of an array.

 

 **Example 1:** 

```
Input: nums = [0,1,0], k = 1
Output: 2
Explanation: Flip nums[0], then flip nums[2].

```

 **Example 2:** 

```
Input: nums = [1,1,0], k = 2
Output: -1
Explanation: No matter how we flip subarrays of size 2, we cannot make the array become [1,1,1].

```

 **Example 3:** 

```
Input: nums = [0,0,0,1,0,1,1,0], k = 3
Output: 3
Explanation: 
Flip nums[0],nums[1],nums[2]: nums becomes [1,1,1,1,0,1,1,0]
Flip nums[4],nums[5],nums[6]: nums becomes [1,1,1,1,1,0,0,0]
Flip nums[5],nums[6],nums[7]: nums becomes [1,1,1,1,1,1,1,1]

```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= k <= nums.length

## Solution

**Language:** Java  
**Runtime:** 4 ms (beats 100.00%)  
**Memory:** 73.1 MB (beats 51.90%)  
**Submitted:** 2026-08-27T08:55:06.861Z  

```java
class Solution {
    public int minKBitFlips(int[] nums, int k) {

        int n = nums.length;
        int[] diff = new int[n + 1];

        int flips = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {

            // Remove the effect of a flip that ended here
            flips ^= diff[i];

            // Current value after all active flips
            if ((nums[i] ^ flips) == 0) {

                // Cannot flip if there aren't k elements remaining
                if (i + k > n) {
                    return -1;
                }

                // Start a new flip
                flips ^= 1;
                diff[i + k] ^= 1;

                answer++;
            }
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/)