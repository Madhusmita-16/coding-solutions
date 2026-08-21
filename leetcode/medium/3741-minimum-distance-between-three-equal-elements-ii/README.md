# Minimum Distance Between Three Equal Elements II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

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

- 1 <= n == nums.length <= 105
- 1 <= nums[i] <= n

## Solution

**Language:** Java  
**Runtime:** 37 ms (beats 94.70%)  
**Memory:** 241.7 MB (beats 75.90%)  
**Submitted:** 2026-08-21T07:15:02.073Z  

```java
import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        List<Integer>[] positions = new ArrayList[n + 1];

        for (int i = 0; i < n; i++) {
            if (positions[nums[i]] == null) {
                positions[nums[i]] = new ArrayList<>();
            }
            positions[nums[i]].add(i);
        }

        for (int value = 1; value <= n; value++) {
            List<Integer> list = positions[value];

            if (list == null || list.size() < 3) {
                continue;
            }

            // For i < j < k:
            // distance = 2 * (k - i)
            //
            // Therefore, among any 3 consecutive occurrences,
            // the first and third give the minimum possible span.
            for (int i = 0; i + 2 < list.size(); i++) {
                int first = list.get(i);
                int third = list.get(i + 2);

                ans = Math.min(ans, 2 * (third - first));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-distance-between-three-equal-elements-ii/)