class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find indices of minimum and maximum
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Put smaller index first
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);

        // 1. Remove both from the front
        int fromFront = right + 1;

        // 2. Remove both from the back
        int fromBack = n - left;

        // 3. Remove left element from front
        //    and right element from back
        int oneEach = (left + 1) + (n - right);

        return Math.min(fromFront, Math.min(fromBack, oneEach));
    }
}