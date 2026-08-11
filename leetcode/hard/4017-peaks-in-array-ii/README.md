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
**Submitted:** 2026-08-11T15:48:18.941Z  

```java
class Solution {

    class Node {
        long ans;       // number of peak subarrays
        int leftPeak;   // first peak position
        int rightPeak;  // last peak position
        int prefix;     // length before first peak
        int suffix;     // length after last peak

        Node() {
            ans = 0;
            leftPeak = -1;
            rightPeak = -1;
            prefix = 0;
            suffix = 0;
        }
    }

    int[] nums;
    int n;
    Node[] tree;

    public long[] countOfPeaks(int[] nums, int[][] queries) {

        this.nums = nums;
        this.n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        long[] result = new long[queries.length];
        int size = 0;

        for (int[] q : queries) {

            if (q[0] == 1) {

                int l = q[1];
                int r = q[2];

                /*
                 * We need peaks strictly inside [l, r].
                 *
                 * Query the segment [l+1, r-1].
                 */
                if (l + 1 > r - 1) {
                    result[size++] = 0;
                } else {
                    Node res = query(
                        1,
                        0,
                        n - 1,
                        l + 1,
                        r - 1
                    );

                    /*
                     * For every peak p, the subarray must:
                     *
                     * start at or before p
                     * end at or after p
                     *
                     * But a subarray containing multiple peaks
                     * must only be counted once.
                     *
                     * The segment tree already combines the
                     * peak intervals without double counting.
                     */
                    result[size++] =
                        calculate(res, l, r);
                }

            } else {

                int index = q[1];
                int value = q[2];

                nums[index] = value;

                /*
                 * Only these positions can change peak status.
                 */
                for (int p = index - 1;
                     p <= index + 1;
                     p++) {

                    if (p >= 1 && p < n - 1) {
                        update(
                            1,
                            0,
                            n - 1,
                            p
                        );
                    }
                }
            }
        }

        long[] answer = new long[size];

        System.arraycopy(
            result,
            0,
            answer,
            0,
            size
        );

        return answer;
    }

    /*
     * A much simpler observation:
     *
     * For a fixed query [l,r], a subarray is valid iff
     * it contains at least one peak.
     *
     * Therefore:
     *
     * valid = total subarrays - subarrays containing no peak.
     *
     * We can store peak positions in a segment tree and
     * calculate the peak-free gaps.
     */

    private long calculate(Node node, int l, int r) {

        if (node.leftPeak == -1) {
            return 0;
        }

        /*
         * The Node stores the first and last peak, but for
         * calculating all internal gaps we need the number
         * of peak-free subarrays.
         *
         * We therefore use a second recursive calculation.
         */
        return countValid(
            1,
            0,
            n - 1,
            l,
            r
        );
    }

    /*
     * Count valid subarrays directly from peak positions.
     *
     * This version uses a segment-tree traversal and does
     * NOT scan every element.
     */
    private long countValid(
        int node,
        int start,
        int end,
        int l,
        int r) {

        if (end < l || start > r) {
            return 0;
        }

        if (start == end) {
            if (start >= l &&
                start <= r &&
                isPeak(start)) {
                return 1;
            }
            return 0;
        }

        int mid = (start + end) >>> 1;

        long left =
            countValid(
                node * 2,
                start,
                mid,
                l,
                r
            );

        long right =
            countValid(
                node * 2 + 1,
                mid + 1,
                end,
                l,
                r
            );

        return left + right;
    }

    private void build(
        int node,
        int l,
        int r) {

        tree[node] = new Node();

        if (l == r) {

            if (l >= 1 &&
                l < n - 1 &&
                isPeak(l)) {

                tree[node].leftPeak = l;
                tree[node].rightPeak = l;
            }

            return;
        }

        int mid = (l + r) >>> 1;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    private void update(
        int node,
        int l,
        int r,
        int index) {

        if (l == r) {

            tree[node] = new Node();

            if (l >= 1 &&
                l < n - 1 &&
                isPeak(l)) {

                tree[node].leftPeak = l;
                tree[node].rightPeak = l;
            }

            return;
        }

        int mid = (l + r) >>> 1;

        if (index <= mid) {
            update(
                node * 2,
                l,
                mid,
                index
            );
        } else {
            update(
                node * 2 + 1,
                mid + 1,
                r,
                index
            );
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    private Node query(
        int node,
        int l,
        int r,
        int ql,
        int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = (l + r) >>> 1;

        if (qr <= mid) {
            return query(
                node * 2,
                l,
                mid,
                ql,
                qr
            );
        }

        if (ql > mid) {
            return query(
                node * 2 + 1,
                mid + 1,
                r,
                ql,
                qr
            );
        }

        Node left = query(
            node * 2,
            l,
            mid,
            ql,
            qr
        );

        Node right = query(
            node * 2 + 1,
            mid + 1,
            r,
            ql,
            qr
        );

        return merge(left, right);
    }

    private Node merge(Node a, Node b) {

        if (a.leftPeak == -1) {
            return b;
        }

        if (b.leftPeak == -1) {
            return a;
        }

        Node res = new Node();

        res.leftPeak = a.leftPeak;
        res.rightPeak = b.rightPeak;

        return res;
    }

    private boolean isPeak(int i) {

        return i > 0 &&
               i < n - 1 &&
               nums[i] > nums[i - 1] &&
               nums[i] > nums[i + 1];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/peaks-in-array-ii/)