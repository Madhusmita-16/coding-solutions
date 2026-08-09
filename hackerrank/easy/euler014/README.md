# Project Euler #13: Large sum

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 14 from projecteuler.net

The following iterative sequence is defined for the set of positive integers:

Using the rule above and starting with 13, we generate the following sequence:

It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

Which starting number, produces the longest chain? If many possible such numbers are there print the maximum one.

 **Note:**  Once the chain starts the terms are allowed to go above.

 **Input Format** 

The first line contains an integer, i.e., number of test cases.
Next lines will contain an integers.

 **Constraints** 

-
-

 **Output Format** 

Print the values corresponding to each test case.

 **Sample Input** 

```
3
10 
15
20

```

 **Sample Output** 

```
9
9
19

```

 **Explanation** 

Collatz sequence for is,

containing steps and is the longest for

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:42:50.652Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/euler014/problem)