# Project Euler #9: Special Pythagorean triplet

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 9 from projecteuler.net

A Pythagorean triplet is a set of three natural numbers,, for which,

For example,

Given, Check if there exists any Pythagorean triplet for which
Find maximum possible value of among all such Pythagorean triplets, If there is no such Pythagorean triplet print.

 **Input Format** 

The first line contains an integer i.e. number of test cases.
The next lines will contain an integer.

 **Constraints** 

-
-

 **Output Format** 

Print the value corresponding to each test case in separate lines.

 **Sample Input 0** 

```
2
12
4

```

 **Sample Output 0** 

```
60
-1

```

 **Explanation 0** 

- For, we have a triplet, whose product is.
- For, we don't have any pythagorean triple.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:32:59.700Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            long n = in.nextLong();
            long maxProduct = -1;

            // a < b < c
            // a can never be >= n/3
            for (long a = 1; a < n / 3; a++) {

                long numerator = n * (n - 2 * a);
                long denominator = 2 * (n - a);

                // b must be an integer
                if (numerator % denominator != 0) {
                    continue;
                }

                long b = numerator / denominator;
                long c = n - a - b;

                if (b > a && b < c) {
                    long product = a * b * c;
                    maxProduct = Math.max(maxProduct, product);
                }
            }

            System.out.println(maxProduct);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler009/problem)