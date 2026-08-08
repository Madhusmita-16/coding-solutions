# Java Method Overriding 2 (Super Keyword)

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

The Java *instanceof* operator is used to test if the object or instance is an instanceof the specified type.

In this problem we have given you three classes in the editor:

* Student class
* Rockstar class 
* Hacker class 

In the main method, we populated an *ArrayList* with several instances of these classes. *count* method calculates how many instances of each type is present in the ArrayList. The code prints three integers, the number of instance of Student class, the number of instance of Rockstar class, the number of instance of Hacker class.


But some lines of the code are missing, and you have to fix it by modifying only $3$ lines! Don't add, delete or modify any extra line. 

To restore the original code in the editor, click on the top left icon in the editor and create a new buffer.

**Sample Input**

    5
    Student
    Student
    Rockstar
    Student
    Hacker

**Sample Output**

    3 1 1

**Input Format**

 

**Constraints**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T11:08:06.658Z  

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

[View on HackerRank](https://www.hackerrank.com/challenges/java-instanceof-keyword/problem)