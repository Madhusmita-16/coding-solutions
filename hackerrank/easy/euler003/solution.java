import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextLong();
            long largest = 1;

            // Remove factor 2
            while (n % 2 == 0) {
                largest = 2;
                n /= 2;
            }

            // Check odd factors
            for (long i = 3; i * i <= n; i += 2) {
                while (n % i == 0) {
                    largest = i;
                    n /= i;
                }
            }

            // If n is still greater than 2, it is prime
            if (n > 2) {
                largest = n;
            }

            System.out.println(largest);
        }

        in.close();
    }
}
