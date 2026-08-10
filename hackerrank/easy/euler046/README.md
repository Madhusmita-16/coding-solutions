# Project Euler #46: Goldbach's other conjecture

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 46 from projecteuler.net

It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a prime and twice a square.

It turns out that the conjecture was false as you'll discover some values can't be represented as a sum of prime and twice a square.
You are given, print the number of ways N can be represented as a sum of prime and twice a square.
Example can be represented in two ways as and

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
9
15

```

 **Sample Output** 

```
1
2

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:18:59.180Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler046/problem)