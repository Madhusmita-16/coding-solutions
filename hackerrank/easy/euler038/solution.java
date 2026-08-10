import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPandigital(String s, int k) {

        if (s.length() != k) {
            return false;
        }

        boolean[] used = new boolean[10];

        for (char c : s.toCharArray()) {

            int digit = c - '0';

            // K-pandigital uses digits 1..K.
            if (digit == 0 || digit > k) {
                return false;
            }

            if (used[digit]) {
                return false;
            }

            used[digit] = true;
        }

        return true;
    }

    static boolean isValid(int x, int k) {

        StringBuilder sb = new StringBuilder();

        for (int multiplier = 1; ; multiplier++) {

            sb.append(x * multiplier);

            if (sb.length() > k) {
                return false;
            }

            if (sb.length() == k) {
                return isPandigital(sb.toString(), k);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        /*
         * For an 8/9 digit pandigital concatenation,
         * the multiplier cannot have more than 4 digits.
         *
         * Testing beyond 9999 is unnecessary because
         * x * 1 already occupies 5+ digits and the next
         * product makes the concatenation too long.
         */
        int limit = Math.min(n - 1, 9999);

        for (int x = 2; x <= limit; x++) {

            if (isValid(x, k)) {
                System.out.println(x);
            }
        }
    }
}
