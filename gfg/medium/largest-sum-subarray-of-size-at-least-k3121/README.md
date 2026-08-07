# Max Sum Subarray of Size at least K

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array  **arr[]** and an integer  **k**, find the maximum sum among all contiguous subarrays having a length greater than or equal to k.

 **Examples:** 

```
Input: arr[] = [1, -2, 2, -3], k = 3
Output: 1
Explanation: The sub-array of length at least 3 that produces greatest sum is [1, -2, 2]
```

```
Input: arr[] = [1, 1, 1, 1, 1, 1], k = 2
Output: 6
Explanation: The sub-array of length at least 2 that produces greatest sum is [1, 1, 1, 1, 1, 1]
```

```
Input: arr[] = [-4, -2, 1, -3], k = 2
Output: -1
Explanation: The sub-array of length at least 2 that produces greatest sum is [-2, 1]
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T18:36:51.198Z  

```java
class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;

        long windowSum = 0;

        // Sum of first k elements
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        long maxSum = windowSum;
        long bestEndingHere = windowSum;

        for (int i = k; i < n; i++) {
            // Sum of the current window of size k
            windowSum += arr[i] - arr[i - k];

            // Either extend the previous subarray
            // or start with the current k-sized window
            bestEndingHere = Math.max(windowSum, bestEndingHere + arr[i]);

            maxSum = Math.max(maxSum, bestEndingHere);
        }

        return (int) maxSum;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/largest-sum-subarray-of-size-at-least-k3121/1)