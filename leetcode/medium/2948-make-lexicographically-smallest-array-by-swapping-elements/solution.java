class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store value and original index
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // original index
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int start = 0;

        while (start < n) {

            int end = start;

            // Find all values belonging to the same group
            while (end + 1 < n &&
                   (long) arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            // Original indices of this group
            List<Integer> indices = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indices.add(arr[i][1]);
            }

            // Sort indices
            Collections.sort(indices);

            // Values are already sorted
            for (int i = 0; i < indices.size(); i++) {
                nums[indices.get(i)] = arr[start + i][0];
            }

            start = end + 1;
        }

        return nums;
    }
}