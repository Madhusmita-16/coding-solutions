# Java Hashset

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

In computer science, a set is an abstract data type that can store certain values, without any particular order, and no repeated values(Wikipedia). $\{1,2,3\}$ is an example of a set, but $\{1,2,2\}$ is not a set. Today you will learn how to use sets in java by solving this problem.<br>

You are given $n$ pairs of strings. Two pairs $(a,b)$ and $(c,d)$ are identical if $a=c$ and $b=d$. That also implies $(a,b)$ is *not* same as $(b,a)$. After taking each pair as input, you need to print number of unique pairs you currently have.

Complete the code in the editor to solve this problem.


**Input Format**

In the first line, there will be an integer $T$ denoting number of pairs. Each of the next $T$ lines will contain two strings seperated by a single space.

**Constraints:**

* $1 \le T \le 100000$
* Length of each string is atmost $5$ and will consist lower case letters only.


**Constraints**

 

**Output Format**

Print $T$ lines. In the $i_{th}$ line, print number of unique pairs you have after taking $i^{th}$ pair as input.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-08T09:25:06.655Z  

```java
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());

        Set<String> pairs = new HashSet<>();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < t; i++) {
            String line = br.readLine().trim();  // trim kills \r and stray spaces
            String[] parts = line.split(" ");
            String key = parts[0] + " " + parts[1];
            pairs.add(key);
            output.append(pairs.size()).append("\n");
        }
        System.out.print(output);
    }
}

```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-hashset/problem)