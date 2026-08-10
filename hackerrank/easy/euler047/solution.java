import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int limit = n + k - 1;

        // count[i] = number of distinct prime factors of i
        int[] count = new int[limit + 1];

        // Sieve
        for (int p = 2; p <= limit; p++) {

            if (count[p] == 0) {

                for (int j = p; j <= limit; j += p) {
                    count[j]++;
                }
            }
        }

        int consecutive = 0;

        // Check up to n + k - 1
        for (int i = 2; i <= limit; i++) {

            if (count[i] == k) {
                consecutive++;
            } else {
                consecutive = 0;
            }

            // We have k consecutive valid numbers
            if (consecutive >= k) {

                int start = i - k + 1;

                // Starting number must be <= n
                if (start <= n) {
                    System.out.println(start);
                }
            }
        }

        sc.close();
    }
}
