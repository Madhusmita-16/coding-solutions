class Solution {
    public int minKBitFlips(int[] nums, int k) {

        int n = nums.length;
        int[] diff = new int[n + 1];

        int flips = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {

            // Remove the effect of a flip that ended here
            flips ^= diff[i];

            // Current value after all active flips
            if ((nums[i] ^ flips) == 0) {

                // Cannot flip if there aren't k elements remaining
                if (i + k > n) {
                    return -1;
                }

                // Start a new flip
                flips ^= 1;
                diff[i + k] ^= 1;

                answer++;
            }
        }

        return answer;
    }
}