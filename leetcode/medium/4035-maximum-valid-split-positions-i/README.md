# Maximum Valid Split Positions I

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`.

You may remove  **at most one**  element from `nums`. Let `arr` be the array of remaining elements in their original order, and let `m` be its length.

A  **split position**  `i` of `arr` is  **valid**  if:

- 0 <= i < m - 1, and
- gcd(arr[0..i]) == gcd(arr[i + 1..m - 1]).

An array of length 1 has no valid split positions.

The  **score**  of `arr` is the number of valid split positions in it.

Return the  **maximum possible score**  of `arr`.

Here, `gcd(a)` denotes the  **greatest common divisor**  of all elements in the array `a`.

 

 **Example 1:** 

 **Input:**  nums = [10,30,15,10]

 **Output:**  2

 **Explanation:** 

One optimal solution is to remove `nums[2] = 15`. Then `arr = [10, 30, 10]`.

The split positions are:

Split Position `i`	`gcd(arr[0..i])`	`gcd(arr[i + 1..m - 1])`
0	10	10
1	10	10

All split positions are valid. Thus, the answer is 2.

 **Example 2:** 

 **Input:**  nums = [2,10,14]

 **Output:**  1

 **Explanation:** 

One optimal solution is to not remove any element. Then `arr = [2, 10, 14]`.

The split positions are:

Split Position `i`	`gcd(arr[0..i])`	`gcd(arr[i + 1..m - 1])`
0	2	2
1	2	14

Only the split position at index 0 is valid. Thus, the answer is 1.

 **Example 3:** 

 **Input:**  nums = [2,4]

 **Output:**  0

 **Explanation:** 

The only remaining array that has a split position is `arr = [2, 4]`.

The split positions are:

Split Position `i`	`gcd(arr[0..i])`	`gcd(arr[i + 1..m - 1])`
0	2	4

There are no valid split positions. Thus, the answer is 0.

 

 **Constraints:** 

- 2 <= nums.length <= 1000
- 1 <= nums[i] <= 109​​​​​​​

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.1 MB  
**Submitted:** 2026-08-31T07:08:44.715Z  

```java
class Solution {
    public int maxValidSplits(int[] nums) {

        int n = nums.length;
        int answer = 0;

        // k = index to remove
        // k = n means remove nothing
        for (int k = 0; k <= n; k++) {

            int m = (k == n) ? n : n - 1;

            // Prefix GCD
            int[] prefix = new int[m];
            int g = 0;

            for (int i = 0, j = 0; i < n; i++) {
                if (i == k) continue;

                g = gcd(g, nums[i]);
                prefix[j++] = g;
            }

            // Count valid splits
            int count = 0;
            int suffixGcd = 0;

            // Build suffix GCD while moving from right to left
            int[] arr = new int[m];
            int idx = 0;

            for (int i = 0; i < n; i++) {
                if (i != k) {
                    arr[idx++] = nums[i];
                }
            }

            for (int i = m - 1; i > 0; i--) {

                suffixGcd = gcd(suffixGcd, arr[i]);

                if (prefix[i - 1] == suffixGcd) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-valid-split-positions-i/)