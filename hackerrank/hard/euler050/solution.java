import java.io.*;
import java.util.*;

public class Solution {

    static boolean[] isPrime;
    static int[] primes;
    static long[] prefix;

    static void sieve(int n) {

        isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; (long) i * i <= n; i++) {

            if (isPrime[i]) {

                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }

        primes = new int[count];

        int p = 0;

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes[p++] = i;
            }
        }

        prefix = new long[count + 1];

        for (int i = 0; i < count; i++) {
            prefix[i + 1] = prefix[i] + primes[i];
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];

        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();

            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        sieve(maxN);

        /*
         * bestPrime[x]  = prime giving longest chain below x
         * bestLength[x] = corresponding chain length
         */
        int[] bestPrime = new int[maxN + 1];
        int[] bestLength = new int[maxN + 1];

        int bestP = 0;
        int bestL = 0;

        /*
         * Calculate the best answer for each possible limit.
         *
         * We only need to examine chains starting from
         * the beginning of the prime list. For a given length,
         * the smallest possible sum is obtained from the
         * earliest primes, which makes this very efficient.
         */
        for (int start = 0; start < primes.length; start++) {

            long sum = 0;

            for (int end = start; end < primes.length; end++) {

                sum += primes[end];

                if (sum > maxN) {
                    break;
                }

                int length = end - start + 1;

                if (isPrime[(int) sum]) {

                    if (length > bestL ||
                        (length == bestL &&
                         (bestP == 0 || sum < bestP))) {

                        bestL = length;
                        bestP = (int) sum;

                        /*
                         * This answer is valid for every limit
                         * greater than the sum.
                         */
                    }
                }
            }
        }

        /*
         * Convert exact results into:
         * best answer for every limit <= maxN.
         */
        int currentPrime = 0;
        int currentLength = 0;

        for (int n = 0; n <= maxN; n++) {

            if (n > 0) {

                /*
                 * A newly discovered prime sum may become
                 * available at this limit.
                 *
                 * Recalculate only when needed below.
                 */
            }

            bestPrime[n] = currentPrime;
            bestLength[n] = currentLength;
        }

        /*
         * Build answers directly for each query.
         *
         * This second calculation uses the fact that only
         * chain lengths larger than the current best matter.
         */
        currentPrime = 0;
        currentLength = 0;

        for (int start = 0; start < primes.length; start++) {

            long sum = 0;

            for (int end = start; end < primes.length; end++) {

                sum += primes[end];

                if (sum > maxN) {
                    break;
                }

                int length = end - start + 1;

                if (length < currentLength) {
                    continue;
                }

                if (isPrime[(int) sum]) {

                    if (length > currentLength ||
                        (length == currentLength &&
                         (currentPrime == 0 ||
                          sum < currentPrime))) {

                        currentLength = length;
                        currentPrime = (int) sum;
                    }

                    /*
                     * Store this improvement starting at sum.
                     */
                    for (int x = (int) sum; x <= maxN; x++) {

                        if (bestLength[x] < currentLength ||
                            (bestLength[x] == currentLength &&
                             (bestPrime[x] == 0 ||
                              currentPrime < bestPrime[x]))) {

                            bestLength[x] = currentLength;
                            bestPrime[x] = currentPrime;

                        } else if (bestLength[x] > currentLength) {
                            break;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < t; i++) {

            int n = queries[i];

            System.out.println(
                bestPrime[n] + " " + bestLength[n]
            );
        }

        sc.close();
    }
}
