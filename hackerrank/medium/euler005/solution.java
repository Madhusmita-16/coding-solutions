import java.io.*;
import java.util.*;

public class Solution {

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        for (int a0 = 0; a0 < t; a0++) {

            int n = in.nextInt();

            long answer = 1;

            for (int i = 2; i <= n; i++) {
                answer = lcm(answer, i);
            }

            System.out.println(answer);
        }

        in.close();
    }
}
