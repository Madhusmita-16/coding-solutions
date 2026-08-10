# Project Euler #34: Digit factorials

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 34 from projecteuler.net

is a curious number, as which is divisible by.

Find the sum of all numbers below which divide the sum of the factorial of their digits.

Note: as are not sums they are not included.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
20

```

 **Sample Output** 

```
19

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T09:17:41.172Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static long[] factorial = new long[10];

    static void precompute() {
        factorial[0] = 1;

        for (int i = 1; i <= 9; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
    }

    static long digitFactorialSum(long num) {
        long sum = 0;

        while (num > 0) {
            int digit = (int)(num % 10);
            sum += factorial[digit];
            num /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();

        precompute();

        long answer = 0;

        for (long i = 10; i < n; i++) {

            long sum = digitFactorialSum(i);

            if (sum % i == 0) {
                answer += i;
            }
        }

        System.out.println(answer);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler035/problem)