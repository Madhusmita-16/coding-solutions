import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
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

        /*
         * Store enough primes for the largest query.
         */
        int[] primes = new int[maxN];

        int count = 0;
        int number = 2;

        while (count < maxN) {

            if (isPrime(number)) {
                primes[count] = number;
                count++;
            }

            number++;
        }

        for (int i = 0; i < t; i++) {
            System.out.println(primes[queries[i] - 1]);
        }

        in.close();
    }
}
