# Q2. 3Sum Closest

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums` of length `n` and an integer `target`, find three integers at  **distinct indices**  in `nums` such that the sum is closest to `target`.

Return  *the sum of the three integers*.

You may assume that each input would have exactly one solution.

 

 **Example 1:** 

```
Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

```

 **Example 2:** 

```
Input: nums = [0,0,0], target = 1
Output: 0
Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

```

 

 **Constraints:** 

- 3 <= nums.length <= 500
- -1000 <= nums[i] <= 1000
- -104 <= target <= 104

## Solution

**Language:** Java  
**Runtime:** 17 ms (beats 78.14%)  
**Memory:** 45 MB (beats 96.86%)  
**Submitted:** 2026-08-14T15:24:45.408Z  

```java
import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int closest = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                // Update closest sum
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                // Exact answer
                if (sum == target) {
                    return sum;
                }

                // Need a larger sum
                if (sum < target) {
                    left++;
                }
                // Need a smaller sum
                else {
                    right--;
                }
            }
        }

        return closest;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/3sum-closest/)