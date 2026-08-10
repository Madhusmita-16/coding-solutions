# Project Euler #48: Self powers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 49 from projecteuler.net

The arithmetic sequence, in which each of the terms increases by is unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations of one another.

There are no arithmetic sequences made up of three,, or primes, exhibiting this property.
You are given and, find all size sequences where first element is less than and elements are permutations of each other, are prime and are in AP(Arithmetic Progression).

Print the answer as concatenated integer formed by joining terms.

 **Input Format** 

Input contains two integers and

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case. each in new line in numerically sorted order of smallest value.

 **Sample Input** 

```
2000 3

```

 **Sample Output** 

```
148748178147

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:29:25.869Z  

```java
import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    static final BigInteger MOD = BigInteger.TEN.pow(10);

    static BigInteger power(long base, long exponent) {

        BigInteger b = BigInteger.valueOf(base);
        BigInteger result = BigInteger.ONE;

        while (exponent > 0) {

            if (exponent % 2 == 1) {
                result = result.multiply(b).mod(MOD);
            }

            b = b.multiply(b).mod(MOD);
            exponent /= 2;
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        BigInteger sum = BigInteger.ZERO;

        for (int i = 1; i <= n; i++) {
            sum = sum.add(power(i, i)).mod(MOD);
        }

        System.out.println(sum);

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler049/problem)