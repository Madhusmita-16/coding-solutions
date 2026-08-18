class Solution {
    public int divide(int dividend, int divisor) {

        // Overflow case: -2^31 / -1 = 2^31
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine whether the result is negative
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert to positive long values safely
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long quotient = 0;

        // Find the quotient using powers of 2
        while (a >= b) {

            long temp = b;
            long multiple = 1;

            // Keep doubling divisor while possible
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            // Subtract the largest possible multiple
            a -= temp;
            quotient += multiple;
        }

        if (negative) {
            quotient = -quotient;
        }

        // Clamp to 32-bit integer range
        if (quotient > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (quotient < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return (int) quotient;
    }
}