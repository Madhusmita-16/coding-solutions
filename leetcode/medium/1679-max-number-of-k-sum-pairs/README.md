# Max Number of K-Sum Pairs

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums` and an integer `k`.

In one operation, you can pick two numbers from the array whose sum equals `k` and remove them from the array.

Return  *the maximum number of operations you can perform on the array*.

 

 **Example 1:** 

```
Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
```

 **Example 2:** 

```
Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.
```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 109
- 1 <= k <= 109

## Solution

**Language:** Java  
**Runtime:** 37 ms (beats 29.02%)  
**Memory:** 70.5 MB (beats 11.38%)  
**Submitted:** 2026-08-27T08:01:48.821Z  

```java
class Solution {
    public int maxOperations(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int operations = 0;

        for (int num : nums) {

            int complement = k - num;

            // If complement is available, make a pair
            if (map.getOrDefault(complement, 0) > 0) {
                operations++;

                map.put(complement, map.get(complement) - 1);
            } else {
                // Store current number for a future pair
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return operations;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/max-number-of-k-sum-pairs/)