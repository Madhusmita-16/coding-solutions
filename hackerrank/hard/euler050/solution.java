import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    static final int LIMIT = 6000000;

    static int[] primes;
    static long[] prefix;

    static void buildPrimes() {

        boolean[] composite = new boolean[LIMIT];

        int count = 0;

        for (int i = 2; i < LIMIT; i++) {

            if (!composite[i]) {

                count++;

                if ((long) i * i < LIMIT) {

                    for (int j = i * i; j < LIMIT; j += i) {
                        composite[j] = true;
                    }
                }
            }
        }

        primes = new int[count];

        int index = 0;

        for (int i = 2; i < LIMIT; i++) {

            if (!composite[i]) {
                primes[index++] = i;
            }
        }

        prefix = new long[primes.length + 1];

        for (int i = 0; i < primes.length; i++) {
            prefix[i + 1] = prefix[i] + primes[i];
        }
    }

    /*
     * Prime test for values <= 10^12.
     */
    static boolean isPrime(long n) {

        if (n < 2) {
            return false;
        }

        return BigInteger.valueOf(n).isProbablePrime(20);
    }

    /*
     * Largest index i such that:
     *
     * prefix[i] <= value
     */
    static int upperBound(long value) {

        int left = 0;
        int right = prefix.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (prefix[mid] <= value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    static String solve(long n) {

        long bestPrime = 0;
        int bestLength = 0;

        /*
         * For N <= 10^12, it is sufficient to consider
         * starting primes up to 131.
         */
        for (int start = 0;
             start < primes.length && primes[start] <= 131;
             start++) {

            if (primes[start] > n) {
                break;
            }

            long subtract = prefix[start];

            /*
             * IMPORTANT:
             * N is INCLUSIVE.
             *
             * prefix[end] - prefix[start] <= n
             */
            long target = n + subtract;

            int end = upperBound(target);

            int length = end - start;

            if (length <= bestLength) {
                continue;
            }

            /*
             * If starting after 2, all primes are odd.
             * An even number of odd primes has an even sum,
             * so only odd lengths can produce a prime.
             */
            if (start > 0 && (length & 1) == 0) {
                length--;
            }

            while (length > bestLength) {

                long sum =
                        prefix[start + length] -
                        prefix[start];

                /*
                 * IMPORTANT:
                 * sum <= n, not sum < n.
                 */
                if (sum <= n && isPrime(sum)) {

                    if (length > bestLength ||
                        (length == bestLength &&
                         (bestPrime == 0 || sum < bestPrime))) {

                        bestLength = length;
                        bestPrime = sum;
                    }

                    break;
                }

                if (start == 0) {
                    length--;
                } else {
                    length -= 2;
                }
            }
        }

        /*
         * Handle very small N safely.
         */
        if (bestLength == 0) {

            if (n >= 2) {
                return "2 1";
            }

            return "0 0";
        }

        return bestPrime + " " + bestLength;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        long[] input = new long[t];

        for (int i = 0; i < t; i++) {
            input[i] = sc.nextLong();
        }

        /*
         * Build primes only once.
         */
        buildPrimes();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {

            output.append(solve(input[i]));

            if (i + 1 < t) {
                output.append('\n');
            }
        }

        System.out.print(output);

        sc.close();
    }
}
