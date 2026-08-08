# Project Euler #1: Multiples of 3 and 5

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 1 from projecteuler.net

If we list all the natural numbers below that are multiples of or, we get and. The sum of these multiples is.

Find the sum of all the multiples of or below.

 **Input Format** 

First line contains that denotes the number of test cases. This is followed by lines, each containing an integer,.

 **Constraints** 

-
-

 **Output Format** 

For each test case, print an integer that denotes the sum of all the multiples of or below.

 **Sample Input 0** 

```
2
10
100

```

 **Sample Output 0** 

```
23
2318

```

 **Explanation 0** 

For, if we list all the natural numbers below that are multiples of or, we get and. The sum of these multiples is.

Similarly for, we get.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:20:10.657Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static long sumOfMultiples(long n, long k) {
        long m = (n - 1) / k;
        return k * m * (m + 1) / 2;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        while (t-- > 0) {
            long n = in.nextLong();

            long ans = sumOfMultiples(n, 3)
                     + sumOfMultiples(n, 5)
                     - sumOfMultiples(n, 15);

            System.out.println(ans);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler001/problem)