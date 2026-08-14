# Q3. Construct Target Array With Multiple Sums

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an array `target` of n integers. From a starting array `arr` consisting of `n` 1's, you may perform the following procedure :

- let x be the sum of all elements currently in your array.
- choose index i, such that 0 <= i < n and set the value of arr at index i to x.
- You may repeat this procedure as many times as needed.

Return `true`  *if it is possible to construct the*  `target`  *array from*  `arr` *, otherwise, return*  `false`.

 

 **Example 1:** 

```
Input: target = [9,3,5]
Output: true
Explanation: Start with arr = [1, 1, 1] 
[1, 1, 1], sum = 3 choose index 1
[1, 3, 1], sum = 5 choose index 2
[1, 3, 5], sum = 9 choose index 0
[9, 3, 5] Done

```

 **Example 2:** 

```
Input: target = [1,1,1,2]
Output: false
Explanation: Impossible to create target array from [1,1,1,1].

```

 **Example 3:** 

```
Input: target = [8,5]
Output: true

```

 

 **Constraints:** 

- n == target.length
- 1 <= n <= 5 * 104
- 1 <= target[i] <= 109

## Solution

**Language:** Java  
**Runtime:** 11 ms (beats 50.20%)  
**Memory:** 55.8 MB (beats 45.70%)  
**Submitted:** 2026-08-14T11:32:14.158Z  

```java
import java.util.*;

class Solution {
    public boolean isPossible(int[] target) {

        PriorityQueue<Long> pq =
                new PriorityQueue<>(Collections.reverseOrder());

        long sum = 0;

        for (int x : target) {
            pq.offer((long) x);
            sum += x;
        }

        while (true) {

            long largest = pq.poll();
            long rest = sum - largest;

            // We reached [1, 1, ..., 1]
            if (largest == 1) {
                return true;
            }

            // Cannot reduce a single element
            if (rest == 0) {
                return false;
            }

            // If the rest sums to 1, we can always reduce
            // the largest to 1.
            if (rest == 1) {
                return true;
            }

            // Largest must be greater than the sum of others
            if (largest <= rest) {
                return false;
            }

            // Reverse the operation
            long previous = largest % rest;

            if (previous == 0) {
                return false;
            }

            pq.offer(previous);

            sum = rest + previous;
        }
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/construct-target-array-with-multiple-sums/)