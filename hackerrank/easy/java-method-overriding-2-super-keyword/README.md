# Java Method Overriding

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
**Submitted:** 2026-08-08T11:05:29.967Z  

```java
import java.io.*;
import java.util.*;

class Sports {
    String getName() {
        return "Generic Sports";
    }
    void getNumberOfTeamMembers() {
        System.out.println("Each team has n players in " + getName());
    }
}

class Soccer extends Sports {
    @Override
    String getName() {
        return "Soccer Class";
    }

    @Override
    void getNumberOfTeamMembers() {
        System.out.println("Each team has 11 players in " + getName());
    }
}

public class Solution {

    public static void main(String[] args) {
        Sports sports = new Sports();
        System.out.println(sports.getName());
        sports.getNumberOfTeamMembers();

        Soccer soccer = new Soccer();
        System.out.println(soccer.getName());
        soccer.getNumberOfTeamMembers();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-method-overriding-2-super-keyword/problem)