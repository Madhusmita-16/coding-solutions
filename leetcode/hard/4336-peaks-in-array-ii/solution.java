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