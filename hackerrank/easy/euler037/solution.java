import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    static String toBase(long num, int base) {

        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int digit = (int)(num % base);

            // Digits are represented using 0-9, A-Z
            if (digit < 10) {
                sb.append((char)('0' + digit));
            } else {
                sb.append((char)('A' + digit - 10));
            }

            num /= base;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        int base = sc.nextInt();

        long sum = 0;

        for (long i = 1; i < n; i++) {

            // Check palindrome in decimal.
            if (!isPalindrome(String.valueOf(i))) {
                continue;
            }

            // Convert to the required base and check palindrome.
            String representation = toBase(i, base);

            if (isPalindrome(representation)) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}
