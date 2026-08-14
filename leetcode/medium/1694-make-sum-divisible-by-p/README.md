# Q2. Make Sum Divisible by P

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array of positive integers `nums`, remove the  **smallest**  subarray (possibly  **empty**) such that the  **sum**  of the remaining elements is divisible by `p`. It is  **not**  allowed to remove the whole array.

Return  *the length of the smallest subarray that you need to remove, or* `-1` *if it's impossible*.

A  **subarray**  is defined as a contiguous block of elements in the array.

 

 **Example 1:** 

```
Input: nums = [3,1,4,2], p = 6
Output: 1
Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.

```

 **Example 2:** 

```
Input: nums = [6,3,5,2], p = 9
Output: 2
Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.

```

 **Example 3:** 

```
Input: nums = [1,2,3], p = 3
Output: 0
Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.

```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 109
- 1 <= p <= 109

## Solution

**Language:** Java  
**Runtime:** 31 ms (beats 20.07%)  
**Memory:** 99.1 MB (beats 67.24%)  
**Submitted:** 2026-08-14T11:59:09.798Z  

```java
import java.util.HashMap;

class Solution {
    public int minSubarray(int[] nums, int p) {

        long total = 0;

        for (int num : nums) {
            total = (total + num) % p;
        }

        int target = (int) total;

        // Already divisible
        if (target == 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        // remainder 0 before starting
        map.put(0, -1);

        long prefix = 0;
        int ans = nums.length;

        for (int i = 0; i < nums.length; i++) {

            prefix = (prefix + nums[i]) % p;

            int current = (int) prefix;

            // Need previous remainder:
            // (current - previous + p) % p = target
            int needed = (current - target + p) % p;

            if (map.containsKey(needed)) {
                ans = Math.min(ans, i - map.get(needed));
            }

            // Store the latest index to get the shortest subarray
            map.put(current, i);
        }

        // Cannot remove the entire array
        return ans == nums.length ? -1 : ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/make-sum-divisible-by-p/)