# Project Euler #3: Largest prime factor

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 3 from projecteuler.net

The prime factors of are and.

What is the largest prime factor of a given number ?

 **Input Format** 

First line contains, the number of test cases. This is followed by lines each containing an integer.

 **Constraints** 

-
-

 **Output Format** 

For each test case, display the largest prime factor of.

 **Sample Input 0** 

```
2
10
17

```

 **Sample Output 0** 

```
5
17

```

 **Explanation 0** 

- Prime factors of are, largest is.
- Prime factor of is itself, hence largest is.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:21:52.728Z  

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
            long n = in.nextLong();
            long largest = 1;

            // Remove factor 2
            while (n % 2 == 0) {
                largest = 2;
                n /= 2;
            }

            // Check odd factors
            for (long i = 3; i * i <= n; i += 2) {
                while (n % i == 0) {
                    largest = i;
                    n /= i;
                }
            }

            // If n is still greater than 2, it is prime
            if (n > 2) {
                largest = n;
            }

            System.out.println(largest);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler003/problem)