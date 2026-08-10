# euler033

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 33 from projecteuler.net

The fraction is a curious fraction. An inexperienced mathematician while attempting to simplify it may incorrectly believe that is obtained by cancelling the s.

We shall consider fractions like,, to be trivial examples.

Which means fractions where trailing 0's are cancelled are trivial. So we will ignore all the cases where we have to cancel 0's.

You will be given 2 integers and. represents the number of digits in Numerator and Denominator, and represents the exact number of digits to be "cancelled" from Numerator and Denominator. Find every non-trivial fraction, (1) where numerator is less than denominator, (2) and the value of the reduced fraction is equal to the original fraction.

Sum all the Numerators and the Denominators of the original fractions, and print them separated by a space.

 **Input Format** 

Input contains two integers

 **Constraints** 

 **Output Format** 

Display 2 space separated integers that denote the sum of the Numerators and the sum of the Denominators respectively of original fractions.
 **Note**  You do not have to reduce the Numerator and Denominator.

 **Sample Input** 

```
2 1

```

 **Sample Output** 

```
110 322

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T07:16:22.333Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int N;
    static long sum = 0;
    static Set<Integer> products = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int[] digits = new int[N];

        for (int i = 0; i < N; i++) {
            digits[i] = i + 1;
        }

        generatePermutations(digits, 0);

        for (int product : products) {
            sum += product;
        }

        System.out.println(sum);
    }

    static void generatePermutations(int[] digits, int index) {

        if (index == N) {
            checkPandigital(digits);
            return;
        }

        for (int i = index; i < N; i++) {

            // Swap
            int temp = digits[index];
            digits[index] = digits[i];
            digits[i] = temp;

            generatePermutations(digits, index + 1);

            // Backtrack
            temp = digits[index];
            digits[index] = digits[i];
            digits[i] = temp;
        }
    }

    static void checkPandigital(int[] digits) {

        // Try every possible position for the first split
        for (int i = 1; i < N - 1; i++) {

            // Try every possible position for the second split
            for (int j = i + 1; j < N; j++) {

                int a = 0;
                int b = 0;
                int product = 0;

                // First number: digits[0 ... i-1]
                for (int k = 0; k < i; k++) {
                    a = a * 10 + digits[k];
                }

                // Second number: digits[i ... j-1]
                for (int k = i; k < j; k++) {
                    b = b * 10 + digits[k];
                }

                // Product: digits[j ... N-1]
                for (int k = j; k < N; k++) {
                    product = product * 10 + digits[k];
                }

                if ((long) a * b == product) {
                    products.add(product);
                }
            }
        }
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler033/problem)