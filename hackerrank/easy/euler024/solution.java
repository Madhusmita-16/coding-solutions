import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        String letters = "abcdefghijklm";

        // Pre-calculate factorials
        long[] fact = new long[14];
        fact[0] = 1;

        for (int i = 1; i <= 13; i++) {
            fact[i] = fact[i - 1] * i;
        }

        while (t-- > 0) {

            long n = sc.nextLong();

            // Convert to 0-based index
            n--;

            ArrayList<Character> list = new ArrayList<>();

            for (char c : letters.toCharArray()) {
                list.add(c);
            }

            StringBuilder answer = new StringBuilder();

            // Select the first 12 characters.
            // The last character is automatically determined.
            for (int remaining = 12; remaining >= 0; remaining--) {

                long blockSize = fact[remaining];

                int index = (int)(n / blockSize);

                answer.append(list.get(index));
                list.remove(index);

                n = n % blockSize;
            }

            System.out.println(answer);
        }

        sc.close();
    }
}
