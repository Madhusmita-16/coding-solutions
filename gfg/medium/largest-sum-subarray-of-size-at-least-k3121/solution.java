class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;

        long windowSum = 0;

        // Sum of first k elements
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        long maxSum = windowSum;
        long bestEndingHere = windowSum;

        for (int i = k; i < n; i++) {
            // Sum of the current window of size k
            windowSum += arr[i] - arr[i - k];

            // Either extend the previous subarray
            // or start with the current k-sized window
            bestEndingHere = Math.max(windowSum, bestEndingHere + arr[i]);

            maxSum = Math.max(maxSum, bestEndingHere);
        }

        return (int) maxSum;
    }
}