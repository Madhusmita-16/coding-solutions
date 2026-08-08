# Project Euler #6: Sum square difference

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 6 from projecteuler.net

The sum of the squares of the first ten natural numbers is,. The square of the sum of the first ten natural numbers is,. Hence the absolute difference between the sum of the squares of the first ten natural numbers and the square of the sum is.

Find the absolute difference between the sum of the squares of the first natural numbers and the square of the sum.

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
22
2640

```

 **Explanation 0** 

- For,
- For,

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:25:20.964Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int t = in.nextInt();

        for (int a0 = 0; a0 < t; a0++) {

            long n = in.nextLong();

            // Sum of first n natural numbers
            long sum = n * (n + 1) / 2;

            // Square of the sum
            long squareOfSum = sum * sum;

            // Sum of squares
            long sumOfSquares =
                    n * (n + 1) * (2 * n + 1) / 6;

            long result =
                    Math.abs(squareOfSum - sumOfSquares);

            System.out.println(result);
        }

        in.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler006/problem)