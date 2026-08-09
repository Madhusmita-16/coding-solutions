# Project Euler #17: Number to Words

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 18 from projecteuler.net

By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is. The path is denoted by numbers in bold.

That is,.

Find the maximum total from top to bottom of the triangle given in input.

 **Input Format** 

First line contains, the number of testcases. For each testcase:
First line contains, the number of rows in the triangle.
For next lines, 'th line contains numbers.

 **Constraints** 

-
-
-

 **Output Format** 

For each testcase, print the required answer in a newline.

 **Sample Input** 

```
1
4
3
7 4
2 4 6
8 5 9 3

```

 **Sample Output** 

```
23

```

 **Explanation** 

As shown in statement.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T09:50:25.412Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String[] ones = {
        "", "One", "Two", "Three", "Four", "Five",
        "Six", "Seven", "Eight", "Nine", "Ten",
        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
        "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };

    static String[] tens = {
        "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    static String convert(long n) {

        if (n == 0) {
            return "Zero";
        }

        if (n < 20) {
            return ones[(int)n];
        }

        if (n < 100) {
            return tens[(int)(n / 10)] +
                   (n % 10 == 0 ? "" : " " + ones[(int)(n % 10)]);
        }

        if (n < 1000) {
            return ones[(int)(n / 100)] + " Hundred" +
                   (n % 100 == 0 ? "" : " " + convert(n % 100));
        }

        if (n < 1000000) {
            return convert(n / 1000) + " Thousand" +
                   (n % 1000 == 0 ? "" : " " + convert(n % 1000));
        }

        if (n < 1000000000) {
            return convert(n / 1000000) + " Million" +
                   (n % 1000000 == 0 ? "" : " " + convert(n % 1000000));
        }

        if (n < 1000000000000L) {
            return convert(n / 1000000000) + " Billion" +
                   (n % 1000000000 == 0 ? "" : " " + convert(n % 1000000000));
        }

        return convert(n / 1000000000000L) + " Trillion" +
               (n % 1000000000000L == 0 ? "" : " " + convert(n % 1000000000000L));
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {

            long n = in.nextLong();

            System.out.println(convert(n));
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler018/problem)