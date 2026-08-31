# Maximum Valid Split Positions II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

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

- 2 <= nums.length <= 105
- 1 <= nums[i] <= 109​​​​​​​

## Solution

**Language:** Java  
**Runtime:** 676 ms  
**Memory:** 168.8 MB  
**Submitted:** 2026-08-31T07:03:13.868Z  

```java
import java.util.*;

class Solution {

    int[][] st;
    int[] log;
    int n;

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int rangeGcd(int l, int r) {
        if (l > r) return 0;

        int len = r - l + 1;
        int j = log[len];

        return gcd(
            st[j][l],
            st[j][r - (1 << j) + 1]
        );
    }

    // GCD of first len elements after deleting index k
    private int prefixGcd(int k, int len) {

        if (k == 0) {
            return rangeGcd(1, len);
        }

        if (len <= k) {
            return rangeGcd(0, len - 1);
        }

        return gcd(
            rangeGcd(0, k - 1),
            rangeGcd(k + 1, len)
        );
    }

    // GCD of last len elements after deleting index k
    private int suffixGcd(int k, int len) {

        if (k == n - 1) {
            return rangeGcd(n - len - 1, n - 2);
        }

        int after = n - 1 - k;

        if (len <= after) {
            return rangeGcd(n - len, n - 1);
        }

        return gcd(
            rangeGcd(n - len - 1, k - 1),
            rangeGcd(k + 1, n - 1)
        );
    }

    private int firstPrefix(int k, int target) {

        int m = n - 1;

        int lo = 1;
        int hi = m;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (prefixGcd(k, mid) == target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int firstSuffix(int k, int target) {

        int m = n - 1;

        int lo = 1;
        int hi = m;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (suffixGcd(k, mid) == target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    // Check the case where we remove NOTHING
    private int scoreWithoutDeletion(int[] nums) {

        int answer = 0;
        int prefix = 0;

        for (int i = 0; i < n - 1; i++) {

            prefix = gcd(prefix, nums[i]);

            int suffix = rangeGcd(i + 1, n - 1);

            if (prefix == suffix) {
                answer++;
            }
        }

        return answer;
    }

    public int maxValidSplits(int[] nums) {

        n = nums.length;

        // Build logarithm table
        log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        // Build Sparse Table
        int K = log[n] + 1;
        st = new int[K][n];

        for (int i = 0; i < n; i++) {
            st[0][i] = nums[i];
        }

        for (int j = 1; j < K; j++) {

            int half = 1 << (j - 1);
            int len = 1 << j;

            for (int i = 0; i + len <= n; i++) {
                st[j][i] = gcd(
                    st[j - 1][i],
                    st[j - 1][i + half]
                );
            }
        }

        // IMPORTANT:
        // "At most one" means we can remove nothing.
        int answer = scoreWithoutDeletion(nums);

        // Try deleting every element
        for (int k = 0; k < n; k++) {

            // GCD of the entire remaining array
            int leftGcd = rangeGcd(0, k - 1);
            int rightGcd = rangeGcd(k + 1, n - 1);

            int target = gcd(leftGcd, rightGcd);

            int m = n - 1;

            // Minimum prefix length with gcd = target
            int L = firstPrefix(k, target);

            // Minimum suffix length with gcd = target
            int R = firstSuffix(k, target);

            /*
             * Valid prefix lengths:
             *
             * L <= q <= m - R
             *
             * Number of valid splits:
             *
             * m - R - L + 1
             */
            int score = m - R - L + 1;

            answer = Math.max(answer, Math.max(0, score));
        }

        return answer;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-valid-split-positions-ii/)