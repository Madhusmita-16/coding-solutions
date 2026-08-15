class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        if (k == 0) return;

        // 1. Reverse the complete array
        reverse(nums, 0, n - 1);

        // 2. Reverse the first k elements
        reverse(nums, 0, k - 1);

        // 3. Reverse the remaining elements
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}