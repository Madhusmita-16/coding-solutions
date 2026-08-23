class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int sum = 0;
        int q = 0;

        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '?') {
                if (i < half) {
                    q++;
                } else {
                    q--;
                }
            } else {
                int digit = num.charAt(i) - '0';

                if (i < half) {
                    sum += digit;
                } else {
                    sum -= digit;
                }
            }
        }

        // If number of '?' is odd, Alice gets one extra move.
        if (q % 2 != 0) {
            return true;
        }
        if (q > 0) {
            return sum + 9 * (q / 2) != 0;
        } else {
            return sum - 9 * (-q / 2) != 0;
        }
    }
}