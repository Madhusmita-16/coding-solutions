class Solution {
    public int findMax(int n, int[] a, int[] b, int[] k) {
        long[] diff = new long[n + 1];

        // Apply all range increments using difference array
        for (int i = 0; i < a.length; i++) {
            diff[a[i]] += k[i];

            if (b[i] + 1 < n) {
                diff[b[i] + 1] -= k[i];
            }
        }

        // Prefix sum gives the final value of each array element
        long current = 0;
        long max = 0;

        for (int i = 0; i < n; i++) {
            current += diff[i];
            max = Math.max(max, current);
        }

        return (int) max;
    }
}
