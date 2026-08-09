import java.io.*;
import java.util.*;

public class Solution {

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int bestA = 0;
        int bestB = 0;
        int maxCount = 0;

        // b must be positive and prime
        for (int b = 2; b <= n; b++) {

            if (!isPrime(b)) {
                continue;
            }

            // a is between -n and n
            for (int a = -n; a <= n; a++) {

                int count = 0;

                while (true) {

                    long value = (long) count * count
                               + (long) a * count
                               + b;

                    if (value < 2 || !isPrime((int) value)) {
                        break;
                    }

                    count++;
                }

                if (count > maxCount) {
                    maxCount = count;
                    bestA = a;
                    bestB = b;
                }
            }
        }

        System.out.println(bestA + " " + bestB);

        sc.close();
    }
}
