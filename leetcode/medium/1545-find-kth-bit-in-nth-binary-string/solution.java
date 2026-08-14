class Solution {
    public char findKthBit(int n, int k) {

        // Base case: S1 = "0"
        if (n == 1) {
            return '0';
        }

        int mid = 1 << (n - 1);

        // Middle bit is always 1
        if (k == mid) {
            return '1';
        }

        // First half
        if (k < mid) {
            return findKthBit(n - 1, k);
        }

        // Second half
        int mirror = (1 << n) - k;

        char bit = findKthBit(n - 1, mirror);

        // Invert the bit
        return bit == '0' ? '1' : '0';
    }
}