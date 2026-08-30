# Removing Minimum and Maximum From Array

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a  **0-indexed**  array of  **distinct**  integers `nums`.

There is an element in `nums` that has the  **lowest**  value and an element that has the  **highest**  value. We call them the  **minimum**  and  **maximum**  respectively. Your goal is to remove  **both**  these elements from the array.

A  **deletion**  is defined as either removing an element from the  **front**  of the array or removing an element from the  **back**  of the array.

Return  *the  **minimum**  number of deletions it would take to remove  **both**  the minimum and maximum element from the array.* 

 

 **Example 1:** 

```
Input: nums = [2,10,7,5,4,1,8,6]
Output: 5
Explanation: 
The minimum element in the array is nums[5], which is 1.
The maximum element in the array is nums[1], which is 10.
We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
This results in 2 + 3 = 5 deletions, which is the minimum number possible.

```

 **Example 2:** 

```
Input: nums = [0,-4,19,1,8,-2,-3,5]
Output: 3
Explanation: 
The minimum element in the array is nums[1], which is -4.
The maximum element in the array is nums[2], which is 19.
We can remove both the minimum and maximum by removing 3 elements from the front.
This results in only 3 deletions, which is the minimum number possible.

```

 **Example 3:** 

```
Input: nums = [101]
Output: 1
Explanation:  
There is only one element in the array, which makes it both the minimum and maximum element.
We can remove it with 1 deletion.

```

 

 **Constraints:** 

- 1 <= nums.length <= 105
- -105 <= nums[i] <= 105
- The integers in nums are distinct.

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 61.82%)  
**Memory:** 86.6 MB (beats 80.36%)  
**Submitted:** 2026-08-30T15:59:30.473Z  

```java
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Put smaller index first
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // 1. Remove both from the front
        int fromFront = right + 1;

        // 2. Remove both from the back
        int fromBack = n - left;

        // 3. Remove left element from front
        //    and right element from back
        int oneEach = (left + 1) + (n - right);

        return Math.min(fromFront, Math.min(fromBack, oneEach));
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/removing-minimum-and-maximum-from-array/)