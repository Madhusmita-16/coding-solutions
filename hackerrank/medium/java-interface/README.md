# Java Interface

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

A Java interface can only contain method signatures and fields. The interface can be used to achieve polymorphism. In this problem, you will practice your knowledge on interfaces.

You are given an interface *AdvancedArithmetic* which contains a method signature *int divisor\_sum(int n)*. You need to write a class called MyCalculator which implements the interface.

*divisorSum* function just takes an integer as input and return the sum of all its divisors. For example divisors of 6 are 1, 2, 3 and 6, so *divisor\_sum* should return 12. The value of n will be at most 1000.

Read the partially completed code in the editor and complete it. You just need to write the MyCalculator class only. *Your class shouldn't be public.*

**Input Format**

 

**Constraints**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:14:46.001Z  

```java
import java.io.*;
import java.util.*;

interface AdvancedArithmetic{
    int divisorSum(int n);
}

class MyCalculator implements AdvancedArithmetic{
    public int divisorSum(int n){
        int sum = 0;
        for(int i = 1; i <= n; i++){
            if(n % i == 0){
                sum += i;
            }
        }
        return sum;
    }
}

public class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        MyCalculator my_calculator = new MyCalculator();
        System.out.print("I implemented: " + my_calculator.getClass().getInterfaces()[0].getName() + "\n" + my_calculator.divisorSum(n));
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-interface/problem)