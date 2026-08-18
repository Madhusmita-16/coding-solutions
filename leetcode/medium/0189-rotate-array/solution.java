class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;

        int count = 0;
        int start = 0;

        while (count < n) {
            int current = start;
            int temp = nums[current];

            while (true) {
                int next = (current + k) % n;

                int swap = nums[next];
                nums[next] = temp;
                temp = swap;

                current = next;
                count++;

                if (current == start) {
                    break;
                }
            }

            start++;
        }
    }
}