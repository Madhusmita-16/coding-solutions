# Project Euler #45: Triangular, pentagonal, and hexagonal

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
**Submitted:** 2026-08-10T10:16:17.330Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long triangular(long n) {
        return n * (n + 1) / 2;
    }

    static long pentagonal(long n) {
        return n * (3 * n - 1) / 2;
    }

    static long hexagonal(long n) {
        return n * (2 * n - 1);
    }

    static boolean isTriangular(long x) {
        if (x <= 0) {
            return false;
        }

        long d = 8 * x + 1;
        long r = (long) Math.sqrt(d);

        while ((r + 1) * (r + 1) <= d) {
            r++;
        }

        while (r * r > d) {
            r--;
        }

        return r * r == d;
    }

    static boolean isPentagonal(long x) {
        if (x <= 0) {
            return false;
        }

        long d = 24 * x + 1;
        long r = (long) Math.sqrt(d);

        while ((r + 1) * (r + 1) <= d) {
            r++;
        }

        while (r * r > d) {
            r--;
        }

        return r * r == d && (1 + r) % 6 == 0;
    }

    static boolean isHexagonal(long x) {
        if (x <= 0) {
            return false;
        }

        long d = 8 * x + 1;
        long r = (long) Math.sqrt(d);

        while ((r + 1) * (r + 1) <= d) {
            r++;
        }

        while (r * r > d) {
            r--;
        }

        return r * r == d && (1 + r) % 4 == 0;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long N = sc.nextLong();
        int a = sc.nextInt();
        int b = sc.nextInt();

        StringBuilder ans = new StringBuilder();

        /*
         * 3 = Triangular
         * 5 = Pentagonal
         * 6 = Hexagonal
         */

        if ((a == 3 && b == 5) || (a == 5 && b == 3)) {

            // Generate pentagonal numbers and check triangular.
            for (long i = 1; ; i++) {

                long p = pentagonal(i);

                if (p >= N) {
                    break;
                }

                if (isTriangular(p)) {
                    ans.append(p).append('\n');
                }
            }

        } else if ((a == 5 && b == 6) || (a == 6 && b == 5)) {

            // Generate hexagonal numbers and check pentagonal.
            for (long i = 1; ; i++) {

                long h = hexagonal(i);

                if (h >= N) {
                    break;
                }

                if (isPentagonal(h)) {
                    ans.append(h).append('\n');
                }
            }

        } else if ((a == 3 && b == 6) || (a == 6 && b == 3)) {

            /*
             * Every hexagonal number is triangular.
             * Therefore every hexagonal number below N
             * satisfies the condition.
             */
            for (long i = 1; ; i++) {

                long h = hexagonal(i);

                if (h >= N) {
                    break;
                }

                ans.append(h).append('\n');
            }
        }

        System.out.print(ans);

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler046/problem)