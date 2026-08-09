# Project Euler #30: Digit Nth powers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

This problem is a programming version of Problem 30 from projecteuler.net

Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

As 1 = 1^4 is not a sum it is not included.
The sum of these numbers is.

Find the sum of all the numbers that can be written as the sum of powers of their digits.

 **Input Format** 

Input contains an integer

 **Constraints** 

 **Output Format** 

Print the answer corresponding to the test case.

 **Sample Input** 

```
4

```

 **Sample Output** 

```
19316

```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-09T10:31:19.243Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        long[] power = new long[10];

        for (int i = 0; i <= 9; i++) {
            power[i] = 1;

            for (int j = 0; j < n; j++) {
                power[i] *= i;
            }
        }

        long answer = 0;

        for (int number = 2; number <= 1000000; number++) {

            int temp = number;
            long sum = 0;

            while (temp > 0) {
                int digit = temp % 10;
                sum += power[digit];
                temp /= 10;

                if (sum > number) {
                    break;
                }
            }

            if (sum == number) {
                answer += number;
            }
        }

        System.out.println(answer);

        sc.close();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/euler030/problem)