# Project Euler #42: Coded triangle numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 42 from projecteuler.net

The term of a sequence of triangle numbers is given by,

so the first ten triangle numbers are:

You are given an integer. If it is a triangular number, print the term corresponding to this number, else print

 **Input Format** 

First line of input contains an integer denoting the number of testcases. Each of the next lines contains an integer.

 **Constraints** 

 **Output Format** 

Print the answer corresponding to each test case in a new line.

 **Sample Input** 

```
3
2
3
55

```

 **Sample Output** 

```
-1
2
10

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-10T10:09:19.060Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while (t-- > 0) {

            long n = sc.nextLong();

            /*
             * Triangle number:
             *
             * T_k = k * (k + 1) / 2
             *
             * Therefore:
             *
             * k^2 + k - 2n = 0
             *
             * k = (-1 + sqrt(1 + 8n)) / 2
             */

            long d = 1 + 8 * n;
            long root = (long) Math.sqrt(d);

            if (root * root == d && (root - 1) % 2 == 0) {
                System.out.println((root - 1) / 2);
            } else {
                System.out.println(-1);
            }
        }

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler042/problem)