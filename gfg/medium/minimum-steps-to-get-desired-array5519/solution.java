class Solution {
    public int countMinOperations(int arr[]) {
        long increments = 0;
        int maxBits = 0;

        for (int x : arr) {
            int bits = 0;
            int value = x;

            while (value > 0) {
                if ((value & 1) == 1) {
                    increments++;
                }

                bits++;
                value >>= 1;
            }

            maxBits = Math.max(maxBits, bits);
        }

        if (maxBits == 0) {
            return 0;
        }

        return (int) (increments + maxBits - 1);
    }
}