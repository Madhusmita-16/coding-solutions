class Solution {
    public String[] largestString(int[] nums) {

        String[] ans = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {

            long x = nums[i];
            StringBuilder sb = new StringBuilder();

            // z represents 2^25 'a's
            long zValue = 1L << 25;

            // Take as many z's as possible
            while (x >= zValue) {
                sb.append('z');
                x -= zValue;
            }

            // Represent the remaining value using y -> a
            for (int bit = 24; bit >= 0; bit--) {

                long value = 1L << bit;

                if (x >= value) {
                    sb.append((char) ('a' + bit));
                    x -= value;
                }
            }

            ans[i] = sb.toString();
        }

        return ans;
    }
}