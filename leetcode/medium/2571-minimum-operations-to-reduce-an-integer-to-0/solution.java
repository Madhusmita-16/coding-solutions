class Solution {
    public int minOperations(int n) {
        int count = 0;

        while (n > 0) {
            if ((n & 1) == 0) {
                // n is even
                n >>= 1;
            } else {
                // n is odd
                if (n == 1 || n == 3) {
                    n -= 1;
                } else if ((n & 3) == 1) {
                    // Ends with 01 -> subtract 1
                    n -= 1;
                } else {
                    // Ends with 11 -> add 1
                    n += 1;
                }

                count++;
            }
        }

        return count;
    }
}