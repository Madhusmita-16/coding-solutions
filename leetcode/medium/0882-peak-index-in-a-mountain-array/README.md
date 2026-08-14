# Q1. Peak Index in a Mountain Array

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer  **mountain**  array `arr` of length `n` where the values increase to a  **peak element**  and then decrease.

Return the index of the peak element.

Your task is to solve it in `O(log(n))` time complexity.

 

 **Example 1:** 

 **Input:**  arr = [0,1,0]

 **Output:**  1

 **Example 2:** 

 **Input:**  arr = [0,2,1,0]

 **Output:**  1

 **Example 3:** 

 **Input:**  arr = [0,10,5,2]

 **Output:**  1

 

 **Constraints:** 

- 3 <= arr.length <= 105
- 0 <= arr[i] <= 106
- arr is guaranteed to be a mountain array.

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 80.2 MB (beats 78.68%)  
**Submitted:** 2026-08-14T12:10:00.136Z  

```java
class Solution {
    public int peakIndexInMountainArray(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < arr[mid + 1]) {
                // We are on the increasing side
                left = mid + 1;
            } else {
                // We are on the decreasing side or at the peak
                right = mid;
            }
        }

        return left;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/peak-index-in-a-mountain-array/)