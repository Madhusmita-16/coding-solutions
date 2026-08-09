import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long[] chainLength;

    static long getChainLength(long n) {

        if (n == 1) {
            return 1;
        }

        // Use memoization only when n fits in our array
        if (n < chainLength.length && chainLength[(int)n] != 0) {
            return chainLength[(int)n];
        }

        long next;

        if (n % 2 == 0) {
            next = n / 2;
        } else {
            next = 3 * n + 1;
        }

        long length = 1 + getChainLength(next);

        if (n < chainLength.length) {
            chainLength[(int)n] = length;
        }

        return length;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        int[] queries = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();

            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        chainLength = new long[maxN + 1];

        chainLength[1] = 1;

        // best[i] = starting number <= i with longest chain
        int[] best = new int[maxN + 1];

        if (maxN >= 1) {
            best[1] = 1;
        }

        long longest = 1;

        for (int i = 2; i <= maxN; i++) {

            long length = getChainLength(i);

            /*
             * Use >= because when chain lengths are equal,
             * we need the larger starting number.
             */
            if (length >= longest) {
                longest = length;
                best[i] = i;
            } else {
                best[i] = best[i - 1];
            }
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            output.append(best[queries[i]]).append('\n');
        }

        System.out.print(output);

        in.close();
    }
}
