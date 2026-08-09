import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        // Maximum limit from the problem
        int limit = 28123;

        // Find sum of proper divisors for every number
        int[] divisorSum = new int[limit + 1];

        for (int i = 1; i <= limit / 2; i++) {
            for (int j = i * 2; j <= limit; j += i) {
                divisorSum[j] += i;
            }
        }

        // Store all abundant numbers
        ArrayList<Integer> abundant = new ArrayList<>();

        for (int i = 1; i <= limit; i++) {
            if (divisorSum[i] > i) {
                abundant.add(i);
            }
        }

        // Check each test case
        while (t-- > 0) {

            int n = sc.nextInt();

            if (n > 28123) {
                System.out.println("YES");
                continue;
            }

            boolean possible = false;

            for (int i = 0; i < abundant.size(); i++) {

                int a = abundant.get(i);

                if (a >= n) {
                    break;
                }

                int b = n - a;

                if (b > 0 && divisorSum[b] > b) {
                    possible = true;
                    break;
                }
            }

            if (possible) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
