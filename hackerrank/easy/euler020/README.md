# Project Euler #20: Factorial digit sum

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 20 from projecteuler.net

For example,,
and the sum of the digits in the number is.

Find the sum of the digits in the number

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integer.

 **Constraints** 

-
-

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
2
3
6

```

 **Sample Output** 

```
6
9

```

 **Explanation** 

- is, sum of digit is.
- is, sum of digits is.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:59:31.291Z  

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

            int n = in.nextInt();

            BigInteger factorial = BigInteger.ONE;

            for (int i = 2; i <= n; i++) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
            }

            String value = factorial.toString();

            int sum = 0;

            for (int i = 0; i < value.length(); i++) {
                sum += value.charAt(i) - '0';
            }

            System.out.println(sum);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler020/problem)