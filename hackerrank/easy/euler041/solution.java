import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static ArrayList<Integer> primes = new ArrayList<Integer>();

    static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        if (n % 2 == 0) {
            return n == 2;
        }

        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static void generate(
            int number,
            int mask,
            int used,
            int total) {

        if (used == total) {

            if (isPrime(number)) {
                primes.add(number);
            }

            return;
        }

        for (int digit = 1; digit <= total; digit++) {

            int bit = 1 << digit;

            if ((mask & bit) != 0) {
                continue;
            }

            generate(
                    number * 10 + digit,
                    mask | bit,
                    used + 1,
                    total
            );
        }
    }

    static void buildPrimes() {

        // Only 4-digit pandigital numbers can be prime
        // among lengths 1 through 6.
        generate(0, 0, 0, 4);

        // 7-digit pandigital primes can also exist.
        generate(0, 0, 0, 7);

        Collections.sort(primes);
    }

    static int findAnswer(long n) {

        int left = 0;
        int right = primes.size() - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int value = primes.get(mid);

            /*
             * IMPORTANT:
             * HackerRank version uses <= N.
             */
            if ((long) value <= n) {

                answer = value;
                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        long[] queries = new long[t];

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextLong();
        }

        buildPrimes();

        for (int i = 0; i < t; i++) {
            System.out.println(findAnswer(queries[i]));
        }

        sc.close();
    }
}
