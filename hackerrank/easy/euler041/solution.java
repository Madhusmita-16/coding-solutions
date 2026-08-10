import java.io.*;
import java.util.*;

public class Solution {

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

    static boolean isPandigital(int n) {
        String s = String.valueOf(n);
        int len = s.length();

        int mask = 0;

        for (int i = 0; i < len; i++) {
            int d = s.charAt(i) - '0';

            if (d == 0 || d > len) {
                return false;
            }

            int bit = 1 << d;

            if ((mask & bit) != 0) {
                return false;
            }

            mask |= bit;
        }

        int required = (1 << (len + 1)) - 2;

        return mask == required;
    }

    static int[] generatePandigitalNumbers() {

        ArrayList<Integer> list = new ArrayList<>();

        /*
         * Only 1 to 7 digit pandigital numbers are useful.
         * 8 and 9 digit pandigital numbers are divisible by 3.
         */
        for (int len = 1; len <= 7; len++) {

            generate(
                0,
                len,
                0,
                list
            );
        }

        Collections.sort(list);

        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    static void generate(
            int value,
            int length,
            int mask,
            ArrayList<Integer> list) {

        if (Integer.toString(value).length() == length) {

            if (isPrime(value)) {
                list.add(value);
            }

            return;
        }

        for (int digit = 1; digit <= length; digit++) {

            int bit = 1 << digit;

            if ((mask & bit) != 0) {
                continue;
            }

            int next = value * 10 + digit;

            generate(
                next,
                length,
                mask | bit,
                list
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
        }

        int[] primes = generatePandigitalNumbers();

        for (int n : queries) {

            int answer = -1;

            for (int p : primes) {

                if (p >= n) {
                    break;
                }

                answer = p;
            }

            System.out.println(answer);
        }

        sc.close();
    }
}
