class Solution {
    public int countSubarray(int[] arr, int l, int r) {
        return countAtMost(arr, r) - countAtMost(arr, l - 1);
    }

    private int countAtMost(int[] arr, int limit) {
        if (limit < 0) {
            return 0;
        }

        long sum = 0;
        long count = 0;
        int left = 0;

        for (int right = 0; right < arr.length; right++) {
            sum += arr[right];

            // Remove elements until sum <= limit
            while (sum > limit) {
                sum -= arr[left++];
            }

            // All subarrays ending at right and
            // starting from left to right are valid
            count += right - left + 1;
        }

        return (int) count;
    }
}