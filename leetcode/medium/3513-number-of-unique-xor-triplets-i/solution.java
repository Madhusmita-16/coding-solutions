class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        // Since nums is a permutation of 1..n,
        // every value from 1 to n is available.
        //
        // For n >= 3, every XOR value from 0 to the
        // next power-of-two range can be formed.
        int max = 1;
        while (max <= n) {
            max <<= 1;
        }

        if (n < 3) {
            return n;
        }

        return max;
    }
}