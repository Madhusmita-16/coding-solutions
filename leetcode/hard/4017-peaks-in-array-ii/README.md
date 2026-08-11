# Q4. Peaks in Array II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `nums` of length `n` and a 2D integer array `queries`.

A  **subarray**  `nums[i..j]` is called a  **peak subarray**  if:

- Its length is at least 3.
- There exists an index k such that i < k < j and: nums[k] > nums[k - 1] nums[k] > nums[k + 1]

You have to process queries of two types:

- [1, li, ri]: Calculate the number of peak subarrays fully contained within nums[li..ri].
- [2, indexi, vali]: Update nums[indexi] to vali. This update applies to all subsequent queries.

Return an array `answer`, where `answer[i]` is the answer to the `ith` query of type 1 in the order they appear.

 

 **Example 1:** 

 **Input:**  nums = [1,3,2,4], queries = [[1,0,3],[2,1,1],[1,0,3]]

 **Output:**  [2,0]

 **Explanation:** ​​​​​​​

- Query [1, 0, 3]: [1, 3, 2]: choose k = 1. Then nums[k] = 3, nums[k - 1] = 1, and nums[k + 1] = 2. Since 3 > 1 and 3 > 2, this is a peak subarray. [1, 3, 2, 4]: choose k = 1. Then nums[k] = 3, nums[k - 1] = 1, and nums[k + 1] = 2. Since 3 > 1 and 3 > 2, this is a peak subarray.
- Query [2, 1, 1]: Update nums[1] to 1. The array becomes [1, 1, 2, 4].
- Query [1, 0, 3]: There are no peak subarrays now.
- Thus, answer = [2, 0].

 **Example 2:** 

 **Input:**  nums = [9,8,9,8], queries = [[1,1,3],[2,2,1],[1,0,2]]

 **Output:**  [1,0]

 **Explanation:** 

- Query [1, 1, 3]: nums[1..3] = [8, 9, 8]: choose k = 2. Then nums[k] = 9, nums[k - 1] = 8, and nums[k + 1] = 8. Since 9 > 8 and 9 > 8, this is a peak subarray.
- Query [2, 2, 1]: Update nums[2] to 1. The array becomes [9, 8, 1, 8].
- Query [1, 0, 2]: There are no peak subarrays.
- Thus, answer = [1, 0].

 **Example 3:** 

 **Input:**  nums = [3,6,2,7,1], queries = [[1,1,3],[2,3,0],[1,0,4]]

 **Output:**  [0,3]

 **Explanation:** 

- Query [1, 1, 3]: The only subarray of length at least 3 is [6, 2, 7]. Its only possible peak index is k = 2, but nums[2] = 2 is less than both nums[1] = 6 and nums[3] = 7, so it is not a peak subarray.
- Query [2, 3, 0]: Update nums[3] to 0. The array becomes [3, 6, 2, 0, 1].
- Query [1, 0, 4]: [3, 6, 2]: choose k = 1. Then nums[k] = 6, nums[k - 1] = 3, and nums[k + 1] = 2. Since 6 > 3 and 6 > 2, this is a peak subarray. [3, 6, 2, 0]: choose k = 1. Then nums[k] = 6, nums[k - 1] = 3, and nums[k + 1] = 2. Since 6 > 3 and 6 > 2, this is a peak subarray. [3, 6, 2, 0, 1]: choose k = 1. Then nums[k] = 6, nums[k - 1] = 3, and nums[k + 1] = 2. Since 6 > 3 and 6 > 2, this is a peak subarray.
- Thus, answer = [0, 3].

 

 **Constraints:** 

- 3 <= n == nums.length <= 105
- 0 <= nums[i] <= 105
- 1 <= queries.length <= 105
- queries[i] = [1, li, ri] or queries[i] = [2, indexi, vali]
- 0 <= li < ri <= n - 1
- 0 <= indexi <= n - 1
- 0 <= vali <= 105

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 43 MB  
**Submitted:** 2026-08-11T15:45:05.165Z  

```java
class Solution {

    class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int index, long value) {
            index++; // 1-based
            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
            // sum of [0 ... index]
            if (index < 0) {
                return 0;
            }

            index++;
            long result = 0;

            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }

        long rangeSum(int left, int right) {
            if (left > right) {
                return 0;
            }

            return sum(right) - sum(left - 1);
        }
    }

    private int[] nums;

    private Fenwick countTree;
    private Fenwick indexTree;
    private Fenwick squareTree;

    public long[] countOfPeaks(int[] nums, int[][] queries) {

        this.nums = nums;

        int n = nums.length;

        countTree = new Fenwick(n);
        indexTree = new Fenwick(n);
        squareTree = new Fenwick(n);

        // Add all initial peaks
        for (int i = 1; i < n - 1; i++) {
            if (isPeak(i)) {
                addPeak(i, 1);
            }
        }

        java.util.ArrayList<Long> result = new java.util.ArrayList<>();

        for (int[] query : queries) {

            if (query[0] == 1) {

                int l = query[1];
                int r = query[2];

                /*
                 * A peak must be strictly inside [l, r].
                 *
                 * k must satisfy:
                 * l < k < r
                 */
                int left = l + 1;
                int right = r - 1;

                if (left > right) {
                    result.add(0L);
                    continue;
                }

                long cnt = countTree.rangeSum(left, right);
                long sumK = indexTree.rangeSum(left, right);
                long sumK2 = squareTree.rangeSum(left, right);

                /*
                 * For a peak k:
                 *
                 * (k - l) * (r - k)
                 *
                 * = kr - k^2 - lr + lk
                 *
                 * = -k^2 + k(l+r) - lr
                 *
                 * Therefore:
                 *
                 * answer =
                 * - sum(k^2)
                 * + (l+r) * sum(k)
                 * - l*r * count
                 */
                long answer =
                        -sumK2
                        + (long) (l + r) * sumK
                        - (long) l * r * cnt;

                result.add(answer);

            } else {

                int index = query[1];
                int value = query[2];

                /*
                 * Changing nums[index] can only affect
                 * peak status at:
                 *
                 * index - 1
                 * index
                 * index + 1
                 */
                for (int p = index - 1; p <= index + 1; p++) {

                    if (p <= 0 || p >= n - 1) {
                        continue;
                    }

                    // Remove old peak status
                    if (isPeak(p)) {
                        addPeak(p, -1);
                    }
                }

                // Perform update
                nums[index] = value;

                // Add new peak status
                for (int p = index - 1; p <= index + 1; p++) {

                    if (p <= 0 || p >= n - 1) {
                        continue;
                    }

                    if (isPeak(p)) {
                        addPeak(p, 1);
                    }
                }
            }
        }

        long[] answer = new long[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private boolean isPeak(int i) {
        return nums[i] > nums[i - 1]
                && nums[i] > nums[i + 1];
    }

    private void addPeak(int index, int delta) {

        countTree.add(index, delta);

        indexTree.add(index, (long) delta * index);

        squareTree.add(
                index,
                (long) delta * index * index
        );
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/peaks-in-array-ii/)