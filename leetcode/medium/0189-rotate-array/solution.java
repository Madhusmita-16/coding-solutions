class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;

        int count = 0;

        for (int start = 0; count < n; start++) {
            int current = start;
            int prev = nums[current];

            do {
                int next = (current + k) % n;

                int temp = nums[next];
                nums[next] = prev;
                prev = temp;

                current = next;
                count++;

            } while (current != start);
        }
    }
}