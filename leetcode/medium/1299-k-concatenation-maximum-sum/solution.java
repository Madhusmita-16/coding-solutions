class Solution {
    private static final long MOD = 1_000_000_007L;

    public int kConcatenationMaxSum(int[] arr, int k) {
        long totalSum = 0;
        long prefix = 0;
        long maxPrefix = 0;
        long suffix = 0;
        long maxSuffix = 0;
        long maxSubarray = 0;
        long current = 0;

        for (int x : arr) {
            totalSum += x;

            prefix += x;
            maxPrefix = Math.max(maxPrefix, prefix);

            current = Math.max(0, current + x);
            maxSubarray = Math.max(maxSubarray, current);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            suffix += arr[i];
            maxSuffix = Math.max(maxSuffix, suffix);
        }

        long answer;

        if (k == 1) {
            answer = maxSubarray;
        } else {
            // Best subarray lies within two copies
            answer = Math.max(maxSubarray, maxPrefix + maxSuffix);

            // If total sum is positive, middle copies can contribute
            if (totalSum > 0) {
                answer = Math.max(
                    answer,
                    maxPrefix + maxSuffix + totalSum * (k - 2L)
                );
            }
        }

        return (int) (answer % MOD);
    }
}