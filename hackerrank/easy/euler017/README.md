# Project Euler #16: Power digit sum

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 17 from projecteuler.net

The numbers to written out in words are

First character of each word will be capital letter for example:
is

Given a number, you have to write it in words.

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
10
17

```

 **Sample Output** 

```
Ten
Seventeen

```

 **Explanation** 

Follow the rules given in statement.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:48:46.463Z  

```java
import java.io.*;
import java.util.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            int n = in.nextInt();

            BigInteger number = BigInteger.valueOf(2).pow(n);

            String value = number.toString();

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler017/problem)