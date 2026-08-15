class Solution {
    public int singleNumber(int[] nums) {

        int ones = 0;
        int twos = 0;

        for (int num : nums) {

            // Bits appearing twice
            twos |= ones & num;

            // Bits appearing once
            ones ^= num;

            // Remove bits that appeared three times
            int threes = ones & twos;

            ones &= ~threes;
            twos &= ~threes;
        }

        return ones;
    }
}