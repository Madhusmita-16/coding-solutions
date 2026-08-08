# Project Euler #7: 10001st prime

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 7 from projecteuler.net

By listing the first six prime numbers: and, we can see that the prime is.
What is the prime number?

 **Input Format** 

First line contains that denotes the number of test cases. This is followed by lines, each containing an integer,.

 **Constraints** 

-
-

 **Output Format** 

Print the required answer for each test case.

 **Sample Input 0** 

```
2
3
6

```

 **Sample Output 0** 

```
5
13

```

 **Explanation 0** 

The first prime numbers are

we can see that prime number is and prime number is

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:26:03.859Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static boolean isPrime(int num) {

        if (num < 2) {
            return false;
        }

        if (num == 2) {
            return true;
        }

        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        int[] queries = new int[t];
        int maxN = 0;

        for (int i = 0; i < t; i++) {
            queries[i] = in.nextInt();

            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        /*
         * Store enough primes for the largest query.
         */
        int[] primes = new int[maxN];

        int count = 0;
        int number = 2;

        while (count < maxN) {

            if (isPrime(number)) {
                primes[count] = number;
                count++;
            }

            number++;
        }

        for (int i = 0; i < t; i++) {
            System.out.println(primes[queries[i] - 1]);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler007/problem)