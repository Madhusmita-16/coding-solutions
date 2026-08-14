class Solution {
    public int findGCD(int[] nums) {

        int min = nums[0];
        int max = nums[0];

        // Find smallest and largest numbers
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // Euclidean Algorithm
        while (max % min != 0) {
            int remainder = max % min;
            max = min;
            min = remainder;
        }

        return min;
    }
}