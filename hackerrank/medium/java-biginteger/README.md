# Java Singleton Pattern

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

In this problem, you have to add and multiply huge numbers! These numbers are so big that you can't contain them in any ordinary data types like a long integer.

Use the power of Java's BigInteger class and solve this problem.

**Input Format**

There will be two lines containing two numbers, $a$ and $b$. 

**Constraints**

$a$ and $b$ are non-negative integers and can have maximum $200$ digits.

**Output Format**

Output two lines. The first line should contain $a+b$, and the second line should contain $a \times b$. Don't print any leading zeros.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T19:44:21.172Z  

```java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;


class Singleton {

    private static Singleton instance = new Singleton();

    public String str;

    private Singleton() {
    }

    public static Singleton getSingleInstance() {
        return instance;
    }
}


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-biginteger/problem)