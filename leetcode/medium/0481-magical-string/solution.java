class Solution {
    public int magicalString(int n) {

        if (n == 0) {
            return 0;
        }

        if (n <= 3) {
            return 1;
        }

        int[] s = new int[n + 2];

        // First three characters: 122
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;

        int read = 2;
        int write = 3;
        int num = 1;
        int countOnes = 1;

        while (write < n) {

            int times = s[read];

            for (int i = 0; i < times && write < n; i++) {

                s[write] = num;

                if (num == 1) {
                    countOnes++;
                }

                write++;
            }

            // Alternate between 1 and 2
            num = 3 - num;

            read++;
        }

        return countOnes;
    }
}