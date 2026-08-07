# Subarrays with Sum in Range

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given an integer array  **arr[]**  and two integers  **l**  and  **r**, find the number of subarrays whose sum lies in the range [l, r] (inclusive).

A subarray is a contiguous sequence of elements within the array.

 **Examples:** 

```
Input: l = 3, r = 8, arr[] = [1, 4, 6]
Output: 3
Explanation: The subarrays are [1,4], [4] and [6]. Therefore answer for this test case is 3.

```

```
Input: l = 4, r = 13, arr[] = [2, 3, 5, 8]
Output: 6
Explanation: The subarrays are [2, 3], [2, 3, 5], [3, 5], [5], [5, 8] and [8]. 
Therefore answer for this test case is 6.

```

 **Constraints:** 
1 ≤ arr.size() ≤ 105
1 ≤ arr[i] ≤ 104
1 ≤ l ≤ r ≤ 109

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T18:39:19.206Z  

```java
class Solution {
    public int countSubarray(int[] arr, int l, int r) {
        return countAtMost(arr, r) - countAtMost(arr, l - 1);
    }

    private int countAtMost(int[] arr, int limit) {
        if (limit < 0) {
            return 0;
        }

        long sum = 0;
        long count = 0;
        int left = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            // Remove elements until sum <= limit
            while (sum > limit) {
                sum -= arr[left++];
            }

            // All subarrays ending at right and
            // starting from left to right are valid
            count += right - left + 1;
        }

        return (int) count;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/count-the-number-of-subarrays/1)