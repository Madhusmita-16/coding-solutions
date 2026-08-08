# Java Method Overriding 2 (Super Keyword)

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

When a method in a subclass overrides a method in superclass, it is still possible to call the overridden method using **super** keyword. If you write *super.func()* to call the function *func()*, it will call the method that was defined in the superclass.

You are given a partially completed code in the editor. Modify the code so that the code prints the following text:

    Hello I am a motorcycle, I am a cycle with an engine.
    My ancestor is a cycle who is a vehicle with pedals.


**Input Format**

 

**Constraints**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:07:54.942Z  

```java
import java.io.*;
import java.util.*;

class Vehicle {
    String type;

    void setType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }
}

class Cycle extends Vehicle {
    Cycle() {
        setType("cycle");
    }

    void print() {
        System.out.println("My ancestor is a cycle who is a vehicle with pedals.");
    }
}

class MotorCycle extends Cycle {
    MotorCycle() {
        setType("motorcycle");
    }

    void print() {
        System.out.println("Hello I am a " + getType() + ", I am a cycle with an engine.");
        super.print();
    }
}

public class Solution {

    public static void main(String[] args) {
        MotorCycle m = new MotorCycle();
        m.print();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-method-overriding-2-super-keyword/problem)