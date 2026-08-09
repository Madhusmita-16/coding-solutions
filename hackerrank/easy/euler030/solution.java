import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] power = new long[10];

        for (int i = 0; i <= 9; i++) {
            power[i] = 1;

            for (int j = 0; j < n; j++) {
                power[i] *= i;
            }
        }

        long answer = 0;

        for (int number = 2; number <= 1000000; number++) {

            int temp = number;
            long sum = 0;

            while (temp > 0) {
                int digit = temp % 10;
                sum += power[digit];
                temp /= 10;

                if (sum > number) {
                    break;
                }
            }

            if (sum == number) {
                answer += number;
            }
        }

        System.out.println(answer);

        sc.close();
    }
}
