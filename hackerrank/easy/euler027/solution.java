import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int[] queries = new int[t];

        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
            maxN = Math.max(maxN, queries[i]);
        }

        // best[n] = denominator < n having longest recurring cycle
        int[] best = new int[maxN + 1];

        int[] remainderPosition = new int[maxN + 1];

        int bestD = 0;
        int bestCycle = 0;

        for (int d = 2; d < maxN; d++) {

            Arrays.fill(remainderPosition, -1);

            int remainder = 1;
            int position = 0;

            while (remainder != 0 && remainderPosition[remainder] == -1) {

                remainderPosition[remainder] = position;

                remainder = (remainder * 10) % d;
                position++;
            }

            int cycle = 0;

            if (remainder != 0) {
                cycle = position - remainderPosition[remainder];
            }

            if (cycle > bestCycle) {
                bestCycle = cycle;
                bestD = d;
            }

            // For every N from d+1 onward, d is currently the best.
            best[d + 1] = bestD;
        }

        // Fill unanswered values
        for (int i = 2; i <= maxN; i++) {
            if (best[i] == 0) {
                best[i] = best[i - 1];
            }
        }

        for (int n : queries) {
            System.out.println(best[n]);
        }

        sc.close();
    }
}
