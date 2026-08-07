# Can You Access?

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a class *Solution* and an inner class *Inner.Private*. The main method of class *Solution* takes an integer $num$ as input. The *powerof2* in class *Inner.Private* checks whether a number is a power of $2$. You have to call the method *powerof2* of the class *Inner.Private* from the *main* method of the class *Solution*.

**Constraints**   
$ 1 \le num \le 2^{30}$

**Input Format**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T20:16:19.451Z  

```java


		Inner inner = new Inner();
Inner.Private privateObject = inner.new Private();

o = privateObject;

System.out.println(num + " is " + privateObject.powerof2(num));


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/can-you-access/problem)