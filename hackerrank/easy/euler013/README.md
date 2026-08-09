# Project Euler #13: Large sum

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 13 from projecteuler.net

Work out the first ten digits of the sum of numbers.

 **Input Format** 

First line contains, next lines contain a 50 digit number each.

 **Constraints** 

-

 **Output Format** 

Print only first 10 digit of the final sum

 **Sample Input** 

```
5
37107287533902102798797998220837590246510135740250
46376937677490009712648124896970078050417018260538
74324986199524741059474233309513058123726617309629
91942213363574161572522430563301811072406154908250
23067588207539346171171980310421047513778063246676

```

 **Sample Output** 

```
2728190129

```

 **Explanation** 

Summing the numbers we get, first 10 digits are.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:42:48.440Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        BigInteger sum = BigInteger.ZERO;

        for (int i = 0; i < n; i++) {
            String number = in.next();
            sum = sum.add(new BigInteger(number));
        }

        String result = sum.toString();

        System.out.println(result.substring(0, 10));

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler013/problem)