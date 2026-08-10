# Project Euler #40: Champernowne's constant

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 40 from projecteuler.net

An irrational decimal fraction is created by concatenating the positive integers:

It can be seen that the digit of the fractional part is 1.

If represents the digit of the fractional part, find the value of the following expression.

 **Input Format** 

First line contains that denotes the number of test cases. This is followed by lines, each containing an integers.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
1
1 2 3 4 5 6 7

```

 **Sample Output** 

```
5040

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:38:45.772Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static int getDigit(long position) {

        long digits = 1;
        long count = 9;
        long start = 1;

        while (position > digits * count) {
            position -= digits * count;
            digits++;
            count *= 10;
            start *= 10;
        }

        long number = start + (position - 1) / digits;

        int index = (int)((position - 1) % digits);

        String s = String.valueOf(number);

        return s.charAt(index) - '0';
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            long product = 1;

            /*
             * Each test case contains 7 positions.
             */
            for (int i = 0; i < 7; i++) {

                long position = sc.nextLong();

                product *= getDigit(position);
            }

            System.out.println(product);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler040/problem)