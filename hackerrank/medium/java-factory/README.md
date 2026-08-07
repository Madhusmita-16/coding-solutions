# Java Factory Pattern

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

According to Wikipedia, a factory is simply an object that returns another object from some other method call, which is assumed to be "new".

In this problem, you are given an interface *Food*. There are two classes *Pizza* and *Cake* which implement the *Food* interface, and they both contain a method *getType*().

The main function in the *Main* class creates an instance of the *FoodFactory* class. The *FoodFactory* class contains a method *getFood(String)* that returns a new instance of *Pizza* or *Cake* according to its parameter.

You are given the partially completed code in the editor. Please complete the *FoodFactory* class.

**Sample Input 1**

	cake

**Sample Output 1**

	The factory returned class Cake
	Someone ordered a Dessert!

**Sample Input 2**

	pizza

**Sample Output 2**

	The factory returned class Pizza
	Someone ordered Fast Food!

**Input Format**

 

**Constraints**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T19:42:44.855Z  

```java


if (order.equalsIgnoreCase("pizza")) {
    return new Pizza();
} else if (order.equalsIgnoreCase("cake")) {
    return new Cake();
}

return null;


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-factory/problem)