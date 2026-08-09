import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();
        int[] queries = new int[t];

        int maxN = 0;

        // Read all test cases and find maximum n
        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[maxN + 1];

        Arrays.fill(isPrime, true);

        if (maxN >= 0) {
            isPrime[0] = false;
        }

        if (maxN >= 1) {
            isPrime[1] = false;
        }

        for (int i = 2; (long) i * i <= maxN; i++) {

            if (isPrime[i]) {

                for (int j = i * i; j <= maxN; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // Prefix sum of primes
        long[] prefix = new long[maxN + 1];

        for (int i = 1; i <= maxN; i++) {
            prefix[i] = prefix[i - 1];

            if (isPrime[i]) {
                prefix[i] += i;
            }
        }

        // Answer each query
        StringBuilder output = new StringBuilder();

        for (int n : queries) {
            output.append(prefix[n]).append('\n');
        }

        System.out.print(output);

        in.close();
    }
}
