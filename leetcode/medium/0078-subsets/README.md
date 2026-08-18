# Subsets

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array `nums` of  **unique**  elements, return  *all possible*   *subsets*   *(the power set)*.

The solution set  **must not**  contain duplicate subsets. Return the solution in  **any order**.

 

 **Example 1:** 

```
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

```

 **Example 2:** 

```
Input: nums = [0]
Output: [[],[0]]

```

 

 **Constraints:** 

- 1 <= nums.length <= 10
- -10 <= nums[i] <= 10
- All the numbers of nums are unique.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 86.46%)  
**Memory:** 44.2 MB (beats 57.91%)  
**Submitted:** 2026-08-18T15:24:24.612Z  

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(nums, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int[] nums, int index,
                           List<Integer> current,
                           List<List<Integer>> result) {

        // Add the current subset
        result.add(new ArrayList<>(current));

        // Try including each remaining element
        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);

            backtrack(nums, i + 1, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/subsets/)