# H-Index

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array of integers `citations` where `citations[i]` is the number of citations a researcher received for their `ith` paper, return  *the researcher's h-index*.

According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of `h` such that the given researcher has published at least `h` papers that have each been cited at least `h` times.

 

 **Example 1:** 

```
Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.

```

 **Example 2:** 

```
Input: citations = [1,3,1]
Output: 1

```

 

 **Constraints:** 

- n == citations.length
- 1 <= n <= 5000
- 0 <= citations[i] <= 1000

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 43.3 MB (beats 85.07%)  
**Submitted:** 2026-08-15T13:01:02.691Z  

```java
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] count = new int[n + 1];

        for (int citation : citations) {
            if (citation >= n) {
                count[n]++;
            } else {
                count[citation]++;
            }
        }

        int papers = 0;

        for (int h = n; h >= 0; h--) {
            papers += count[h];

            if (papers >= h) {
                return h;
            }
        }

        return 0;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/h-index/)