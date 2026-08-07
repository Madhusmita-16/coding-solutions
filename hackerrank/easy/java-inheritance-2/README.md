# Java Inheritance II

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Write the following code in your editor below:

1. A class named *Arithmetic* with a method named *add* that takes $2$ integers as parameters and returns an integer denoting their sum.
1. A class named *Adder* that inherits from a superclass named *Arithmetic*. 

Your classes should not be be $\text{public}$.

**Input Format**

You are not responsible for reading any input from stdin; a locked code stub will test your submission by calling the *add* method on an *Adder* object and passing it $2$ integer parameters.

**Constraints**

 

**Output Format**

You are not responsible for printing anything to stdout. Your *add* method must return the sum of its parameters.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T20:06:39.395Z  

```java


class Arithmetic {
    int add(int a, int b) {
        return a + b;
    }
}

class Adder extends Arithmetic {
}


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-inheritance-2/problem)