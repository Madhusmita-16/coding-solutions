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
**Runtime:** 238 ms (beats 58.19%)  
**Memory:** 286.7 MB (beats 5.20%)  
**Submitted:** 2026-08-11T15:53:31.019Z  

```java
class Solution {

    class Node {
        int count;
        int first;
        int last;
        long value;

        Node() {
            count = 0;
            first = -1;
            last = -1;
            value = 0;
        }
    }

    Node[] tree;
    int n;
    int[] nums;

    public long[] countOfPeaks(int[] nums, int[][] queries) {

        this.nums = nums;
        this.n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        // Initially mark all peaks.
        for (int i = 1; i < n - 1; i++) {
            if (isPeak(i)) {
                update(1, 0, n - 1, i, true);
            }
        }

        long[] temp = new long[queries.length];
        int answerCount = 0;

        for (int[] q : queries) {

            if (q[0] == 1) {

                int l = q[1];
                int r = q[2];

                Node res = query(1, 0, n - 1, l, r);

                if (res.count == 0) {
                    temp[answerCount++] = 0;
                    continue;
                }

                /*
                 * res.value =
                 *
                 * p1 * (p2-p1)
                 * + p2 * (p3-p2)
                 * + ...
                 * + p(k-1) * (pk-p(k-1))
                 */

                long firstPart =
                        res.value
                        - (long) l * (res.last - res.first);

                /*
                 * Last peak can extend its right endpoint
                 * all the way to r.
                 */
                long lastPart =
                        (long) (res.last - l)
                        * (r - res.last);

                temp[answerCount++] =
                        firstPart + lastPart;

            } else {

                int index = q[1];
                int value = q[2];

                /*
                 * Only these three positions can change
                 * their peak status.
                 */
                for (int p = index - 1; p <= index + 1; p++) {

                    if (p >= 1 && p < n - 1) {

                        boolean oldPeak = isPeak(p);

                        if (oldPeak) {
                            update(
                                1,
                                0,
                                n - 1,
                                p,
                                false
                            );
                        }
                    }
                }

                nums[index] = value;

                for (int p = index - 1; p <= index + 1; p++) {

                    if (p >= 1 && p < n - 1) {

                        boolean newPeak = isPeak(p);

                        if (newPeak) {
                            update(
                                1,
                                0,
                                n - 1,
                                p,
                                true
                            );
                        }
                    }
                }
            }
        }

        long[] answer = new long[answerCount];

        System.arraycopy(
                temp,
                0,
                answer,
                0,
                answerCount
        );

        return answer;
    }

    private boolean isPeak(int i) {
        return nums[i] > nums[i - 1]
                && nums[i] > nums[i + 1];
    }

    private void build(int node, int left, int right) {

        tree[node] = new Node();

        if (left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);
    }

    private void update(
            int node,
            int left,
            int right,
            int index,
            boolean peak) {

        if (left == right) {

            tree[node] = new Node();

            if (peak) {
                tree[node].count = 1;
                tree[node].first = index;
                tree[node].last = index;
            }

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(
                node * 2,
                left,
                mid,
                index,
                peak
            );
        } else {
            update(
                node * 2 + 1,
                mid + 1,
                right,
                index,
                peak
            );
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    private Node merge(Node a, Node b) {

        if (a.count == 0) {
            return copy(b);
        }

        if (b.count == 0) {
            return copy(a);
        }

        Node res = new Node();

        res.count = a.count + b.count;

        res.first = a.first;
        res.last = b.last;

        /*
         * Existing consecutive peak pairs.
         */
        res.value = a.value + b.value;

        /*
         * Connect the last peak of the left part
         * with the first peak of the right part.
         *
         * Contribution:
         *
         * a.last * (b.first - a.last)
         */
        res.value +=
                (long) a.last
                * (b.first - a.last);

        return res;
    }

    private Node copy(Node x) {

        Node res = new Node();

        res.count = x.count;
        res.first = x.first;
        res.last = x.last;
        res.value = x.value;

        return res;
    }

    private Node query(
            int node,
            int left,
            int right,
            int ql,
            int qr) {

        if (ql <= left && right <= qr) {
            return copy(tree[node]);
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(
                node * 2,
                left,
                mid,
                ql,
                qr
            );
        }

        if (ql > mid) {
            return query(
                node * 2 + 1,
                mid + 1,
                right,
                ql,
                qr
            );
        }

        Node a = query(
            node * 2,
            left,
            mid,
            ql,
            qr
        );

        Node b = query(
            node * 2 + 1,
            mid + 1,
            right,
            ql,
            qr
        );

        return merge(a, b);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/peaks-in-array-ii/)