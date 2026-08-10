import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static ArrayList<Integer> primes = new ArrayList<Integer>();

    static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static void generate(
            int number,
            int mask,
            int length,
            int maxDigit) {

        if (length == maxDigit) {

            if (isPrime(number)) {
                primes.add(number);
            }

            return;
        }

        for (int digit = 1; digit <= maxDigit; digit++) {

            int bit = 1 << digit;

            if ((mask & bit) != 0) {
                continue;
            }

            generate(
                    number * 10 + digit,
                    mask | bit,
                    length + 1,
                    maxDigit);
        }
    }

    static void prepare() {

        // 4 digit pandigital primes
        generate(0, 0, 0, 4);

        // 7 digit pandigital primes
        generate(0, 0, 0, 7);

        Collections.sort(primes);
    }

    static int findAnswer(int n) {

        int left = 0;
        int right = primes.size() - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int value = primes.get(mid);

            if (value < n) {
                answer = value;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];

        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
        }

        prepare();

        for (int i = 0; i < t; i++) {
            System.out.println(findAnswer(queries[i]));
        }

        sc.close();
    }
}
