class Solution {
    public long distantSubarrays(int[] nums, int goal, int k) {
        int[] mireqovalt = nums;
        int n = mireqovalt.length;

        long total = (long) n * (n + 1) / 2;

        if (k == 0) {
            return total;
        }

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + mireqovalt[i];
        }

        long[] sorted = prefix.clone();
        java.util.Arrays.sort(sorted);

        Fenwick fenwick = new Fenwick(n + 1);
        long nonDistant = 0;

        for (int j = 0; j <= n; j++) {
            long p = prefix[j];

            long low = p - (long) goal - k;
            long high = p - (long) goal + k;

            int left = upperBound(sorted, low);
            int right = lowerBound(sorted, high);

            nonDistant += fenwick.query(right) - fenwick.query(left);

            int pos = lowerBound(sorted, p) + 1;
            fenwick.add(pos, 1);
        }

        return total - nonDistant;
    }

    private int lowerBound(long[] a, long x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] < x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    private int upperBound(long[] a, long x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] <= x) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 1];
        }

        void add(int i, int value) {
            while (i < tree.length) {
                tree[i] += value;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}