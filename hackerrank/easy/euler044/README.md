# Project Euler #43: Sub-string divisibility

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 43 from projecteuler.net

The number,, is a to pandigital number because it is made up of each of the digits to in some order, but it also has a rather interesting sub-string divisibility property.

Let be the digit, be the digit, and so on. In this way, we note the following:

Find the sum of all to pandigital numbers with this property.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
3

```

 **Sample Output** 

```
22212

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:13:03.075Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long sum;
    static int n;

    static int[] divisors = {2, 3, 5, 7, 11, 13, 17};

    static void generate(int[] digits, boolean[] used, int pos) {

        if (pos == n + 1) {

            long number = 0;

            for (int i = 0; i <= n; i++) {
                number = number * 10 + digits[i];
            }

            sum += number;
            return;
        }

        for (int digit = 0; digit <= n; digit++) {

            if (used[digit]) {
                continue;
            }

            digits[pos] = digit;

            /*
             * When pos = 3:
             * digits[1..3] must be divisible by 2
             *
             * When pos = 4:
             * digits[2..4] must be divisible by 3
             *
             * and so on.
             */
            if (pos >= 3) {

                int value =
                        digits[pos - 2] * 100
                        + digits[pos - 1] * 10
                        + digits[pos];

                int divisor = divisors[pos - 3];

                if (value % divisor != 0) {
                    continue;
                }
            }

            used[digit] = true;

            generate(digits, used, pos + 1);

            used[digit] = false;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        sum = 0;

        int[] digits = new int[n + 1];
        boolean[] used = new boolean[n + 1];

        generate(digits, used, 0);

        System.out.println(sum);

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler044/problem)