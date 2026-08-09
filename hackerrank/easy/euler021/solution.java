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

        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        // sumDivisors[x] = sum of proper divisors of x
        long[] sumDivisors = new long[maxN];

        /*
         * Add divisor i to all multiples of i.
         * Start from 2*i because i itself is not a proper
         * divisor of itself.
         */
        for (int i = 1; i < maxN; i++) {

            for (int j = i * 2; j < maxN; j += i) {
                sumDivisors[j] += i;
            }
        }

        /*
         * prefix[i] = sum of amicable numbers < i
         */
        long[] prefix = new long[maxN];

        for (int i = 1; i < maxN; i++) {

            prefix[i] = prefix[i - 1];

            long partner = sumDivisors[i];

            /*
             * i and partner are amicable if:
             *
             * sumDivisors[i] = partner
             * sumDivisors[partner] = i
             *
             * and they must be different.
             */
            if (partner != i &&
                partner > 0 &&
                partner < maxN &&
                sumDivisors[(int)partner] == i) {

                prefix[i] += i;
            }
        }

        for (int n : queries) {
            System.out.println(prefix[n - 1]);
        }

        in.close();
    }
}
