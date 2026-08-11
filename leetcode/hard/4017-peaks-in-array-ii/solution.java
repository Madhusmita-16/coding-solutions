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