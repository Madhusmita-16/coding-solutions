import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static boolean isPrime(long n) {

        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            long n = sc.nextLong();

            int count = 0;

            /*
             * N = prime + 2 * square
             *
             * Therefore:
             *
             * prime = N - 2 * i * i
             *
             * Try every possible square.
             */
            for (long i = 1; 2 * i * i < n; i++) {

                long prime = n - 2 * i * i;

                if (isPrime(prime)) {
                    count++;
                }
            }

            System.out.println(count);
        }

        sc.close();
    }
}
