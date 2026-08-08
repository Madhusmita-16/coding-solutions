import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPalindrome(int num) {

        int original = num;
        int reverse = 0;

        while (num > 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }

        return original == reverse;
    }

    static int largestPalindrome(int n) {

        int max = 0;

        for (int i = 999; i >= 100; i--) {

            for (int j = i; j >= 100; j--) {

                int product = i * j;

                if (product >= n) {
                    continue;
                }

                if (product <= max) {
                    break;
                }

                if (isPalindrome(product)) {
                    max = product;
                }
            }
        }

        return max;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(
                                System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(
                bufferedReader.readLine().trim());

        for (int i = 0; i < t; i++) {

            int n = Integer.parseInt(
                    bufferedReader.readLine().trim());

            int result = largestPalindrome(n);

            bufferedWriter.write(
                    String.valueOf(result));

            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
