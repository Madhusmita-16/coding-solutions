class Solution {

    class Fenwick {
        long[] tree;

        Fenwick(int n) {
            tree = new long[n + 1];
        }

        void add(int index, long value) {
            index++;

            while (index < tree.length) {
                tree[index] += value;
                index += index & -index;
            }
        }

        long sum(int index) {
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

    int[] nums;
    int n;

    Fenwick count;
    Fenwick position;

    public long[] countOfPeaks(int[] nums, int[][] queries) {

        this.nums = nums;
        this.n = nums.length;

        count = new Fenwick(n);
        position = new Fenwick(n);

        // Initially mark all peaks
        for (int i = 1; i < n - 1; i++) {
            if (isPeak(i)) {
                addPeak(i, 1);
            }
        }

        java.util.ArrayList<Long> ans = new java.util.ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int l = q[1];
                int r = q[2];

                ans.add(query(l, r));

            } else {

                int index = q[1];
                int value = q[2];

                /*
                 * Only these positions can change
                 * their peak status.
                 */
                for (int p = index - 1; p <= index + 1; p++) {

                    if (p >= 1 && p < n - 1 && isPeak(p)) {
                        addPeak(p, -1);
                    }
                }

                nums[index] = value;

                for (int p = index - 1; p <= index + 1; p++) {

                    if (p >= 1 && p < n - 1 && isPeak(p)) {
                        addPeak(p, 1);
                    }
                }
            }
        }

        long[] result = new long[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            result[i] = ans.get(i);
        }

        return result;
    }

    private long query(int l, int r) {

        /*
         * Peaks must be strictly inside [l, r].
         */
        int left = l + 1;
        int right = r - 1;

        if (left > right) {
            return 0;
        }

        /*
         * Find the first peak >= left.
         */
        int totalPeaks = (int) count.sum(n - 1);

        int before = (int) count.sum(left - 1);

        if (before == totalPeaks) {
            return 0;
        }

        /*
         * We process consecutive peaks.
         *
         * For a peak p, assign a subarray to p only if
         * p is its RIGHTMOST peak.
         *
         * If q is the next peak:
         *
         * number of possible starts = p - l + 1
         *
         * number of possible right endpoints =
         * q - p - 1
         *
         * For the last peak:
         * right endpoint can go through r.
         */

        long result = 0;

        int previousPeak = -1;

        for (int p = left; p <= right; p++) {

            if (!isPeak(p)) {
                continue;
            }

            if (previousPeak != -1) {
                result += (long) (previousPeak - l + 1)
                        * (p - previousPeak - 1);
            }

            previousPeak = p;
        }

        if (previousPeak != -1) {
            result += (long) (previousPeak - l + 1)
                    * (r - previousPeak);
        }

        return result;
    }

    private boolean isPeak(int i) {
        return nums[i] > nums[i - 1]
                && nums[i] > nums[i + 1];
    }

    private void addPeak(int index, int delta) {
        count.add(index, delta);
        position.add(index, (long) index * delta);
    }
}