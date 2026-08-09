# Project Euler #18: Maximum path sum I

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
**Submitted:** 2026-08-09T09:52:27.239Z  

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

            long[][] triangle = new long[n][n];

            // Read triangle
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    triangle[i][j] = in.nextLong();
                }
            }

            // Bottom-up DP
            for (int i = n - 2; i >= 0; i--) {

                for (int j = 0; j <= i; j++) {

                    triangle[i][j] += Math.max(
                        triangle[i + 1][j],
                        triangle[i + 1][j + 1]
                    );
                }
            }

            System.out.println(triangle[0][0]);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler018/problem)