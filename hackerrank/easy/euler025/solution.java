import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        int[] queries = new int[t];
        int maxDigits = 0;

        // Read all queries first
        for (int i = 0; i < t; i++) {
            queries[i] = sc.nextInt();
            maxDigits = Math.max(maxDigits, queries[i]);
        }

        // Fibonacci numbers
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ONE;

        // Answer for each digit length
        int[] answer = new int[maxDigits + 1];

        int index = 2;
        int currentDigits = 1;

        while (currentDigits < maxDigits) {

            BigInteger next = a.add(b);
            a = b;
            b = next;
            index++;

            int digits = b.toString().length();

            if (digits > currentDigits) {
                for (int d = currentDigits + 1; d <= digits && d <= maxDigits; d++) {
                    answer[d] = index;
                }
                currentDigits = digits;
            }
        }

        // Print answers
        for (int n : queries) {
            System.out.println(answer[n]);
        }

        sc.close();
    }
}
