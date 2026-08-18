# Rotate Array

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.

 

 **Example 1:** 

```
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

```

 **Example 2:** 

```
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- -231 <= nums[i] <= 231 - 1
- 0 <= k <= 105

 

 **Follow up:** 

- Try to come up with as many solutions as you can. There are at least three different ways to solve this problem.
- Could you do it in-place with O(1) extra space?

## Solution

**Language:** Java  
**Runtime:** 8 ms (beats 3.38%)  
**Memory:** 268.5 MB (beats 8.00%)  
**Submitted:** 2026-08-18T17:21:06.797Z  

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int count = 0;

        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[current];

            while (true) {
                int next = (current + k) % n;

                int temp = nums[next];
                nums[next] = prev;
                prev = temp;

                current = next;
                count++;

                if (current == start) {
                    break;
                }
            }
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/rotate-array/)