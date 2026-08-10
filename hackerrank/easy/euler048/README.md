# Project Euler #48: Self powers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 48 from projecteuler.net

The series,

Find the last ten digits of the series,

 **Note**  You do not need to print leading zeros. See sample.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
10

```

 **Sample Output** 

```
405071317

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:29:10.223Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler048/problem)