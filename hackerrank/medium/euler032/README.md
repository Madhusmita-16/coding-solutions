# euler032

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 32 from projecteuler.net

We shall say that an -digit number is pandigital if it makes use of all the digits to exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through pandigital.

 **HINT:**  Some products can be obtained in more than one way so be sure to only include it once in your sum.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
4

```

 **Sample Output** 

```
12

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T07:16:19.150Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler032/problem)