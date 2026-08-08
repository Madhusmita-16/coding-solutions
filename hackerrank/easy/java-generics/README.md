# Java Hashset

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Generic methods are a very efficient way to handle multiple datatypes using a single method. This problem will test your knowledge on Java Generic methods.

Let's say you have an integer array and a string array. You have to write a **single** method *printArray* that can print all the elements of both arrays. The method should be able to accept both integer arrays or string arrays.

You are given code in the editor. Complete the code so that it prints the following lines:

    1
    2
    3
    Hello
    World

Do not use method overloading because your answer will not be accepted.

**Input Format**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T10:57:37.583Z  

```java
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int t = Integer.parseInt(br.readLine().trim());
        Set<String> pairs = new HashSet<>();
        for (int i = 0; i < t; i++) {
            String[] parts = br.readLine().trim().split("\\s+");
            String key;
            if (parts[0].compareTo(parts[1]) <= 0) {
                key = parts[0] + " " + parts[1];
            } else {
                key = parts[1] + " " + parts[0];
            }
            pairs.add(key);
            out.println(pairs.size());
        }
        out.flush();
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-generics/problem)