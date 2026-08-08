# Project Euler #5: Smallest multiple

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

This problem is a programming version of Problem 5 from projecteuler.net

is the smallest number that can be divided by each of the numbers from to without any remainder.
What is the smallest positive number that is evenly divisible(divisible with no remainder) by all of the numbers from to ?

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
10

```

 **Sample Output 0** 

```
6
2520

```

 **Explanation 0** 

- You can check is divisible by each of, giving quotient of respectively.
- You can check is divisible by each of giving quotient of respectively.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:23:45.564Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        for (int a0 = 0; a0 < t; a0++) {

            int n = in.nextInt();

            long answer = 1;

            for (int i = 2; i <= n; i++) {
                answer = lcm(answer, i);
            }

            System.out.println(answer);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler005/problem)