import java.io.*;
import java.util.*;

public class Solution {

    static long[] factorial = new long[10];

    static void precompute() {
        factorial[0] = 1;

        for (int i = 1; i <= 9; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    static long digitFactorialSum(long num) {
        long sum = 0;

        while (num > 0) {
            int digit = (int)(num % 10);
            sum += factorial[digit];
            num /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        precompute();

        long answer = 0;

        for (long i = 10; i < n; i++) {

            long sum = digitFactorialSum(i);

            if (sum % i == 0) {
                answer += i;
            }
        }

        System.out.println(answer);
    }
}
