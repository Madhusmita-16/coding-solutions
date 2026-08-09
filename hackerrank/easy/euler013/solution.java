import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Query {
        int index;
        long value;

        Query(int index, long value) {
            this.index = index;
            this.value = value;
        }
    }

    static long divisorCount(long n) {

        if (n == 1) {
            return 1;
        }

        long count = 1;

        int power = 0;

        while (n % 2 == 0) {
            n /= 2;
            power++;
        }

        if (power > 0) {
            count *= (power + 1);
        }

        for (long p = 3; p * p <= n; p += 2) {

            if (n % p == 0) {

                power = 0;

                while (n % p == 0) {
                    n /= p;
                    power++;
                }

                count *= (power + 1);
            }
        }

        if (n > 1) {
            count *= 2;
        }

        return count;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        Query[] queries = new Query[t];

        for (int i = 0; i < t; i++) {
            queries[i] = new Query(i, in.nextLong());
        }

        // Compatible with older Java versions
        Arrays.sort(queries, new Comparator<Query>() {
            public int compare(Query a, Query b) {

                if (a.value < b.value) {
                    return -1;
                }

                if (a.value > b.value) {
                    return 1;
                }

                return 0;
            }
        });

        long[] answer = new long[t];

        long triangle = 0;
        long n = 0;
        long divisors = 0;

        for (int q = 0; q < t; q++) {

            long required = queries[q].value;

            /*
             * Keep generating triangular numbers until
             * the number of divisors is greater than required.
             */
            while (divisors <= required) {

                n++;
                triangle += n;

                divisors = divisorCount(triangle);
            }

            answer[queries[q].index] = triangle;
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            output.append(answer[i]).append('\n');
        }

        System.out.print(output);

        in.close();
    }
}
