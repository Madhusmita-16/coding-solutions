# Number of Unique XOR Triplets II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`.

A  **XOR triplet**  is defined as the XOR of three elements `nums[i] XOR nums[j] XOR nums[k]` where `i <= j <= k`.

Return the number of  **unique**  XOR triplet values from all possible triplets `(i, j, k)`.

 

 **Example 1:** 

 **Input:**  nums = [1,3]

 **Output:**  2

 **Explanation:** 

The possible XOR triplet values are:

- (0, 0, 0) → 1 XOR 1 XOR 1 = 1
- (0, 0, 1) → 1 XOR 1 XOR 3 = 3
- (0, 1, 1) → 1 XOR 3 XOR 3 = 1
- (1, 1, 1) → 3 XOR 3 XOR 3 = 3

The unique XOR values are `{1, 3}`. Thus, the output is 2.

 **Example 2:** 

 **Input:**  nums = [6,7,8,9]

 **Output:**  4

 **Explanation:** 

The possible XOR triplet values are `{6, 7, 8, 9}`. Thus, the output is 4.

 

 **Constraints:** 

- 1 <= nums.length <= 1500
- 1 <= nums[i] <= 1500

## Solution

**Language:** Java  
**Runtime:** 670 ms (beats 25.18%)  
**Memory:** 47.2 MB (beats 9.66%)  
**Submitted:** 2026-08-20T18:02:27.477Z  

```java
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean[] present = new boolean[MAX];

        for (int x : nums) {
            present[x] = true;
        }

        // All possible XORs of two elements
        boolean[] pair = new boolean[MAX];

        for (int a = 0; a < MAX; a++) {
            if (!present[a]) continue;

            for (int b = 0; b < MAX; b++) {
                if (present[b]) {
                    pair[a ^ b] = true;
                }
            }
        }

        // All possible XORs of three elements
        boolean[] triplet = new boolean[MAX];

        for (int x = 0; x < MAX; x++) {
            if (!pair[x]) continue;

            for (int y = 0; y < MAX; y++) {
                if (present[y]) {
                    triplet[x ^ y] = true;
                }
            }
        }

        int count = 0;

        for (boolean value : triplet) {
            if (value) {
                count++;
            }
        }

        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-unique-xor-triplets-ii/)