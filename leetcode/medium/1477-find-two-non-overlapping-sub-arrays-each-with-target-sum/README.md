# Find Two Non-overlapping Sub-arrays Each With Target Sum

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an array of integers `arr` and an integer `target`.

You have to find  **two non-overlapping sub-arrays**  of `arr` each with a sum equal `target`. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is  **minimum**.

Return  *the minimum sum of the lengths*  of the two required sub-arrays, or return `-1` if you cannot find such two sub-arrays.

 

 **Example 1:** 

```
Input: arr = [3,2,2,4,3], target = 3
Output: 2
Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.

```

 **Example 2:** 

```
Input: arr = [7,3,4,7], target = 7
Output: 2
Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.

```

 **Example 3:** 

```
Input: arr = [4,3,2,6,2,3,4], target = 6
Output: -1
Explanation: We have only one sub-array of sum = 6.

```

 

 **Constraints:** 

- 1 <= arr.length <= 105
- 1 <= arr[i] <= 1000
- 1 <= target <= 108

## Solution

**Language:** Java  
**Runtime:** 7 ms (beats 84.30%)  
**Memory:** 91.1 MB (beats 60.54%)  
**Submitted:** 2026-09-17T04:48:02.957Z  

```java
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];

        int INF = n + 1;
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        long sum = 0;
        int ans = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            if (sum == target) {
                int currentLength = right - left + 1;

                if (left > 0 && best[left - 1] != INF) {
                    ans = Math.min(ans, currentLength + best[left - 1]);
                }

                minLength = Math.min(minLength, currentLength);
            }

            if (right == 0) {
                best[right] = minLength;
            } else {
                best[right] = Math.min(best[right - 1], minLength);
            }
        }

        return ans == INF ? -1 : ans;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/)