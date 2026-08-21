# Minimum Distance Between Three Equal Elements I

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given an integer array `nums`.

A tuple `(i, j, k)` of 3  **distinct**  indices is  **good**  if `nums[i] == nums[j] == nums[k]`.

The  **distance**  of a  **good**  tuple is `abs(i - j) + abs(j - k) + abs(k - i)`, where `abs(x)` denotes the  **absolute value**  of `x`.

Return an integer denoting the  **minimum**  possible  **distance**  of a  **good**  tuple. If no  **good**  tuples exist, return `-1`.

 

 **Example 1:** 

 **Input:**  nums = [1,2,1,1,3]

 **Output:**  6

 **Explanation:** 

The minimum distance is achieved by the good tuple `(0, 2, 3)`.

`(0, 2, 3)` is a good tuple because `nums[0] == nums[2] == nums[3] == 1`. Its distance is `abs(0 - 2) + abs(2 - 3) + abs(3 - 0) = 2 + 1 + 3 = 6`.

 **Example 2:** 

 **Input:**  nums = [1,1,2,3,2,1,2]

 **Output:**  8

 **Explanation:** 

The minimum distance is achieved by the good tuple `(2, 4, 6)`.

`(2, 4, 6)` is a good tuple because `nums[2] == nums[4] == nums[6] == 2`. Its distance is `abs(2 - 4) + abs(4 - 6) + abs(6 - 2) = 2 + 2 + 4 = 8`.

 **Example 3:** 

 **Input:**  nums = [1]

 **Output:**  -1

 **Explanation:** 

There are no good tuples. Therefore, the answer is -1.

 

 **Constraints:** 

- 1 <= n == nums.length <= 100
- 1 <= nums[i] <= n

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 88.60%)  
**Memory:** 44.1 MB (beats 92.81%)  
**Submitted:** 2026-08-21T07:13:09.455Z  

```java
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] != nums[j]) {
                    continue;
                }

                for (int k = j + 1; k < n; k++) {
                    if (nums[i] == nums[k]) {
                        int distance = (k - i) * 2;
                        ans = Math.min(ans, distance);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-distance-between-three-equal-elements-i/)