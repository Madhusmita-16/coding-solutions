# Project Euler #46: Goldbach's other conjecture

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 47 from projecteuler.net

The first two consecutive numbers to have two distinct prime factors are:

The first three consecutive numbers to have three distinct prime factors are:

Given find all the consecutive integers, where first integer is to have exactly distinct prime factors. Print the first of these numbers in ascending order.

 **Input Format** 
Input contains two integers and.

 **Output Format** 
Print the answer corresponding to the test case. Print each integer in a new line.

 **Constraints** 

 **Sample Input#00** 

```
20 2

```

 **Sample Output#00** 

```
14
20

```

 **Sample Input#01** 

```
644 3

```

 **Sample Output** 

```
644

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:19:03.179Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static boolean isPrime(long n) {

        if (n < 2) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            long n = sc.nextLong();

            int count = 0;

            /*
             * N = prime + 2 * square
             *
             * Therefore:
             *
             * prime = N - 2 * i * i
             *
             * Try every possible square.
             */
            for (long i = 1; 2 * i * i < n; i++) {

                long prime = n - 2 * i * i;

                if (isPrime(prime)) {
                    count++;
                }
            }

            System.out.println(count);
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler047/problem)