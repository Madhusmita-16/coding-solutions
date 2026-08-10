import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Remove digits from right to left
    static boolean truncatableFromRight(int n) {
        while (n > 0) {
            if (!isPrime(n)) {
                return false;
            }

            n /= 10;
        }

        return true;
    }

    // Remove digits from left to right
    static boolean truncatableFromLeft(int n) {

        int divisor = 1;

        while (divisor <= n / 10) {
            divisor *= 10;
        }

        while (divisor > 0) {

            if (!isPrime(n)) {
                return false;
            }

            n %= divisor;
            divisor /= 10;
        }

        return true;
    }

    static boolean isTruncatablePrime(int n) {

        // 2, 3, 5, 7 are not considered truncatable.
        if (n < 10) {
            return false;
        }

        return isPrime(n)
                && truncatableFromRight(n)
                && truncatableFromLeft(n);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long sum = 0;

        for (int i = 10; i < n; i++) {

            if (isTruncatablePrime(i)) {
                sum += i;
            }
        }

        System.out.println(sum);
    }
}
