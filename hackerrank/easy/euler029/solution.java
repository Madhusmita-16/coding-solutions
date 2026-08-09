import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int maxExponent = 16;

        int[] minExponent = new int[(n + 1) * maxExponent + 1];

        for (int i = 1; i <= maxExponent; i++) {
            for (int j = 1; j <= n; j++) {
                int index = i * j;

                if (minExponent[index] == 0) {
                    minExponent[index] = i;
                }
            }
        }

        int[] base = new int[n + 1];

        long repeated = 0;

        for (int x = 2; x <= n; x++) {

            int parent = base[x];

            if (parent == 0) {

                long power = (long) x * x;

                while (power <= n) {

                    base[(int) power] = x;

                    if (power > n / x) {
                        break;
                    }

                    power *= x;
                }

                continue;
            }

            int exponent = 0;
            int reduce = x;

            while (reduce > 1) {
                reduce /= parent;
                exponent++;
            }

            for (int y = 2; y <= n; y++) {

                int index = y * exponent;

                if (minExponent[index] < exponent) {
                    repeated++;
                }
            }
        }

        long total = (long) (n - 1) * (n - 1);

        long answer = total - repeated;

        System.out.println(answer);

        sc.close();
    }
}
