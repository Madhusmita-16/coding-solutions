# Q2. Find K Pairs with Smallest Sums

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two integer arrays `nums1` and `nums2` sorted in  **non-decreasing order**  and an integer `k`.

Define a pair `(u, v)` which consists of one element from the first array and one element from the second array.

Return  *the*  `k`  *pairs*  `(u1, v1), (u2, v2),..., (uk, vk)`  *with the smallest sums*.

 

 **Example 1:** 

```
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

```

 **Example 2:** 

```
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

```

 

 **Constraints:** 

- 1 <= nums1.length, nums2.length <= 105
- -109 <= nums1[i], nums2[i] <= 109
- nums1 and nums2 both are sorted in non-decreasing order.
- 1 <= k <= 104
- k <= nums1.length * nums2.length

## Solution

**Language:** Java  
**Runtime:** 1 ms  
**Memory:** 42.7 MB  
**Submitted:** 2026-08-14T11:29:13.378Z  

```java
import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> result = new ArrayList<>();

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0] + a[1], b[0] + b[1])
        );

        // Add the first possible pair for each nums1 element
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], i, 0});
        }

        while (k > 0 && !minHeap.isEmpty()) {

            int[] current = minHeap.poll();

            int value1 = current[0];
            int value2 = current[1];
            int i = current[2];
            int j = current[3];

            result.add(Arrays.asList(value1, value2));
            k--;

            // Move to the next element in nums2
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{
                    nums1[i],
                    nums2[j + 1],
                    i,
                    j + 1
                });
            }
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/find-k-pairs-with-smallest-sums/)